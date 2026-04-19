CREATE TABLE ZHURONG_PLATFORM_NEST100_STATE_SNAPSHOT
(
    RecID      int not null,
    MState     smallint,
    LastDate   DATETIME,

    SyncTime   DATETIME DEFAULT GETDATE(),
    CreateTime DATETIME DEFAULT GETDATE(),
    UpdateTime DATETIME DEFAULT GETDATE()
);
ALTER TABLE ZHURONG_PLATFORM_NEST100_STATE_SNAPSHOT
    ADD CONSTRAINT PK_NEST100_SNAPSHOT PRIMARY KEY (RecID);
CREATE INDEX IDX_NEST100_SNAPSHOT_LASTDATE
    ON ZHURONG_PLATFORM_NEST100_STATE_SNAPSHOT (LastDate);


--增量游标表（避免每次扫 3 天）
CREATE TABLE ZHURONG_PLATFORM_JOB_CURSOR (
                                             JobName VARCHAR(64) NOT NULL PRIMARY KEY,
                                             CursorTime DATETIME NOT NULL,
                                             UpdateTime DATETIME NOT NULL DEFAULT GETDATE()
);
INSERT INTO ZHURONG_PLATFORM_JOB_CURSOR(JobName, CursorTime) VALUES ('nest100_state_sync', DATEADD(day, -3, GETDATE()));

--Outbox 事件表（可靠投递）
CREATE TABLE ZHURONG_PLATFORM_OUTBOX_EVENT (
                                               Id BIGINT IDENTITY(1,1) PRIMARY KEY,
                                               EventId UNIQUEIDENTIFIER NOT NULL,       -- 全局唯一事件ID（幂等关键）
                                               EventType VARCHAR(64) NOT NULL,          -- NEST100_STATE_CHANGED
                                               AggregateId INT NOT NULL,                -- RecID
                                               OldState SMALLINT NULL,
                                               NewState SMALLINT NOT NULL,
                                               SourceLastDate DATETIME NULL,           -- 原表LastDate（可追溯）
                                               Payload NVARCHAR(MAX) NULL,               -- JSON（可选）
                                               Status SMALLINT NOT NULL DEFAULT 0,       -- 0=NEW,1=SENT,2=FAILED
                                               RetryCount INT NOT NULL DEFAULT 0,
                                               NextRetryTime DATETIME NULL,
                                               CreateTime DATETIME NOT NULL DEFAULT GETDATE(),
                                               UpdateTime DATETIME NOT NULL DEFAULT GETDATE()
);

CREATE UNIQUE INDEX UX_OUTBOX_EVENT_ID ON ZHURONG_PLATFORM_OUTBOX_EVENT(EventId);
CREATE INDEX IX_OUTBOX_STATUS ON ZHURONG_PLATFORM_OUTBOX_EVENT(Status, NextRetryTime);
CREATE INDEX IX_OUTBOX_AGG ON ZHURONG_PLATFORM_OUTBOX_EVENT(AggregateId, CreateTime);
