# Zhurong Platform 项目指南

## 仓库结构

这是一个 Gradle 多模块 Java 21 / Spring Boot 3.4.x 平台项目。

核心模块：

- `zhurong-platform-base`：基础模型、异常、工具类、路径/树工具、XML 导出辅助能力。
- `zhurong-platform-security`：JWT 和 Spring Security 公共支持。
- `zhurong-platform-auth-api` 与 `zhurong-platform-auth`：租户、用户、角色、菜单契约和认证服务。
- `zhurong-platform-core-api` 与 `zhurong-platform-core`：Lantek 和平台标准能力契约/实现。
- `zhurong-platform-custom-api`：客户模块共享 API 和基础实现。
- `zhurong-platform-custom/zhurong-platform-custom-*`：具体客户应用。
- `zhurong-platform-gateway`：网关服务。
- `zhurong-platform-code-generator`：MyBatis-Plus/Freemarker 代码生成器。

当前可见客户模块：

- `zhurong-platform-custom-template`：新客户应用基线模板。
- `zhurong-platform-custom-goodmate`：当前 `settings.gradle` 中启用的客户模块，包含较新的 client-import handler 样例。
- `zhurong-platform-custom-but`：较复杂客户模块，包含 SAP HANA、Oracle ERP、额外实体、controller 和多数据源模式。
- `zhurong-platform-custom-haobao`：包含 POI/ZXing 标签和 Excel 相关依赖的客户模块。
- `zhurong-platform-custom-crun`：较早风格客户模块，包含 OpenFeign/MyBatis starter 模式。

当前 `settings.gradle` 只 include 了 `zhurong-platform-custom-goodmate`，其他客户模块 include 被注释。根目录 `build.gradle` 的 `webServiceProjects` 列表在切换 active 客户模块时也可能需要同步调整。

## 常用配置点

根目录 `build.gradle` 管理主要版本：

- Spring Boot `3.4.13`
- Spring Cloud `2024.0.2`
- Spring Cloud Alibaba `2023.0.3.4`
- MyBatis-Plus `3.5.16`
- Lombok `1.18.42`
- MapStruct `1.5.5.Final`
- Flyway `9.22.3`

客户模块常用依赖：

- Spring Boot application plugin
- Nacos discovery/config
- Spring Cloud LoadBalancer
- dynamic-datasource Spring Boot 3 starter
- Flyway SQL Server support
- SQL Server driver，按需增加 Oracle 和 SAP HANA driver
- `springdoc-openapi-starter-webmvc-ui`
- MapStruct/Lombok annotation processor

新增代码不要把真实账号密码写入 `application.yaml` 并提交。仓库现有文件可能已有本地或客户环境凭据；新开发应优先使用 Nacos 配置、环境变量、部署 overlay 或占位配置。

## 客户模块基线

新客户优先从这些文件开始：

- `zhurong-platform-custom/zhurong-platform-custom-template/build.gradle`
- `zhurong-platform-custom/zhurong-platform-custom-template/src/main/resources/application.yaml`
- `zhurong-platform-custom/zhurong-platform-custom-template/src/main/java/com/zhurong/platform/custom/ZhurongPlatformCustomApplication.java`

应用入口基线注解：

- `@SpringBootApplication`
- `@EnableFeignClients`
- `@MapperScan(basePackages = {"com.zhurong.platform.custom.mapper"})`
- `@ConfigurationPropertiesScan`

资源约定：

- Flyway：`src/main/resources/db/migration`
- MyBatis XML：`src/main/resources/mapper`
- 来自 `custom-api` 的共享 mapper XML：`classpath*:/mapper-common/**/*.xml`
- 服务名通常是 `zhurong-platform-custom`
- 客户专属 Nacos import：`zhurong-platform-custom-{customer}-${spring.profiles.active}`

## 客户代码布局

沿用既有包结构：

- `configuration`：security、RabbitMQ、WebClient、异步线程池、Feign interceptor。
- `controller`：客户专属 REST 接口。
- `service` 和 `service.impl`：业务服务。SQL Server 大批量 `IN` 查询/更新优先复用 `BaseIService`。
- `mapper`、`entity`、`dto`、`vo`、`convert`：MyBatis/MapStruct 模型层。
- `feign`：调用 core Lantek API 等平台服务。
- `httpclient`：外部 HTTP 客户端，通常继承 `BaseApiClient`。
- `properties`：类型化 `@ConfigurationProperties`。
- `clientimport/handler`：客户侧标准导入处理器。

## 标准 Client Import 架构

Core 端职责：

- 外部提交 API 在 `zhurong-platform-core/src/main/java/com/zhurong/platform/core/clientimport/controller/ClientImportController.java` 附近。
- 共享请求 DTO 和 MQ 消息在 `zhurong-platform-core-api/src/main/java/com/zhurong/platform/core/clientimport`。
- 标准业务常量在 `ClientImportBusinessTypes`。
- RabbitMQ command/status 名称在 `ClientImportMqConstants`。
- Core 通过 SQL Server migration 保存标准导入记录，例如 `zhurong-platform-core/src/main/resources/db/migration/V2__client_import_schema.sql`。
- Core 创建派发任务、发布 MQ 任务元数据、跟踪重试/状态，并通过 `IClientImportTaskApi` 暴露内部任务接口。

Custom 端职责：

- 通用监听、分发、状态回写代码在 `zhurong-platform-custom-api/src/main/java/com/zhurong/platform/custom/clientimport`。
- 具体客户在客户模块中实现 `ClientImportHandler<T>` Bean。
- `ClientImportTaskDispatcher` 按 `businessType()` 选择 handler，并根据 `taskId` 从 core 拉取当前待处理数据。
- `ClientStatusReporter` 将 `RECEIVED`、`RUNNING`、`SUCCESS`、`FAILED`、`PONG`、heartbeat 等状态回写 core。
- `zhurong.client-communication.enabled` 控制客户端通信相关 Bean 是否启用。

当前已有标准业务类型：

- `PART_DRAWING_ARCHIVE`
- `PRODUCTION_ORDER`
- `RAW_MATERIAL`
- `PING`

已有业务类型的客户侧实现可参考 `goodmate`：

- `zhurong-platform-custom/zhurong-platform-custom-goodmate/src/main/java/com/zhurong/platform/custom/clientimport/handler/PartDrawingArchiveHandler.java`
- `.../ProductionOrderHandler.java`
- `.../RawMaterialHandler.java`

## 新增标准导入类型

Core API：

1. 在 `zhurong-platform-core-api/src/main/java/com/zhurong/platform/core/clientimport/dto` 增加请求 DTO。
2. 在 `ClientImportBusinessTypes` 增加业务类型常量。
3. 只有 endpoint 或内部契约变化时才扩展 API interface。

Core 实现：

1. 为标准导入表和索引新增 Flyway migration。
2. 增加 entity、mapper、mapper XML、service、service implementation。
3. 实现 `ClientImportBusinessHandler<T, R>`，处理查重、保存、状态更新、pending payload 投影。
4. 如果外部系统会提交该类型，在 `ClientImportController` 增加 endpoint，并在 `ClientImportApplicationServiceImpl` 增加应用服务方法。
5. SQL Server 保持事实来源，MQ 不承载长期业务数据。

Custom 实现：

1. 在每个支持该业务的客户模块中增加 `ClientImportHandler<T>`。
2. 把标准 DTO 字段映射到客户 ERP、Lantek 或本地软件契约。
3. 准确返回成功导入的 core recordId。部分成功时只返回成功项 ID。
4. 明确幂等规则，优先基于 taskId、requestId、业务 key 或客户系统 key。

## 代码生成器说明

生成器相关文件：

- `zhurong-platform-code-generator/src/main/java/com/zhurong/platform/codegenerator/CodeGenerator.java`
- `.../GeneratorConfig.java`
- `.../GeneratorExecutor.java`
- 模板目录：`zhurong-platform-code-generator/src/main/resources/templates`

生成输出目录：

- `zhurong-platform-code-generator/src/main/generated`

推荐流程：

1. 本地设置 DB URL、账号和密码。
2. 选择 `basePackage`、`moduleName`、`includeTables` 和生成 flags。
3. 运行生成器。
4. 审阅生成的 Java/XML/Vue 产物。
5. 只移动需要的内容到 `core-api`、`core`、`custom-api` 或具体客户模块。
6. 编译以刷新 MapStruct 生成实现。

## MyBatis 和数据源规则

- 项目经常保持数据库列名原样，配置为 `map-underscore-to-camel-case: false`。
- 客户模块常见 dynamic datasource 名称包括 `lantek`、`sap`、`sbut`、`erp`、`erp2`。
- 指向非默认数据源的 service implementation 使用 `@DS("name")`。
- `BaseIService` 提供 SQL Server 参数数量限制下的分片 `IN` 查询/更新。
- mapper XML 默认放在模块自己的 `mapper` resources 下；只有共享 XML 才放入 `custom-api` 的 `mapper-common`。

## 构建和验证命令

修改模块 include 后先查看项目列表：

```powershell
.\gradlew.bat projects
```

聚焦编译目标模块：

```powershell
.\gradlew.bat :zhurong-platform-core-api:compileJava
.\gradlew.bat :zhurong-platform-core:compileJava
.\gradlew.bat :zhurong-platform-custom-api:compileJava
.\gradlew.bat :zhurong-platform-custom:zhurong-platform-custom-goodmate:compileJava
```

改动 Gradle 或模块边界后再跑更广的构建：

```powershell
.\gradlew.bat build -x test
```

改动 shared base/security/auth/core 行为时运行测试：

```powershell
.\gradlew.bat test
```

## 扩展评审清单

- `settings.gradle` include 了目标客户模块。
- 根构建辅助任务、publish 脚本、Docker/publish 文件引用了正确模块。
- Nacos config 名称与客户模块和 active profile 匹配。
- 没有新增提交真实凭据。
- Flyway migration 是增量、可重复部署风险可控的。
- mapper scan 覆盖了新增 mapper package。
- Feign client 有唯一 `contextId`。
- 每个部署的客户 `client-id` 唯一。
- RabbitMQ queue/exchange 与 `ClientImportMqConstants` 保持一致。
- client-import handler 返回准确的导入成功 recordId。
- 编译验证覆盖 API 模块和具体实现模块。
