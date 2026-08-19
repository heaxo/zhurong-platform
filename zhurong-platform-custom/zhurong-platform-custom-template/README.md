# Custom 客户端模板

本模板已接入 core 通用透明 HTTP 代理。新客户模块从此目录复制后，可以只部署到 Windows 客户端，不需要在服务器再部署一个 custom 实例。

默认运行方式：

- 客户端使用 Windows 主机名作为 `client-id`。
- 客户端从 Nacos 读取配置并发现 core/auth，但不注册到 Nacos discovery。
- custom HTTP 只监听 `127.0.0.1:9300`。
- core 按当前登录账号的 `sys_user.client_id` 定向发送 MQ 命令。
- 客户端将命令转成本机 HTTP 请求，并把原始状态码、响应头和响应体返回 core。
- `Zhurong_ClientCommandExecution` 按 commandId 持久化结果，防止 MQ 重投重复执行有副作用的接口。

新客户复制后至少需要修改：

1. Gradle `description` 和客户模块目录名。
2. `application.yaml` 中的客户专属 Nacos data-id、RabbitMQ、数据库和本机端口。
3. 在 auth 用户管理页将登录账号绑定到对应 Windows 主机名。
4. 前端客户业务请求使用 `/core/client-proxy/{local-path}`。

客户端必须以 Web 应用启动，不能传入 `--spring.main.web-application-type=none`。

前端 `apps/zhurong-platform-custom-template` 已同步用户管理页，支持新建账号时选择部门、角色、状态并直接绑定客户端主机名。
