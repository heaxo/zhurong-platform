-- A production order number is a display/business number and may be repeated.
-- The ERP internal code remains the only unique manufacturing-order identity.
IF EXISTS (
    SELECT 1
    FROM sys.indexes
    WHERE name = N'UX_RoneindMO_OrderNumber'
      AND object_id = OBJECT_ID(N'dbo.Zhurong_Roneind_ManufacturingOrder')
)
BEGIN
    DROP INDEX UX_RoneindMO_OrderNumber
        ON dbo.Zhurong_Roneind_ManufacturingOrder;
END;
