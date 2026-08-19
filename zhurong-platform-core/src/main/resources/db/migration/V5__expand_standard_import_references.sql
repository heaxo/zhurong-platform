-- 标准导入字段要覆盖客户业务原始长度；尤其 CusRef 会承载“ERP内码-计划跟踪号”组合值。
IF EXISTS (
    SELECT 1 FROM sys.indexes
    WHERE name = 'UX_Zhurong_ProductionOrder_MnORef'
      AND object_id = OBJECT_ID('Zhurong_ProductionOrder')
)
    DROP INDEX UX_Zhurong_ProductionOrder_MnORef ON Zhurong_ProductionOrder;

ALTER TABLE Zhurong_ProductionOrder ALTER COLUMN PrdRef NVARCHAR(255) NULL;
ALTER TABLE Zhurong_ProductionOrder ALTER COLUMN MatRef NVARCHAR(80) NULL;
ALTER TABLE Zhurong_ProductionOrder ALTER COLUMN WrkRef NVARCHAR(80) NULL;
ALTER TABLE Zhurong_ProductionOrder ALTER COLUMN MnORef NVARCHAR(80) NOT NULL;
ALTER TABLE Zhurong_ProductionOrder ALTER COLUMN OrdRef NVARCHAR(80) NULL;
ALTER TABLE Zhurong_ProductionOrder ALTER COLUMN CusRef NVARCHAR(512) NULL;

CREATE UNIQUE INDEX UX_Zhurong_ProductionOrder_MnORef
    ON Zhurong_ProductionOrder(MnORef);

IF EXISTS (
    SELECT 1 FROM sys.indexes
    WHERE name = 'UX_Zhurong_RawMaterial_PrdRef'
      AND object_id = OBJECT_ID('Zhurong_RawMaterial')
)
    DROP INDEX UX_Zhurong_RawMaterial_PrdRef ON Zhurong_RawMaterial;

ALTER TABLE Zhurong_RawMaterial ALTER COLUMN PrdRef NVARCHAR(255) NOT NULL;
ALTER TABLE Zhurong_RawMaterial ALTER COLUMN MatRef NVARCHAR(80) NULL;

CREATE UNIQUE INDEX UX_Zhurong_RawMaterial_PrdRef
    ON Zhurong_RawMaterial(PrdRef);
