-- 象屿宝元（0111）菜单及默认管理员角色权限。
-- 对应前端应用 apps/zhurong-platform-custom-xybaoyuan，支持 Flyway 执行，也可在 SQL Server 中手工执行。
-- 脚本可重复执行：已有菜单会被校正并恢复为有效状态，已有角色菜单关系不会重复插入。

SET XACT_ABORT ON;
BEGIN TRANSACTION;

DECLARE @Now DATETIME = GETDATE();
DECLARE @RootMenuId BIGINT;
DECLARE @BasePartMenuId BIGINT;
DECLARE @SteelPlateMenuId BIGINT;
DECLARE @ManufacturingOrderMenuId BIGINT;
DECLARE @NestMenuId BIGINT;

-- 根目录：象屿宝元
SELECT TOP (1) @RootMenuId = id FROM sys_menu WHERE path = N'/xybaoyuan';
IF @RootMenuId IS NULL
BEGIN
    SET @RootMenuId = 2081501110000000001;
    IF EXISTS (SELECT 1 FROM sys_menu WHERE id = @RootMenuId)
        THROW 50001, N'象屿宝元根菜单预设ID已被其他菜单占用', 1;

    INSERT INTO sys_menu (
        id, tenant_id, deleted, create_by, create_time, update_by, update_time, version,
        pid, name, type, path, active_path, component, auth_code, status, sort_order,
        meta_title, meta_icon, meta_active_icon, meta_badge_type, meta_badge, meta_badge_variants,
        meta_iframe_src, meta_link, meta_keep_alive, meta_affix_tab, meta_hide_in_menu,
        meta_hide_children_in_menu, meta_hide_in_breadcrumb, meta_hide_in_tab, remark
    ) VALUES (
        @RootMenuId, 0, 0, NULL, @Now, NULL, NULL, 0,
        -1, N'XyBaoyuan', N'catalog', N'/xybaoyuan', N'/xybaoyuan/base-parts', NULL, NULL, 1, 10,
        N'象屿宝元', N'mdi:factory', NULL, NULL, NULL, NULL,
        NULL, NULL, 0, 0, 0, 0, 0, 0, N'象屿宝元客户业务菜单'
    );
END;

UPDATE sys_menu SET
    tenant_id = 0, deleted = 0, update_time = @Now, pid = -1, name = N'XyBaoyuan', type = N'catalog',
    path = N'/xybaoyuan', active_path = N'/xybaoyuan/base-parts', component = NULL, auth_code = NULL,
    status = 1, sort_order = 10, meta_title = N'象屿宝元', meta_icon = N'mdi:factory',
    meta_hide_in_menu = 0, meta_hide_children_in_menu = 0, meta_hide_in_breadcrumb = 0, meta_hide_in_tab = 0,
    remark = N'象屿宝元客户业务菜单'
WHERE id = @RootMenuId;

-- 基础零件
SELECT TOP (1) @BasePartMenuId = id FROM sys_menu WHERE path = N'/xybaoyuan/base-parts';
IF @BasePartMenuId IS NULL
BEGIN
    SET @BasePartMenuId = 2081501110000000002;
    IF EXISTS (SELECT 1 FROM sys_menu WHERE id = @BasePartMenuId)
        THROW 50002, N'基础零件菜单预设ID已被其他菜单占用', 1;

    INSERT INTO sys_menu (
        id, tenant_id, deleted, create_by, create_time, update_by, update_time, version,
        pid, name, type, path, active_path, component, auth_code, status, sort_order,
        meta_title, meta_icon, meta_active_icon, meta_badge_type, meta_badge, meta_badge_variants,
        meta_iframe_src, meta_link, meta_keep_alive, meta_affix_tab, meta_hide_in_menu,
        meta_hide_children_in_menu, meta_hide_in_breadcrumb, meta_hide_in_tab, remark
    ) VALUES (
        @BasePartMenuId, 0, 0, NULL, @Now, NULL, NULL, 0,
        @RootMenuId, N'XyBaseParts', N'menu', N'/xybaoyuan/base-parts', NULL,
        N'/xybaoyuan/base-parts', NULL, 1, 0,
        N'基础零件', N'mdi:cube-outline', NULL, NULL, NULL, NULL,
        NULL, NULL, 0, 0, 0, 0, 0, 0, N'基础零件查询、维护与导出'
    );
END;

UPDATE sys_menu SET
    tenant_id = 0, deleted = 0, update_time = @Now, pid = @RootMenuId, name = N'XyBaseParts', type = N'menu',
    path = N'/xybaoyuan/base-parts', active_path = NULL, component = N'/xybaoyuan/base-parts', auth_code = NULL,
    status = 1, sort_order = 0, meta_title = N'基础零件', meta_icon = N'mdi:cube-outline',
    meta_hide_in_menu = 0, meta_hide_children_in_menu = 0, meta_hide_in_breadcrumb = 0, meta_hide_in_tab = 0,
    remark = N'基础零件查询、维护与导出'
WHERE id = @BasePartMenuId;

-- 钢板库存
SELECT TOP (1) @SteelPlateMenuId = id FROM sys_menu WHERE path = N'/xybaoyuan/steel-plates';
IF @SteelPlateMenuId IS NULL
BEGIN
    SET @SteelPlateMenuId = 2081501110000000003;
    IF EXISTS (SELECT 1 FROM sys_menu WHERE id = @SteelPlateMenuId)
        THROW 50003, N'钢板库存菜单预设ID已被其他菜单占用', 1;

    INSERT INTO sys_menu (
        id, tenant_id, deleted, create_by, create_time, update_by, update_time, version,
        pid, name, type, path, active_path, component, auth_code, status, sort_order,
        meta_title, meta_icon, meta_active_icon, meta_badge_type, meta_badge, meta_badge_variants,
        meta_iframe_src, meta_link, meta_keep_alive, meta_affix_tab, meta_hide_in_menu,
        meta_hide_children_in_menu, meta_hide_in_breadcrumb, meta_hide_in_tab, remark
    ) VALUES (
        @SteelPlateMenuId, 0, 0, NULL, @Now, NULL, NULL, 0,
        @RootMenuId, N'XySteelPlates', N'menu', N'/xybaoyuan/steel-plates', NULL,
        N'/xybaoyuan/steel-plates', NULL, 1, 1,
        N'钢板库存', N'mdi:layers-triple-outline', NULL, NULL, NULL, NULL,
        NULL, NULL, 0, 0, 0, 0, 0, 0, N'ERP钢板同步、导入与导出'
    );
END;

UPDATE sys_menu SET
    tenant_id = 0, deleted = 0, update_time = @Now, pid = @RootMenuId, name = N'XySteelPlates', type = N'menu',
    path = N'/xybaoyuan/steel-plates', active_path = NULL, component = N'/xybaoyuan/steel-plates', auth_code = NULL,
    status = 1, sort_order = 1, meta_title = N'钢板库存', meta_icon = N'mdi:layers-triple-outline',
    meta_hide_in_menu = 0, meta_hide_children_in_menu = 0, meta_hide_in_breadcrumb = 0, meta_hide_in_tab = 0,
    remark = N'ERP钢板同步、导入与导出'
WHERE id = @SteelPlateMenuId;

-- 生产订单
SELECT TOP (1) @ManufacturingOrderMenuId = id FROM sys_menu WHERE path = N'/xybaoyuan/manufacturing-orders';
IF @ManufacturingOrderMenuId IS NULL
BEGIN
    SET @ManufacturingOrderMenuId = 2081501110000000004;
    IF EXISTS (SELECT 1 FROM sys_menu WHERE id = @ManufacturingOrderMenuId)
        THROW 50004, N'生产订单菜单预设ID已被其他菜单占用', 1;

    INSERT INTO sys_menu (
        id, tenant_id, deleted, create_by, create_time, update_by, update_time, version,
        pid, name, type, path, active_path, component, auth_code, status, sort_order,
        meta_title, meta_icon, meta_active_icon, meta_badge_type, meta_badge, meta_badge_variants,
        meta_iframe_src, meta_link, meta_keep_alive, meta_affix_tab, meta_hide_in_menu,
        meta_hide_children_in_menu, meta_hide_in_breadcrumb, meta_hide_in_tab, remark
    ) VALUES (
        @ManufacturingOrderMenuId, 0, 0, NULL, @Now, NULL, NULL, 0,
        @RootMenuId, N'XyManufacturingOrders', N'menu', N'/xybaoyuan/manufacturing-orders', NULL,
        N'/xybaoyuan/manufacturing-orders', NULL, 1, 2,
        N'生产订单', N'mdi:clipboard-list-outline', NULL, NULL, NULL, NULL,
        NULL, NULL, 0, 0, 0, 0, 0, 0, N'生产订单设置、LSTX导入与任务重试'
    );
END;

UPDATE sys_menu SET
    tenant_id = 0, deleted = 0, update_time = @Now, pid = @RootMenuId, name = N'XyManufacturingOrders', type = N'menu',
    path = N'/xybaoyuan/manufacturing-orders', active_path = NULL,
    component = N'/xybaoyuan/manufacturing-orders', auth_code = NULL,
    status = 1, sort_order = 2, meta_title = N'生产订单', meta_icon = N'mdi:clipboard-list-outline',
    meta_hide_in_menu = 0, meta_hide_children_in_menu = 0, meta_hide_in_breadcrumb = 0, meta_hide_in_tab = 0,
    remark = N'生产订单设置、LSTX导入与任务重试'
WHERE id = @ManufacturingOrderMenuId;

-- 套料反馈
SELECT TOP (1) @NestMenuId = id FROM sys_menu WHERE path = N'/xybaoyuan/nests';
IF @NestMenuId IS NULL
BEGIN
    SET @NestMenuId = 2081501110000000005;
    IF EXISTS (SELECT 1 FROM sys_menu WHERE id = @NestMenuId)
        THROW 50005, N'套料反馈菜单预设ID已被其他菜单占用', 1;

    INSERT INTO sys_menu (
        id, tenant_id, deleted, create_by, create_time, update_by, update_time, version,
        pid, name, type, path, active_path, component, auth_code, status, sort_order,
        meta_title, meta_icon, meta_active_icon, meta_badge_type, meta_badge, meta_badge_variants,
        meta_iframe_src, meta_link, meta_keep_alive, meta_affix_tab, meta_hide_in_menu,
        meta_hide_children_in_menu, meta_hide_in_breadcrumb, meta_hide_in_tab, remark
    ) VALUES (
        @NestMenuId, 0, 0, NULL, @Now, NULL, NULL, 0,
        @RootMenuId, N'XyNests', N'menu', N'/xybaoyuan/nests', NULL,
        N'/xybaoyuan/nests', NULL, 1, 3,
        N'套料反馈', N'mdi:view-grid-outline', NULL, NULL, NULL, NULL,
        NULL, NULL, 0, 0, 0, 0, 0, 0, N'套料结果发送金蝶及撤回反馈'
    );
END;

UPDATE sys_menu SET
    tenant_id = 0, deleted = 0, update_time = @Now, pid = @RootMenuId, name = N'XyNests', type = N'menu',
    path = N'/xybaoyuan/nests', active_path = NULL, component = N'/xybaoyuan/nests', auth_code = NULL,
    status = 1, sort_order = 3, meta_title = N'套料反馈', meta_icon = N'mdi:view-grid-outline',
    meta_hide_in_menu = 0, meta_hide_children_in_menu = 0, meta_hide_in_breadcrumb = 0, meta_hide_in_tab = 0,
    remark = N'套料结果发送金蝶及撤回反馈'
WHERE id = @NestMenuId;

-- 默认授权给V2初始化的“超级管理员”和“管理员”。父目录与页面菜单必须同时授权，否则无法构建菜单树。
DECLARE @Grants TABLE (
    id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    menu_id BIGINT NOT NULL
);

INSERT INTO @Grants (id, role_id, menu_id) VALUES
    (2081501110000000101, 1, @RootMenuId),
    (2081501110000000102, 1, @BasePartMenuId),
    (2081501110000000103, 1, @SteelPlateMenuId),
    (2081501110000000104, 1, @ManufacturingOrderMenuId),
    (2081501110000000105, 1, @NestMenuId),
    (2081501110000000201, 2026254113280397314, @RootMenuId),
    (2081501110000000202, 2026254113280397314, @BasePartMenuId),
    (2081501110000000203, 2026254113280397314, @SteelPlateMenuId),
    (2081501110000000204, 2026254113280397314, @ManufacturingOrderMenuId),
    (2081501110000000205, 2026254113280397314, @NestMenuId);

-- 恢复被逻辑删除的同一角色菜单关系。
UPDATE role_menu SET
    role_menu.deleted = 0,
    role_menu.update_time = @Now
FROM sys_role_menu role_menu
INNER JOIN @Grants grant_item
    ON grant_item.role_id = role_menu.role_id AND grant_item.menu_id = role_menu.menu_id;

-- 仅给数据库中真实存在的角色授权。
INSERT INTO sys_role_menu (
    id, tenant_id, deleted, create_by, create_time, update_by, update_time, version, role_id, menu_id
)
SELECT
    grant_item.id, 0, 0, NULL, @Now, NULL, NULL, 0, grant_item.role_id, grant_item.menu_id
FROM @Grants grant_item
WHERE EXISTS (SELECT 1 FROM sys_role role WHERE role.id = grant_item.role_id AND role.deleted = 0)
  AND NOT EXISTS (
      SELECT 1 FROM sys_role_menu role_menu
      WHERE role_menu.role_id = grant_item.role_id AND role_menu.menu_id = grant_item.menu_id
  );

COMMIT TRANSACTION;
