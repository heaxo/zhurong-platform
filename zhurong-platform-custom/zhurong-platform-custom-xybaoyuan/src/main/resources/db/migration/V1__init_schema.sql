CREATE TABLE dbo.Zhurong_Xybaoyuan_BasePart (
    id BIGINT NOT NULL PRIMARY KEY, is_deleted BIT NOT NULL DEFAULT 0, version INT NOT NULL DEFAULT 0,
    created_by BIGINT NULL, created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), updated_by BIGINT NULL,
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), is_read BIT NOT NULL DEFAULT 0, is_reviewed BIT NOT NULL DEFAULT 0,
    prd_ref NVARCHAR(80) NOT NULL, prd_name NVARCHAR(255) NULL, drawing_code NVARCHAR(255) NOT NULL,
    mat_ref NVARCHAR(80) NULL, thickness FLOAT NULL, rou_ref NVARCHAR(80) NULL, cus_ref NVARCHAR(255) NULL,
    cus_name NVARCHAR(255) NULL, udata1 NVARCHAR(255) NULL, udata2 NVARCHAR(255) NULL, udata3 NVARCHAR(255) NULL,
    invalid_state BIT NOT NULL DEFAULT 0
);
CREATE UNIQUE INDEX UX_XyBasePart_PrdRef ON dbo.Zhurong_Xybaoyuan_BasePart(prd_ref) WHERE is_deleted = 0;
CREATE UNIQUE INDEX UX_XyBasePart_DrawingCode ON dbo.Zhurong_Xybaoyuan_BasePart(drawing_code) WHERE is_deleted = 0;

CREATE TABLE dbo.Zhurong_Xybaoyuan_ImportTask (
    id BIGINT NOT NULL PRIMARY KEY, is_deleted BIT NOT NULL DEFAULT 0, version INT NOT NULL DEFAULT 0,
    created_by BIGINT NULL, created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), updated_by BIGINT NULL,
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), is_read BIT NOT NULL DEFAULT 0, is_reviewed BIT NOT NULL DEFAULT 0,
    business_type NVARCHAR(40) NOT NULL, status NVARCHAR(20) NOT NULL, record_ids_json NVARCHAR(MAX) NOT NULL,
    attempts INT NOT NULL DEFAULT 0, message NVARCHAR(2000) NULL, execution_time DATETIME2 NULL
);
CREATE INDEX IX_XyImportTask_Status ON dbo.Zhurong_Xybaoyuan_ImportTask(status, created_at);

CREATE TABLE dbo.Zhurong_Xybaoyuan_ManufacturingOrder (
    id BIGINT NOT NULL PRIMARY KEY, is_deleted BIT NOT NULL DEFAULT 0, version INT NOT NULL DEFAULT 0,
    created_by BIGINT NULL, created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), updated_by BIGINT NULL,
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), is_read BIT NOT NULL DEFAULT 0, is_reviewed BIT NOT NULL DEFAULT 0,
    production_order_number NVARCHAR(80) NOT NULL, production_order_line_id NVARCHAR(80) NULL,
    production_order_erp_internal_code NVARCHAR(80) NOT NULL, prd_ref NVARCHAR(80) NOT NULL, prd_name NVARCHAR(255) NULL,
    mat_ref NVARCHAR(80) NULL, wrk_ref NVARCHAR(80) NULL, thickness FLOAT NULL, quantity INT NOT NULL,
    delivery_date DATETIME2 NULL, rou_ref NVARCHAR(80) NULL, cus_ref NVARCHAR(255) NULL, cus_name NVARCHAR(255) NULL,
    udata1 NVARCHAR(255) NULL, udata2 NVARCHAR(255) NULL, work_center NVARCHAR(80) NULL,
    job_ref NVARCHAR(80) NULL, job_name NVARCHAR(255) NULL, production_workshop_code NVARCHAR(80) NULL,
    production_workshop_name NVARCHAR(255) NULL, read_state BIT NOT NULL DEFAULT 0, read_time DATETIME2 NULL,
    send_state BIT NOT NULL DEFAULT 0, send_time DATETIME2 NULL, invalid_state BIT NOT NULL DEFAULT 0, last_task_id BIGINT NULL
);
CREATE UNIQUE INDEX UX_XyMO_ErpCode ON dbo.Zhurong_Xybaoyuan_ManufacturingOrder(production_order_erp_internal_code) WHERE is_deleted = 0;

CREATE TABLE dbo.Zhurong_Xybaoyuan_SteelPlate (
    id BIGINT NOT NULL PRIMARY KEY, is_deleted BIT NOT NULL DEFAULT 0, version INT NOT NULL DEFAULT 0,
    created_by BIGINT NULL, created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), updated_by BIGINT NULL,
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), is_read     BIT NOT NULL DEFAULT 0, is_reviewed BIT NOT NULL DEFAULT 0,
    erp_material_id INT NULL, prd_ref NVARCHAR(255) NOT NULL, prd_name NVARCHAR(255) NULL, specification NVARCHAR(255) NULL,
    mat_ref NVARCHAR(80) NULL, stock_name NVARCHAR(255) NULL, stock_number NVARCHAR(80) NULL, tons FLOAT NULL,
    quantity FLOAT NOT NULL, thickness FLOAT NULL, width FLOAT NULL, length FLOAT NULL, lot_number NVARCHAR(255) NULL,
    remark NVARCHAR(255) NULL, read_state BIT NOT NULL DEFAULT 0, read_time DATETIME2 NULL, send_state BIT NOT NULL DEFAULT 0,
    send_time DATETIME2 NULL, invalid_state BIT NOT NULL DEFAULT 0, last_task_id BIGINT NULL
);
CREATE UNIQUE INDEX UX_XySteelPlate_PrdRef ON dbo.Zhurong_Xybaoyuan_SteelPlate(prd_ref) WHERE is_deleted = 0;

CREATE TABLE dbo.Zhurong_Xybaoyuan_NestFeedbackState (
    id BIGINT NOT NULL PRIMARY KEY, is_deleted BIT NOT NULL DEFAULT 0, version INT NOT NULL DEFAULT 0,
    created_by BIGINT NULL, created_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), updated_by BIGINT NULL,
    updated_at DATETIME2 NOT NULL DEFAULT SYSDATETIME(), is_read BIT NOT NULL DEFAULT 0, is_reviewed BIT NOT NULL DEFAULT 0,
    nest_rec_id INT NOT NULL, nst_ref NVARCHAR(80) NOT NULL, sent BIT NOT NULL DEFAULT 0, sent_at DATETIME2 NULL, remark NVARCHAR(1000) NULL
);
CREATE UNIQUE INDEX UX_XyNestFeedback_RecId ON dbo.Zhurong_Xybaoyuan_NestFeedbackState(nest_rec_id) WHERE is_deleted = 0;
