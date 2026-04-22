create table Zhurong_But_SupplierInfo
(
    id BIGINT NOT NULL PRIMARY KEY,
    created_at DATETIME NOT NULL CONSTRAINT DF_supplier_info_created_at DEFAULT GETDATE(),
    updated_at DATETIME NOT NULL CONSTRAINT DF_supplier_info_updated_at DEFAULT GETDATE(),
    created_by BIGINT NULL,
    updated_by BIGINT NULL,
    version INT NOT NULL CONSTRAINT DF_supplier_info_version DEFAULT 0,
    is_deleted BIT NOT NULL CONSTRAINT DF_supplier_info_is_deleted DEFAULT 0,
    is_read BIT NOT NULL CONSTRAINT DF_supplier_info_is_read DEFAULT 0,
    is_reviewed BIT NOT NULL CONSTRAINT DF_supplier_info_is_reviewed DEFAULT 0,
    cnc nvarchar(255) not null,
    loc_name nvarchar(255) not null,
    sht_ref nvarchar(255) not null,
    sht_name nvarchar(255) null,
    quantity int not null default 0,
    whs_name nvarchar(40) null,
    batch_number nvarchar(255) null,
    weight float,
    unit nvarchar(40) null,
    business_type nvarchar(40) null
);