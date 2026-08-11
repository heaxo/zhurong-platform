CREATE TABLE dbo.Zhurong_Xybaoyuan_CodeSequence (
    id BIGINT NOT NULL PRIMARY KEY,
    is_deleted BIT NOT NULL DEFAULT 0,
    version INT NOT NULL DEFAULT 0,
    created_by BIGINT NULL,
    created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    updated_by BIGINT NULL,
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    is_read BIT NOT NULL DEFAULT 0,
    is_reviewed BIT NOT NULL DEFAULT 0,
    sequence_key NVARCHAR(100) NOT NULL,
    current_value BIGINT NOT NULL,
    increment_by INT NOT NULL DEFAULT 1,
    last_allocated_at DATETIME2 NULL,
    CONSTRAINT CK_XyCodeSequence_CurrentValue CHECK (current_value >= 0),
    CONSTRAINT CK_XyCodeSequence_IncrementBy CHECK (increment_by > 0)
);

CREATE UNIQUE INDEX UX_XyCodeSequence_SequenceKey
    ON dbo.Zhurong_Xybaoyuan_CodeSequence(sequence_key)
    WHERE is_deleted = 0;

INSERT INTO dbo.Zhurong_Xybaoyuan_CodeSequence (
    id,
    sequence_key,
    current_value,
    increment_by
) VALUES (
    20260811000004001,
    N'JOB',
    100000000,
    1
);
