# 部署指南 (Windows)

## 1. 环境要求

项目       要求
---------- ---------------------------------------------
操作系统   Windows Server 2019 / 2022 或 Windows 10/11
CPU        ≥ 4 Core
内存       ≥ 8GB
磁盘       ≥ 50GB
软件       Docker Desktop (包含 Docker Compose)

安装 Docker Desktop：\
https://www.docker.com/products/docker-desktop/

验证安装：

``` powershell
docker -v
docker compose version
```

------------------------------------------------------------------------

# 2. 部署包目录结构

部署目录如下：

    deploy
    │
    ├─ docker-compose.yml
    ├─ standalone-mysql.yaml
    │
    ├─ images
    │   ├─ ao-platform-gateway.tar
    │   ├─ ao-platform-auth.tar
    │   ├─ ao-platform-core.tar
    │   └─ ao-platform-custom-first.tar
    │
    ├─ import-images.ps1
    ├─ deploy.ps1

------------------------------------------------------------------------

# 3. 导入 Docker 镜像

进入部署目录：

``` powershell
cd deploy
```

执行导入脚本：

``` powershell
.\import-images.ps1
```

如果需要手动导入镜像，可执行：

``` powershell
docker load -i images/ao-platform-gateway.tar
docker load -i images/ao-platform-auth.tar
docker load -i images/ao-platform-core.tar
docker load -i images/ao-platform-custom-first.tar
```

查看镜像：

``` powershell
docker images
```

应看到：

    a.he/ao-platform-gateway
    a.he/ao-platform-auth
    a.he/ao-platform-core
    a.he/ao-platform-custom-first

------------------------------------------------------------------------

# 4. 启动系统

启动全部服务：

``` powershell
docker compose up -d
```

查看服务状态：

``` powershell
docker compose ps
```

------------------------------------------------------------------------

# 5. 查看日志

查看 Gateway 日志：

``` powershell
docker logs -f ao-platform-gateway
```

查看 Auth 日志：

``` powershell
docker logs -f ao-platform-auth
```

查看 Core 日志：

``` powershell
docker logs -f ao-platform-core
```

------------------------------------------------------------------------

# 6. 服务访问地址

服务                地址
------------------- -----------------------
Gateway             http://服务器IP:9000
Auth                http://服务器IP:9100
Core                http://服务器IP:9200
Custom              http://服务器IP:9300
Nacos               http://服务器IP:8848
RabbitMQ 管理后台   http://服务器IP:15672

RabbitMQ 默认账号：

    admin
    admin

------------------------------------------------------------------------

# 7. 停止系统

``` powershell
docker compose down
```

------------------------------------------------------------------------

# 8. 重启系统

``` powershell
docker compose restart
```

------------------------------------------------------------------------

# 9. 常见问题

## 端口被占用

查看端口：

``` powershell
netstat -ano
```

修改 `docker-compose.yml` 中的端口映射。

------------------------------------------------------------------------

## 查看所有容器

``` powershell
docker ps
```

------------------------------------------------------------------------

## 查看容器日志

``` powershell
docker logs -f 容器名
```

示例：

``` powershell
docker logs -f ao-platform-gateway
```
