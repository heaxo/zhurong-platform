---
name: zhurong-platform-extension
description: 面向 zhurong-platform 的项目专属扩展指南。适用于在这个 Gradle Spring Boot 3 Java 21 多模块项目中新增客户模块、复制或改造 custom 模块、开发客户专属业务、扩展 Lantek 表 CRUD、接入 RabbitMQ Feign 客户端通信、增加标准 client-import 导入业务、迁移代码生成器产物、调整模块 include 构建发布配置，或诊断新客户新业务扩展问题。
---

# Zhurong Platform 扩展

## 用途

使用本技能在 Zhurong 平台上扩展新客户和新业务，同时保持模块边界清晰。平台标准契约放在 `core-api`、`core`、`custom-api`，客户差异化实现放在当前目标 `zhurong-platform-custom-*` 模块。

开始实现或评审前，按需读取 `references/project-guide.md`，里面有更完整的项目结构、落点和清单。

## 第一轮判断

1. 先判断需求类型：
   - 新客户模块：从 `zhurong-platform-custom/zhurong-platform-custom-template` 开始。
   - 客户专属功能：在目标 `zhurong-platform-custom/zhurong-platform-custom-{customer}` 模块内实现。
   - 可复用客户契约或公共辅助能力：优先考虑 `zhurong-platform-custom-api`。
   - 新标准导入业务：同时更新 core 端标准接入和 custom 端客户处理器。
   - Lantek CRUD 或 API 能力：先查看生成器和已有生成代码模式，再决定是否手写。
2. 编码前先找最近的已有样例：
   - `goodmate`：当前 client-import 客户端处理器样例。
   - `but`：多数据源、ERP、SAP HANA、客户复杂业务样例。
   - `template`：新客户模块基线。
3. 检查 `settings.gradle`，确认当前启用的客户模块。新增或切换 include 要显式处理，不要假设所有客户模块会一起编译。
4. 新增配置不要提交真实账号密码。真实凭据放到 Nacos、环境变量、部署 overlay 或外部配置。

## 模块边界

- `zhurong-platform-core-api`：core 能力的共享 API 契约、DTO、MQ 消息、Feign 接口。
- `zhurong-platform-core`：平台标准实现、Lantek 服务、标准 client-import 接收、落库、派发和重试。
- `zhurong-platform-custom-api`：客户模块共享基础代码、Lantek 镜像对象、通用 controller/service、客户端导入监听分发和状态回写框架。
- `zhurong-platform-custom/zhurong-platform-custom-{customer}`：客户专属应用、数据源配置、controller、Feign client、ERP/SAP/HTTP 适配器、导入 handler、部署脚本。
- `zhurong-platform-code-generator`：通过 MyBatis-Plus、MapStruct、Freemarker 生成 DTO/VO/API/XML/Vue 等产物到 `src/main/generated`；生成物必须先审阅再移动。

## 新客户流程

1. 复制 `zhurong-platform-custom-template` 为 `zhurong-platform-custom-{customer}`。
2. 修改 Gradle `description`、Nacos import、部署脚本名、客户专属 properties。
3. 更新 `settings.gradle`，只 include 当前构建需要的客户模块。
4. 按客户实际需要补依赖，例如 AMQP、OpenFeign、POI/ZXing、Oracle、SAP HANA、额外 datasource。
5. 配置 `spring.application.name`、端口、Nacos discovery/config、Flyway schema/table、dynamic datasource、`zhurong.client-communication.*`。
6. 客户代码放入既有包结构：`controller`、`service`、`service.impl`、`mapper`、`entity`、`dto`、`vo`、`convert`、`feign`、`httpclient`、`properties`，以及必要的集成子包。
7. 用最窄的 Gradle 任务先验证目标模块能编译。

## Client Import 流程

对于已有标准导入类型，通常只实现客户侧 handler：

1. 在客户模块 `clientimport/handler` 下新增 `ClientImportHandler<T>` Bean。
2. `businessType()` 返回准确的 `ClientImportBusinessTypes.*` 常量，`payloadType()` 返回 `core-api` 中对应 DTO 类型。
3. 用 `ClientImportContext#getDataList()` 获取业务数据，用 `getRecordIds()` 获取 core 业务表记录 ID。
4. 用 `ClientImportResult.success(...)`、`successAll(...)` 或 `failed(...)` 返回执行结果；只把真正导入成功的 core recordId 放入结果。
5. 只有需要消费 RabbitMQ 任务的部署环境才开启 `zhurong.client-communication.enabled`。

对于新增标准导入业务类型，需要更新完整契约：

1. 在 `zhurong-platform-core-api` 增加 DTO、API 契约或 MQ 常量。
2. 在 `zhurong-platform-core` 增加 entity、mapper、migration、service、`ClientImportBusinessHandler` 实现。
3. 在 `ClientImportApplicationServiceImpl` 注册接入逻辑；如果由外部系统提交，还要暴露 controller endpoint。
4. 在每个支持该业务的客户模块增加客户侧 `ClientImportHandler<T>`。
5. 保持现有设计：MQ 只承载任务元数据，custom 模块按 taskId 通过 Feign 从 core 拉取当前未导入数据。

## 代码生成

代码生成器只能作为起点，不要把生成物未经审阅直接覆盖业务模块：

1. 修改 `zhurong-platform-code-generator/src/main/java/com/zhurong/platform/codegenerator/CodeGenerator.java`。
2. 明确设置 `DatabaseConfig`、`GeneratorConfig.basePackage`、`moduleName`、`includeTables`、`useDbColumnName`、`importLantekBaseEntity`。
3. 运行生成器后检查 `zhurong-platform-code-generator/src/main/generated`。
4. 只把审阅后的 Java/XML 产物移动到目标 API 模块或客户模块。
5. 如果 converter 改动，编译后检查 MapStruct 生成源是否符合预期。

## 验证

先跑聚焦的 Gradle 命令：

```powershell
.\gradlew.bat :zhurong-platform-custom:zhurong-platform-custom-goodmate:compileJava
.\gradlew.bat :zhurong-platform-core:compileJava
.\gradlew.bat :zhurong-platform-custom-api:compileJava
```

如果改了模块 include 或构建边界，再跑：

```powershell
.\gradlew.bat projects
.\gradlew.bat build -x test
```

RabbitMQ/client-import 相关改动除了编译，还要核对配置：

- `zhurong.rabbitmq.enabled` 和 `zhurong.client-communication.enabled` 是否按环境明确开启或关闭。
- `client-id` 是否在每个客户客户端部署中唯一。
- core 和 custom 服务是否共用可达的 Nacos/RabbitMQ 配置。
- 状态回写是否通过 `ClientImportCoreFeignClient` 和 `IClientImportTaskApi`。

## 参考资料

读取 `references/project-guide.md` 获取项目结构、当前客户模块、常用文件位置和扩展检查清单。
