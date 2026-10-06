CREATE TABLE dbo.Zhurong_Scjinggong_BasePart
(
    id               BIGINT        NOT NULL PRIMARY KEY,
    is_deleted       BIT           NOT NULL DEFAULT 0,
    version          INT           NOT NULL DEFAULT 0,
    created_by       BIGINT        NULL,
    created_at       DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    updated_by       BIGINT        NULL,
    updated_at       DATETIME2     NOT NULL DEFAULT SYSDATETIME(),
    is_read          BIT           NOT NULL DEFAULT 0,
    is_reviewed      BIT           NOT NULL DEFAULT 0,
    invalid_state    BIT           NOT NULL DEFAULT 0,
    prd_ref          NVARCHAR(80)  NOT NULL,--物料编码
    prd_name         NVARCHAR(255) NULL,--物料名称
    wrk_ref          NVARCHAR(40)  NULL,--机床
    mat_ref          NVARCHAR(80)  NOT NULL,--材质
    thickness        FLOAT         NOT NULL,--厚度
    quantity         INT           NULL,--数量
    udata1           NVARCHAR(255) NULL,--层级
    udata2           NVARCHAR(255) NULL,--客户件号
    udata3           NVARCHAR(255) NULL,--物料参数
    udata4           NVARCHAR(255) NULL,--工艺路线集合
    udata5           NVARCHAR(255) NULL,--子件物料编码
    udata6           NVARCHAR(255) NULL,--子件物料名称
    udata7           NVARCHAR(255) NULL,--子件物料规格
    udata8           NVARCHAR(255) NULL,--子件物料材质
    drawing_path     NVARCHAR(500) NOT NULL,--图纸路径
    raw_drawing_path NVARCHAR(500) NOT NULL--原始图纸路径
);
EXEC sp_addextendedproperty 'MS_Description', N'物料编码', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'prd_ref';
EXEC sp_addextendedproperty 'MS_Description', N'物料名称', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'prd_name';
EXEC sp_addextendedproperty 'MS_Description', N'机床', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'wrk_ref';
EXEC sp_addextendedproperty 'MS_Description', N'材质', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'mat_ref';
EXEC sp_addextendedproperty 'MS_Description', N'厚度', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'thickness';
EXEC sp_addextendedproperty 'MS_Description', N'数量', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'quantity';
EXEC sp_addextendedproperty 'MS_Description', N'层级', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata1';
EXEC sp_addextendedproperty 'MS_Description', N'客户件号', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata2';
EXEC sp_addextendedproperty 'MS_Description', N'物料参数', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata3';
EXEC sp_addextendedproperty 'MS_Description', N'工艺路线集合', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata4';
EXEC sp_addextendedproperty 'MS_Description', N'子件物料编码', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata5';
EXEC sp_addextendedproperty 'MS_Description', N'子件物料名称', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata6';
EXEC sp_addextendedproperty 'MS_Description', N'子件物料规格', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata7';
EXEC sp_addextendedproperty 'MS_Description', N'子件物料材质', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'udata8';
EXEC sp_addextendedproperty 'MS_Description', N'图纸路径', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'drawing_path';
EXEC sp_addextendedproperty 'MS_Description', N'原始图纸路径', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_BasePart', 'COLUMN', 'raw_drawing_path';
CREATE UNIQUE INDEX UX_ScBasePart_PrdRef ON dbo.Zhurong_Scjinggong_BasePart (prd_ref) WHERE is_deleted = 0;

CREATE TABLE dbo.Zhurong_Scjinggong_Order
(
    id            BIGINT    NOT NULL PRIMARY KEY,
    is_deleted    BIT       NOT NULL DEFAULT 0,
    version       INT       NOT NULL DEFAULT 0,
    created_by    BIGINT    NULL,
    created_at    DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    updated_by    BIGINT    NULL,
    updated_at    DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    is_read       BIT       NOT NULL DEFAULT 0,
    is_reviewed   BIT       NOT NULL DEFAULT 0,
    invalid_state BIT       NOT NULL DEFAULT 0,
    order_code  NVARCHAR(255) NOT NULL,--批次号
    order_name  NVARCHAR(255) NOT NULL,--批次名
    udata1           NVARCHAR(255) NULL,
    udata2           NVARCHAR(255) NULL,
    udata3           NVARCHAR(255) NULL,
    udata4           NVARCHAR(255) NULL,
    udata5           NVARCHAR(255) NULL
);
EXEC sp_addextendedproperty 'MS_Description', N'批次号', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_Order', 'COLUMN', 'order_code';
EXEC sp_addextendedproperty 'MS_Description', N'批次名', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_Order', 'COLUMN', 'order_name';
CREATE UNIQUE INDEX UX_ScOrder_Code ON dbo.Zhurong_Scjinggong_Order (order_code) WHERE is_deleted = 0;

CREATE TABLE dbo.Zhurong_Scjinggong_OrderItem
(
    id            BIGINT    NOT NULL PRIMARY KEY,
    is_deleted    BIT       NOT NULL DEFAULT 0,
    version       INT       NOT NULL DEFAULT 0,
    created_by    BIGINT    NULL,
    created_at    DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    updated_by    BIGINT    NULL,
    updated_at    DATETIME2 NOT NULL DEFAULT SYSDATETIME(),
    is_read       BIT       NOT NULL DEFAULT 0,
    is_reviewed   BIT       NOT NULL DEFAULT 0,
    invalid_state BIT       NOT NULL DEFAULT 0,
    prd_ref     NVARCHAR(80) NOT NULL,
    wrk_ref     NVARCHAR(40) NOT NULL,
    cus_ref     NVARCHAR(40) NOT NULL,
    ord_ref     NVARCHAR(40) NOT NULL,
    quantity     INT NOT NULL,
    order_id     BIGINT NOT NULL,
    rdate       datetime NOT NULL,--订单交货日期
    udata1           NVARCHAR(255) NULL,--加工中心编码
    udata2           NVARCHAR(255) NULL,--U8生产订单号
    udata3           NVARCHAR(255) NULL,--收料仓库编码
    udata4           NVARCHAR(255) NULL,--收料仓库名称
    udata5           NVARCHAR(255) NULL,--工序行号
    udata6           NVARCHAR(255) NULL,--工序编码
    udata7           NVARCHAR(255) NULL,--工序名称
    udata8           NVARCHAR(255) NULL,--班组编码
    udata9           NVARCHAR(255) NULL,--班组名称
    udata10           NVARCHAR(255) NULL,--计划完工时间
    udata11           NVARCHAR(255) NULL,--订单变更后交期
    udata12           NVARCHAR(255) NULL,--工单状态
);
EXEC sp_addextendedproperty 'MS_Description', N'订单交货日期', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'rdate';
EXEC sp_addextendedproperty 'MS_Description', N'加工中心编码', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata1';
EXEC sp_addextendedproperty 'MS_Description', N'U8生产订单号', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata2';
EXEC sp_addextendedproperty 'MS_Description', N'收料仓库编码', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata3';
EXEC sp_addextendedproperty 'MS_Description', N'收料仓库名称', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata4';
EXEC sp_addextendedproperty 'MS_Description', N'工序行号', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata5';
EXEC sp_addextendedproperty 'MS_Description', N'工序编码', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata6';
EXEC sp_addextendedproperty 'MS_Description', N'工序名称', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata7';
EXEC sp_addextendedproperty 'MS_Description', N'班组编码', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata8';
EXEC sp_addextendedproperty 'MS_Description', N'班组名称', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata9';
EXEC sp_addextendedproperty 'MS_Description', N'计划完工时间', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata10';
EXEC sp_addextendedproperty 'MS_Description', N'订单变更后交期', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata11';
EXEC sp_addextendedproperty 'MS_Description', N'工单状态', 'SCHEMA', 'dbo', 'TABLE', 'Zhurong_Scjinggong_OrderItem', 'COLUMN', 'udata12';
CREATE UNIQUE INDEX UX_ScOrderItem_CusRef ON dbo.Zhurong_Scjinggong_OrderItem (cus_ref) WHERE is_deleted = 0;
