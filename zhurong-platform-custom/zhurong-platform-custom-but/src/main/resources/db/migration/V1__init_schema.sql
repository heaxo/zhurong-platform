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
    whs_name nvarchar(40) not null,
    loc_name nvarchar(255) not null,
    prd_ref nvarchar(40) not null
);