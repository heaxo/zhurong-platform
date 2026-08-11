ALTER TABLE dbo.Zhurong_Xybaoyuan_ManufacturingOrder
    ALTER COLUMN quantity FLOAT NOT NULL;

IF NOT EXISTS (
    SELECT 1
    FROM sys.indexes
    WHERE name = N'UX_XyMO_OrderNumber'
      AND object_id = OBJECT_ID(N'dbo.Zhurong_Xybaoyuan_ManufacturingOrder')
)
BEGIN
    CREATE UNIQUE INDEX UX_XyMO_OrderNumber
        ON dbo.Zhurong_Xybaoyuan_ManufacturingOrder(production_order_number)
        WHERE is_deleted = 0;
END;
