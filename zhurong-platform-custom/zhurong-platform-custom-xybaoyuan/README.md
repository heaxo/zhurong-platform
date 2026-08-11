# 象屿宝元（0111）客户模块

本模块迁移 MesLink 0111 前端实际使用的客户功能，并保留金蝶实际调用的基础零件、生产订单入站接口；未照搬其余历史接口。

## 已实现范围

- 基础零件：分页、新增、批量删除、CSV 导出。
- ERP 钢板：按物料编号/批号同步、分页、批量删除、同步或异步导入、CSV 导出。
- 生产订单：分页、设备/作业/作废批量更新、作业复用或创建、同步或异步导入、失败任务重试、CSV 导出。
- 套料：实时读取 Lantek 套料详情，向金蝶发送反馈或撤销反馈。
- 金蝶入站：批量接收基础零件和生产订单，按生产订单 ERP 内码去重和幂等，并保持0111的工艺路线截断及零件Excel输出语义。

0111 套料页面中“套料拆分”和“作业发送”调用的是基类虚方法，客户 `FeedbackService` 没有实现；这些历史入口未迁移。除金蝶实际使用的两个 `Creates` 外，其余未被前端或外部系统使用的历史入站接口未迁移。

金蝶可继续使用原兼容地址 `POST /0111/BasePart/Creates`、`POST /0111/ManufacturingOrder/Creates`，也可使用平台规范地址 `POST /xybaoyuan/base-parts/creates`、`POST /xybaoyuan/manufacturing-orders/creates`。四个地址均要求与原0111相同的认证。

## LSTX 字段约定

MasterLink 可以分别写入 `MnORef`、`OrderRef`、`CusRef`，LSTX 不能直接指定 `MnORef`。本模块采用以下可逆映射：

- `OrdRef = productionOrderNumber`
- `CusRef = productionOrderErpInternalCode + "-" + cusRef`
- `Reference = 基础零件 drawingCode`
- `UserData3 = 基础零件 udata3`（金蝶零件物料内码）

反馈时只按第一个 `-` 拆分 `CusRef`，因此计划跟踪号本身包含横线时不会丢失。ERP 内码按现有业务约束应为不含横线的数字字符串。

`productionOrderNumber` 是允许重复的业务展示单号；`productionOrderErpInternalCode` 才是生产订单唯一标识。入站查重、数据库唯一约束以及 Lantek 导入结果核验均以 ERP 内码为准。导入结果查询可以用 `OrdRef` 缩小候选范围，但最终必须从组合 `CusRef` 中还原 ERP 内码进行匹配，不能把 `OrdRef` 当成唯一键。

钢板导入后使用 `DIS_UData1_Sht` 保存仓库名称，使用 `DIS_UData2_Sht` 保存 ERP 物料内码，使用 `DIS_UData3_Sht` 保存批号。套料反馈优先从原始板材的 `DIS_UData2_Sht` 读取 `FMATERIALID`，并兼容旧数据的 `DIS_UData1_Prt`。

## 运行配置

象屿宝元现场配置集中在 `src/main/resources/application.yaml` 的 `xybaoyuan` 配置段，部署前应逐项核对地址、路径、密钥和 FTP 参数。

基础零件入站使用 `xybaoyuan.inbound.base-part-excel-directory`、`drawing-root` 和 `drawing-extension`。Excel按日期追加、按ERP物料内码去重，并采用临时文件原子替换，避免写入中断损坏已有文件。

生产订单和钢板只允许由用户主动发起同步导入；LSTX/PRC 导入使用进程级公平锁串行执行，避免 Lantek 自动化进程并发冲突。接口会在 Lantek 导入和结果核验完成后同步返回最终 `SUCCESS` 或 `FAILED` 状态，不再定时执行或自动重试待处理任务。

## Long 类型与前端 ID

本客户服务将所有 Java `Long`/`long` 响应值序列化为 JSON 字符串，避免雪花 ID 超出 JavaScript `Number.MAX_SAFE_INTEGER` 后发生精度丢失。前端的实体 ID、任务 ID、批量选择键和更新请求均保持字符串形式；分页总数等仅用于界面计算的安全范围数值由前端显式转换，业务主键禁止调用 `Number()`。

## 作业创建

新建作业编码由 `Zhurong_Xybaoyuan_CodeSequence` 的 `JOB` 序列分配，首次编码为 `100000001`，之后按 1 原子递增。新建作业必须指定当前 Lantek 作业树中真实存在的文件夹路径；后端再次校验路径后，使用 Expert PRC 的 `CreateJob` 指令在该目录创建作业。

## 过滤唯一索引

本模块业务唯一索引均使用 `WHERE is_deleted = 0`。MyBatis-Plus逻辑删除会把记录更新为 `is_deleted = 1`，记录随后退出过滤索引，因此删除后可以重新插入相同业务编码，不会产生唯一键冲突。索引仅禁止同时存在两条未删除的相同业务数据，用于保护查询单值假设，并避免多实例并发绕过应用层查重。

生产订单表仅对 `production_order_erp_internal_code` 建立过滤唯一索引，不对允许重复的 `production_order_number` 建立唯一索引。若历史环境已经执行过 V2 并创建了 `UX_XyMO_OrderNumber`，V3 会安全删除该索引。

`invalid_state` 是业务作废状态，不等同于逻辑删除。作废数据仍保留业务唯一性，这与原0111生产订单接收时对历史订单执行幂等判断的行为一致；需要重新接收时应执行逻辑删除或明确设计重开流程。
