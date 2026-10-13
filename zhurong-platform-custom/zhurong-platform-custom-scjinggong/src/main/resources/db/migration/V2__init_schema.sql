alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata13 nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata14 nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata15 nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata16 nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata17 nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata18 nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata19 nvarchar(200) null;
alter TABLE dbo.Zhurong_Scjinggong_OrderItem add udata20 nvarchar(200) null;


CREATE TABLE dbo.Zhurong_Scjinggong_SteelPlateInventory
(
    id            BIGINT        NOT NULL PRIMARY KEY,
    is_deleted    BIT           NOT NULL DEFAULT 0,
    version       INT           NOT NULL DEFAULT 0,
    created_by    BIGINT        NULL,
    created_at    DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    updated_by    BIGINT        NULL,
    updated_at    DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    is_read       BIT           NOT NULL DEFAULT 0,
    is_reviewed   BIT           NOT NULL DEFAULT 0,
    invalid_state BIT           NOT NULL DEFAULT 0,
    sht_ref       NVARCHAR(40)  NOT NULL,--钢板编码
    mat_ref       NVARCHAR(40)  NOT NULL,--材质
    thickness     float         NOT NULL,--厚度
    length        float         NOT NULL,--长度
    width         float         NOT NULL,--宽度
    quantity      int           NOT NULL,--数量
    udata1        NVARCHAR(255) NULL,
    udata2        NVARCHAR(255) NULL,
    udata3        NVARCHAR(255) NULL,
    udata4        NVARCHAR(255) NULL,
    udata5        NVARCHAR(255) NULL,
    udata6        NVARCHAR(255) NULL,
    udata7        NVARCHAR(255) NULL,
    udata8        NVARCHAR(255) NULL,
    udata9        NVARCHAR(255) NULL,
    udata10       NVARCHAR(255) NULL,
    udata11       NVARCHAR(255) NULL,
    udata12       NVARCHAR(255) NULL,
    udata13       NVARCHAR(255) NULL,
    udata14       NVARCHAR(255) NULL,
    udata15       NVARCHAR(255) NULL,
    udata16       NVARCHAR(255) NULL,
    udata17       NVARCHAR(255) NULL,
    udata18       NVARCHAR(255) NULL,
    udata19       NVARCHAR(255) NULL,
    udata20       NVARCHAR(255) NULL
);
EXEC sp_addextendedproperty 'MS_Description', N'钢板编码', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_SteelPlateInventory', 'COLUMN', 'sht_ref';
EXEC sp_addextendedproperty 'MS_Description', N'材质', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_SteelPlateInventory', 'COLUMN', 'mat_ref';
EXEC sp_addextendedproperty 'MS_Description', N'厚度', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_SteelPlateInventory', 'COLUMN', 'thickness';
EXEC sp_addextendedproperty 'MS_Description', N'长度', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_SteelPlateInventory', 'COLUMN', 'length';
EXEC sp_addextendedproperty 'MS_Description', N'宽度', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_SteelPlateInventory', 'COLUMN', 'width';
EXEC sp_addextendedproperty 'MS_Description', N'数量', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_SteelPlateInventory', 'COLUMN', 'quantity';