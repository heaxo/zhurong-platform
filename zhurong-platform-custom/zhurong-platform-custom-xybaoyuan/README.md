# 象屿宝元（0111）客户模块

本模块迁移 MesLink 0111 前端实际使用的客户功能，并保留金蝶实际调用的基础零件、生产订单入站接口；未照搬其余历史接口。

## 已实现范围

- 基础零件：分页、新增、批量删除、CSV 导出。
- ERP 钢板：按物料编号/批号同步、分页、批量删除、同步或异步导入、CSV 导出。
- 生产订单：分页、设备/作业/作废批量更新、作业复用或创建、同步或异步导入、失败任务重试、CSV 导出。
- 套料：实时读取 Lantek 套料详情，向金蝶发送反馈或撤销反馈。
- 金蝶入站：批量接收基础零件和生产订单，保持0111的去重、幂等、工艺路线截断及零件Excel输出语义。

0111 套料页面中“套料拆分”和“作业发送”调用的是基类虚方法，客户 `FeedbackService` 没有实现；这些历史入口未迁移。除金蝶实际使用的两个 `Creates` 外，其余未被前端或外部系统使用的历史入站接口未迁移。

金蝶可继续使用原兼容地址 `POST /0111/BasePart/Creates`、`POST /0111/ManufacturingOrder/Creates`，也可使用平台规范地址 `POST /xybaoyuan/base-parts/creates`、`POST /xybaoyuan/manufacturing-orders/creates`。四个地址均要求与原0111相同的认证。

## LSTX 字段约定

MasterLink 可以分别写入 `MnORef`、`OrderRef`、`CusRef`，LSTX 不能直接指定 `MnORef`。本模块采用以下可逆映射：

- `OrdRef = productionOrderNumber`
- `CusRef = productionOrderErpInternalCode + "-" + cusRef`
- `Reference = 基础零件 drawingCode`
- `UserData3 = 基础零件 udata3`（金蝶零件物料内码）

反馈时只按第一个 `-` 拆分 `CusRef`，因此计划跟踪号本身包含横线时不会丢失。ERP 内码按现有业务约束应为不含横线的数字字符串。

钢板导入后使用 `DIS_UData1_Sht` 保存仓库名称，使用 `DIS_UData2_Sht` 保存 ERP 物料内码，使用 `DIS_UData3_Sht` 保存批号。套料反馈优先从原始板材的 `DIS_UData2_Sht` 读取 `FMATERIALID`，并兼容旧数据的 `DIS_UData1_Prt`。

## 运行配置

象屿宝元现场配置集中在 `src/main/resources/application.yaml` 的 `xybaoyuan` 配置段，部署前应逐项核对地址、路径、密钥和 FTP 参数。

基础零件入站使用 `xybaoyuan.inbound.base-part-excel-directory`、`drawing-root` 和 `drawing-extension`。Excel按日期追加、按ERP物料内码去重，并采用临时文件原子替换，避免写入中断损坏已有文件。

生产订单和钢板导入任务最多自动尝试三次；LSTX/PRC 导入使用进程级公平锁串行执行，避免 Lantek 自动化进程并发冲突。
