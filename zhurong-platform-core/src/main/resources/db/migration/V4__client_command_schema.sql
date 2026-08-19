CREATE TABLE Zhurong_ClientCommandTask
(
    Id BIGINT NOT NULL PRIMARY KEY,
    Deleted BIT NOT NULL DEFAULT 0,
    Version INT NOT NULL DEFAULT 0,
    CreateBy BIGINT NULL,
    CreateTime DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateBy BIGINT NULL,
    UpdateTime DATETIME NULL,

    CommandId NVARCHAR(128) NOT NULL,
    CommandType NVARCHAR(128) NOT NULL,
    TargetClientId NVARCHAR(128) NOT NULL,
    Status NVARCHAR(64) NOT NULL,
    PayloadJson NVARCHAR(MAX) NULL,
    ResultJson NVARCHAR(MAX) NULL,
    ResultMessage NVARCHAR(1000) NULL,
    PublishTime DATETIME NULL,
    FinishTime DATETIME NULL
);

CREATE UNIQUE INDEX UX_Zhurong_ClientCommandTask_CommandId
    ON Zhurong_ClientCommandTask(CommandId);
CREATE INDEX IX_Zhurong_ClientCommandTask_ClientStatus
    ON Zhurong_ClientCommandTask(TargetClientId, Status, CreateTime);

DECLARE @currentSchema sysname = SCHEMA_NAME();
EXEC sys.sp_addextendedproperty
    @name=N'MS_Description',
    @value=N'通用客户端定向命令记录表，core不解释客户业务载荷',
    @level0type=N'SCHEMA', @level0name=@currentSchema,
    @level1type=N'TABLE', @level1name=N'Zhurong_ClientCommandTask';
