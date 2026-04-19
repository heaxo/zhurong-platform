create table Zhurong_But_SupplierInfo
(
    id BIGINT IDENTITY(1,1) NOT NULL PRIMARY KEY,
    created_at DATETIME NOT NULL CONSTRAINT DF_example_entity_created_at DEFAULT GETDATE(),
    updated_at DATETIME NOT NULL CONSTRAINT DF_example_entity_updated_at DEFAULT GETDATE(),
    created_by BIGINT NULL,
    updated_by BIGINT NULL,
    version INT NOT NULL CONSTRAINT DF_example_entity_version DEFAULT 0,
    is_deleted BIT NOT NULL CONSTRAINT DF_example_entity_is_deleted DEFAULT 0,
    is_read BIT NOT NULL CONSTRAINT DF_example_entity_is_read DEFAULT 0,
    is_reviewed BIT NOT NULL CONSTRAINT DF_example_entity_is_reviewed DEFAULT 0,
    nst_ref nvarchar(80) not null,
    supplier_name nvarchar(255) null,
    whs_name nvarchar(40) null,
    udata1 nvarchar(255) null,
    udata2 nvarchar(255) null,
    udata3 nvarchar(255) null
);