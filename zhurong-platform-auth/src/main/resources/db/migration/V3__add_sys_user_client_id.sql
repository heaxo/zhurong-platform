-- 登录账号绑定的 Windows 客户端主机名，用于通过 RabbitMQ 定向执行本机 Lantek 任务。
IF COL_LENGTH('sys_user', 'client_id') IS NULL
BEGIN
    ALTER TABLE sys_user ADD client_id NVARCHAR(128) NULL;
END;

IF NOT EXISTS (
    SELECT 1
    FROM sys.indexes
    WHERE name = 'idx_user_client_id'
      AND object_id = OBJECT_ID('sys_user')
)
BEGIN
    CREATE INDEX idx_user_client_id ON sys_user(client_id);
END;
