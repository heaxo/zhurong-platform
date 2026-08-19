CREATE TABLE dbo.Zhurong_Xybaoyuan_ClientCommandExecution
(
    id BIGINT NOT NULL PRIMARY KEY,
    is_deleted BIT NOT NULL DEFAULT 0,
    version INT NOT NULL DEFAULT 0,
    created_by BIGINT NULL,
    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    updated_by BIGINT NULL,
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    is_read BIT NOT NULL DEFAULT 0,
    is_reviewed BIT NOT NULL DEFAULT 0,
    command_id NVARCHAR(128) NOT NULL,
    command_type NVARCHAR(128) NOT NULL,
    status NVARCHAR(32) NOT NULL,
    message NVARCHAR(1000) NULL,
    result_json NVARCHAR(MAX) NULL
);

CREATE UNIQUE INDEX UX_XyClientCommandExecution_CommandId
    ON dbo.Zhurong_Xybaoyuan_ClientCommandExecution(command_id)
    WHERE is_deleted = 0;
