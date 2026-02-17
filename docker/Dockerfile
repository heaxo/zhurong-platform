FROM nacos/nacos-server:v2.4.2

# 创建 plugins 目录
RUN mkdir -p /home/nacos/plugins

# 拷贝 PostgreSQL 插件
COPY nacos-plugins/nacos-postgresql-datasource-plugin-ext-1.0.0-SNAPSHOT.jar \
     /home/nacos/plugins/