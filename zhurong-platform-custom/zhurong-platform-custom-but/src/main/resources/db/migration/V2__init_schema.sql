create table Zhurong_But_Nesting_Parts_Split_Records
(
    id BIGINT NOT NULL PRIMARY KEY,
    created_at DATETIME NOT NULL CONSTRAINT DF_split_records_created_at DEFAULT GETDATE(),
    updated_at DATETIME NOT NULL CONSTRAINT DF_split_records_updated_at DEFAULT GETDATE(),
    created_by BIGINT NULL,
    updated_by BIGINT NULL,
    version INT NOT NULL CONSTRAINT DF_split_records_version DEFAULT 0,
    is_deleted BIT NOT NULL CONSTRAINT DF_split_records_is_deleted DEFAULT 0,
    is_read BIT NOT NULL CONSTRAINT DF_split_records_is_read DEFAULT 0,
    is_reviewed BIT NOT NULL CONSTRAINT DF_split_records_is_reviewed DEFAULT 0,
    nst_ref nvarchar(80) not null,
    mno_ref nvarchar(40) not null,
    opr_id int not null,
    quantity int not null,
    remark nvarchar(500) null,
    ord_ref nvarchar(40) not null,
    rec_id int not null
);