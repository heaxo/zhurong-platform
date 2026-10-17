CREATE TABLE dbo.Zhurong_Scjinggong_NestPartFeedbackState
(
    id               BIGINT         NOT NULL PRIMARY KEY,
    is_deleted       BIT            NOT NULL DEFAULT 0,
    version          INT            NOT NULL DEFAULT 0,
    created_by       BIGINT         NULL,
    created_at       DATETIME2      NOT NULL DEFAULT SYSDATETIME(),
    updated_by       BIGINT         NULL,
    updated_at       DATETIME2      NOT NULL DEFAULT SYSDATETIME(),
    is_read          BIT            NOT NULL DEFAULT 0,
    is_reviewed      BIT            NOT NULL DEFAULT 0,
    nest_rec_id      INT            NOT NULL,
    nest_part_rec_id INT            NOT NULL,
    nst_ref          NVARCHAR(80)   NULL,
    order_id         BIGINT         NOT NULL,
    order_code       NVARCHAR(255)  NOT NULL,
    order_name       NVARCHAR(255)  NOT NULL,
    sent             BIT            NOT NULL DEFAULT 0,
    attempt_count    INT            NOT NULL DEFAULT 0,
    sent_at          DATETIME2      NULL,
    remark           NVARCHAR(1000) NULL
);

CREATE UNIQUE INDEX UX_ScNestPartFeedback_RecId
    ON dbo.Zhurong_Scjinggong_NestPartFeedbackState (nest_part_rec_id)
    WHERE is_deleted = 0;

CREATE INDEX IX_ScNestPartFeedback_NestRecId
    ON dbo.Zhurong_Scjinggong_NestPartFeedbackState (nest_rec_id, sent)
    WHERE is_deleted = 0;

EXEC sp_addextendedproperty 'MS_Description', N'NEST100 RecID', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_NestPartFeedbackState', 'COLUMN', 'nest_rec_id';
EXEC sp_addextendedproperty 'MS_Description', N'NEST500 RecID（全局唯一）', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_NestPartFeedbackState', 'COLUMN', 'nest_part_rec_id';
EXEC sp_addextendedproperty 'MS_Description', N'是否反馈成功', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_NestPartFeedbackState', 'COLUMN', 'sent';
EXEC sp_addextendedproperty 'MS_Description', N'反馈尝试次数', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_NestPartFeedbackState', 'COLUMN', 'attempt_count';
EXEC sp_addextendedproperty 'MS_Description', N'最近一次反馈结果', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_NestPartFeedbackState', 'COLUMN', 'remark';
