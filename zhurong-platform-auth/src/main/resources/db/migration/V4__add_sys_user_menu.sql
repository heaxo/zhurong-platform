-- 用户管理页面：用于创建登录账号，并维护账号与 Windows 客户端主机名的绑定关系。
IF NOT EXISTS (SELECT 1 FROM sys_menu WHERE id = 2000004)
BEGIN
    INSERT INTO sys_menu (
        id, tenant_id, deleted, create_by, create_time, update_by, update_time, version,
        pid, name, type, path, active_path, component, auth_code, status, sort_order,
        meta_title, meta_icon, meta_active_icon, meta_badge_type, meta_badge,
        meta_badge_variants, meta_iframe_src, meta_link, meta_keep_alive, meta_affix_tab,
        meta_hide_in_menu, meta_hide_children_in_menu, meta_hide_in_breadcrumb,
        meta_hide_in_tab, remark
    ) VALUES (
        2000004, 0, 0, NULL, SYSDATETIME(), NULL, NULL, 0,
        2000000, N'用户管理', N'menu', N'/system/user', NULL, N'/system/user/list', NULL, 0, 4,
        N'用户管理', N'carbon:user-multiple', NULL, NULL, NULL,
        NULL, NULL, NULL, 0, 0,
        0, 0, 0, 0, N'创建账号并维护客户端主机名绑定'
    );
END;

-- 给已有且拥有“系统管理”目录权限的角色补充用户管理菜单权限。
;WITH eligible_roles AS (
    SELECT DISTINCT role_id, tenant_id
    FROM sys_role_menu
    WHERE menu_id = 2000000
      AND deleted = 0
)
INSERT INTO sys_role_menu (
    id, tenant_id, deleted, create_by, create_time, update_by, update_time, version,
    role_id, menu_id
)
SELECT
    2090000000000000000 + ROW_NUMBER() OVER (ORDER BY eligible_roles.role_id),
    eligible_roles.tenant_id,
    0,
    NULL,
    SYSDATETIME(),
    NULL,
    NULL,
    0,
    eligible_roles.role_id,
    2000004
FROM eligible_roles
WHERE NOT EXISTS (
      SELECT 1
      FROM sys_role_menu existing_permission
      WHERE existing_permission.role_id = eligible_roles.role_id
        AND existing_permission.menu_id = 2000004
        AND existing_permission.deleted = 0
  );
