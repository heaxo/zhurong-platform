CREATE TABLE Zhurong_PartDrawingArchive
(
    Id BIGINT NOT NULL PRIMARY KEY,
    Deleted BIT NOT NULL DEFAULT 0,
    Version INT NOT NULL DEFAULT 0,
    CreateBy BIGINT NULL,
    CreateTime DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateBy BIGINT NULL,
    UpdateTime DATETIME NULL,

    RequestId NVARCHAR(128) NOT NULL,
    RequestItemIndex INT NOT NULL,
    TaskId NVARCHAR(128) NULL,
    TargetClientId NVARCHAR(128) NOT NULL,
    PrdRef NVARCHAR(40) NOT NULL,
    PrdName NVARCHAR(255) NULL,
    MatRef NVARCHAR(40) NOT NULL,
    Thickness float NOT NULL,
    WrkRef NVARCHAR(40) NULL,
    OriginalImage NVARCHAR(1000) NULL,
    StoredFileId NVARCHAR(128) NULL,
    StoredRelativePath NVARCHAR(1000) NULL,
    ClientImagePath NVARCHAR(1000) NULL,
    UData1 NVARCHAR(255) NULL,
    UData2 NVARCHAR(255) NULL,
    UData3 NVARCHAR(255) NULL,
    UData4 NVARCHAR(255) NULL,
    UData5 NVARCHAR(255) NULL,
    UData6 NVARCHAR(255) NULL,
    UData7 NVARCHAR(255) NULL,
    UData8 NVARCHAR(255) NULL,
    ExtensionsJson NVARCHAR(MAX) NULL,
    Imported BIT NOT NULL DEFAULT 0,
    DispatchStatus NVARCHAR(64) NOT NULL,
    DispatchMessage NVARCHAR(1000) NULL
);
CREATE UNIQUE INDEX UX_Zhurong_PartDrawingArchive_PrdRef ON Zhurong_PartDrawingArchive(PrdRef);
CREATE INDEX IX_Zhurong_PartDrawingArchive_RequestId ON Zhurong_PartDrawingArchive(RequestId);
CREATE INDEX IX_Zhurong_PartDrawingArchive_TaskId ON Zhurong_PartDrawingArchive(TaskId);
CREATE INDEX IX_Zhurong_PartDrawingArchive_TargetClientId ON Zhurong_PartDrawingArchive(TargetClientId);

CREATE TABLE Zhurong_ProductionOrder
(
    Id BIGINT NOT NULL PRIMARY KEY,
    Deleted BIT NOT NULL DEFAULT 0,
    Version INT NOT NULL DEFAULT 0,
    CreateBy BIGINT NULL,
    CreateTime DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateBy BIGINT NULL,
    UpdateTime DATETIME NULL,

    RequestId NVARCHAR(128) NOT NULL,
    RequestItemIndex INT NOT NULL,
    TaskId NVARCHAR(128) NULL,
    TargetClientId NVARCHAR(128) NOT NULL,
    PrdRef NVARCHAR(40) NULL,
    PrdName NVARCHAR(255) NULL,
    MatRef NVARCHAR(40) NULL,
    Thickness float NULL,
    WrkRef NVARCHAR(40) NULL,
    OriginalImage NVARCHAR(1000) NULL,
    StoredFileId NVARCHAR(128) NULL,
    StoredRelativePath NVARCHAR(1000) NULL,
    ClientImagePath NVARCHAR(1000) NULL,
    MnORef NVARCHAR(40) NOT NULL,
    OrdRef NVARCHAR(40) NULL,
    CusRef NVARCHAR(40) NULL,
    Quantity INT NOT NULL,
    ExtensionsJson NVARCHAR(MAX) NULL,
    Imported BIT NOT NULL DEFAULT 0,
    DispatchStatus NVARCHAR(64) NOT NULL,
    DispatchMessage NVARCHAR(1000) NULL
);
CREATE UNIQUE INDEX UX_Zhurong_ProductionOrder_MnORef ON Zhurong_ProductionOrder(MnORef);
CREATE INDEX IX_Zhurong_ProductionOrder_RequestId ON Zhurong_ProductionOrder(RequestId);
CREATE INDEX IX_Zhurong_ProductionOrder_TaskId ON Zhurong_ProductionOrder(TaskId);
CREATE INDEX IX_Zhurong_ProductionOrder_TargetClientId ON Zhurong_ProductionOrder(TargetClientId);

CREATE TABLE Zhurong_RawMaterial
(
    Id BIGINT NOT NULL PRIMARY KEY,
    Deleted BIT NOT NULL DEFAULT 0,
    Version INT NOT NULL DEFAULT 0,
    CreateBy BIGINT NULL,
    CreateTime DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateBy BIGINT NULL,
    UpdateTime DATETIME NULL,

    RequestId NVARCHAR(128) NOT NULL,
    RequestItemIndex INT NOT NULL,
    TaskId NVARCHAR(128) NULL,
    TargetClientId NVARCHAR(128) NOT NULL,
    PrdRef NVARCHAR(40) NOT NULL,
    PrdName NVARCHAR(255) NULL,
    MatRef NVARCHAR(40) NULL,
    Thickness FLOAT NULL,
    [Length] FLOAT NULL,
    Width FLOAT NULL,
    Quantity INT NULL,
    UData1 NVARCHAR(255) NULL,
    UData2 NVARCHAR(255) NULL,
    UData3 NVARCHAR(255) NULL,
    OriginalImage NVARCHAR(1000) NULL,
    StoredFileId NVARCHAR(128) NULL,
    StoredRelativePath NVARCHAR(1000) NULL,
    ClientImagePath NVARCHAR(1000) NULL,
    IsRemnant BIT NOT NULL DEFAULT 0,
    ExtensionsJson NVARCHAR(MAX) NULL,
    Imported BIT NOT NULL DEFAULT 0,
    DispatchStatus NVARCHAR(64) NOT NULL,
    DispatchMessage NVARCHAR(1000) NULL
);
CREATE UNIQUE INDEX UX_Zhurong_RawMaterial_PrdRef ON Zhurong_RawMaterial(PrdRef);
CREATE INDEX IX_Zhurong_RawMaterial_RequestId ON Zhurong_RawMaterial(RequestId);
CREATE INDEX IX_Zhurong_RawMaterial_TaskId ON Zhurong_RawMaterial(TaskId);
CREATE INDEX IX_Zhurong_RawMaterial_TargetClientId ON Zhurong_RawMaterial(TargetClientId);

CREATE TABLE Zhurong_ClientDispatchTask
(
    Id BIGINT NOT NULL PRIMARY KEY,
    Deleted BIT NOT NULL DEFAULT 0,
    Version INT NOT NULL DEFAULT 0,
    CreateBy BIGINT NULL,
    CreateTime DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateBy BIGINT NULL,
    UpdateTime DATETIME NULL,

    TaskId NVARCHAR(128) NOT NULL,
    RequestId NVARCHAR(128) NOT NULL,
    BusinessType NVARCHAR(64) NOT NULL,
    TargetClientId NVARCHAR(128) NOT NULL,
    Status NVARCHAR(64) NOT NULL,
    RetryCount INT NOT NULL DEFAULT 0,
    RoutingKey NVARCHAR(255) NOT NULL,
    RequestSnapshot NVARCHAR(MAX) NULL,
    ResponseSnapshot NVARCHAR(MAX) NULL,
    ErrorMessage NVARCHAR(1000) NULL,
    PublishTime DATETIME NULL,
    ReceiveTime DATETIME NULL,
    FinishTime DATETIME NULL
);
CREATE UNIQUE INDEX UX_Zhurong_ClientDispatchTask_TaskId ON Zhurong_ClientDispatchTask(TaskId);
CREATE UNIQUE INDEX UX_Zhurong_ClientDispatchTask_RequestBusiness ON Zhurong_ClientDispatchTask(RequestId, BusinessType);
CREATE INDEX IX_Zhurong_ClientDispatchTask_TargetClientId ON Zhurong_ClientDispatchTask(TargetClientId);
CREATE INDEX IX_Zhurong_ClientDispatchTask_Status ON Zhurong_ClientDispatchTask(Status, CreateTime);

CREATE TABLE Zhurong_ClientRegistry
(
    Id BIGINT NOT NULL PRIMARY KEY,
    Deleted BIT NOT NULL DEFAULT 0,
    Version INT NOT NULL DEFAULT 0,
    CreateBy BIGINT NULL,
    CreateTime DATETIME NOT NULL DEFAULT GETDATE(),
    UpdateBy BIGINT NULL,
    UpdateTime DATETIME NULL,

    ClientId NVARCHAR(128) NOT NULL,
    UserName NVARCHAR(128) NULL,
    ClientVersion NVARCHAR(64) NULL,
    Status NVARCHAR(64) NOT NULL,
    LastHeartbeatTime DATETIME NULL
);
CREATE UNIQUE INDEX UX_Zhurong_ClientRegistry_ClientId ON Zhurong_ClientRegistry(ClientId);
CREATE INDEX IX_Zhurong_ClientRegistry_Status ON Zhurong_ClientRegistry(Status, LastHeartbeatTime);

DECLARE @currentSchema sysname = SCHEMA_NAME();
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'钣金件图纸档案标准导入表', @level0type=N'SCHEMA', @level0name=@currentSchema, @level1type=N'TABLE', @level1name=N'Zhurong_PartDrawingArchive';
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'生产订单标准导入表', @level0type=N'SCHEMA', @level0name=@currentSchema, @level1type=N'TABLE', @level1name=N'Zhurong_ProductionOrder';
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'原材料标准导入表', @level0type=N'SCHEMA', @level0name=@currentSchema, @level1type=N'TABLE', @level1name=N'Zhurong_RawMaterial';
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'客户端异步派发任务表', @level0type=N'SCHEMA', @level0name=@currentSchema, @level1type=N'TABLE', @level1name=N'Zhurong_ClientDispatchTask';
EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'客户端注册和心跳状态表', @level0type=N'SCHEMA', @level0name=@currentSchema, @level1type=N'TABLE', @level1name=N'Zhurong_ClientRegistry';
