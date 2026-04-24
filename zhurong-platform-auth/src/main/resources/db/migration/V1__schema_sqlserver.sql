-- =====================================================
-- 租户表
-- =====================================================
CREATE TABLE sys_tenant
(
    id          BIGINT PRIMARY KEY,
    name        NVARCHAR(200) NOT NULL,
    status      SMALLINT     NOT NULL DEFAULT 1,
    expire_time DATETIME,
    create_time DATETIME    NOT NULL ,
    update_time DATETIME,
    version     INT      NOT NULL DEFAULT 0
);

CREATE INDEX idx_tenant_status ON sys_tenant (status);


-- =====================================================
-- 部门表
-- =====================================================
CREATE TABLE sys_dept
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT       NOT NULL,
    deleted     BIT      NOT NULL DEFAULT 0,

    create_by   BIGINT,
    create_time DATETIME    NOT NULL ,
    update_by   BIGINT,
    update_time DATETIME,
    version     INT      NOT NULL DEFAULT 0,

    pid         BIGINT,
    name        NVARCHAR(100) NOT NULL,
    status      SMALLINT     NOT NULL DEFAULT 1,
    sort_order  INT               DEFAULT 0,
    remark      NVARCHAR(MAX)
);

CREATE INDEX idx_dept_tenant ON sys_dept (tenant_id);
CREATE INDEX idx_dept_pid ON sys_dept (pid);
CREATE INDEX idx_dept_status ON sys_dept (status);

-- =====================================================
-- 角色表
-- =====================================================
CREATE TABLE sys_role
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT       NOT NULL,
    deleted     BIT      NOT NULL DEFAULT 0,

    create_by   BIGINT,
    create_time DATETIME    NOT NULL ,
    update_by   BIGINT,
    update_time DATETIME,
    version     INT      NOT NULL DEFAULT 0,

    name        NVARCHAR(100) NOT NULL,
    code        NVARCHAR(100),
    status      SMALLINT     NOT NULL DEFAULT 1,
    data_scope  SMALLINT              DEFAULT 1,
    remark      NVARCHAR(MAX)
);

CREATE INDEX idx_role_tenant ON sys_role (tenant_id);
CREATE INDEX idx_role_status ON sys_role (status);



-- =====================================================
-- 用户表
-- =====================================================
CREATE TABLE sys_user
(
    id              BIGINT PRIMARY KEY,
    tenant_id       BIGINT       NOT NULL,
    deleted         BIT      NOT NULL DEFAULT 0,

    create_by       BIGINT,
    create_time     DATETIME    NOT NULL ,
    update_by       BIGINT,
    update_time     DATETIME,
    version         INT      NOT NULL DEFAULT 0,

    username        NVARCHAR(100) NOT NULL,
    password        NVARCHAR(255) NOT NULL,
    real_name       NVARCHAR(100),
    dept_id         BIGINT,
    status          SMALLINT     NOT NULL DEFAULT 1,

    last_login_time DATETIME,
    last_login_ip   NVARCHAR(50),
    remark          NVARCHAR(MAX)
);

CREATE INDEX idx_user_tenant ON sys_user (tenant_id);
CREATE INDEX idx_user_dept ON sys_user (dept_id);
CREATE INDEX idx_user_status ON sys_user (status);



-- =====================================================
-- 用户角色关联表
-- =====================================================
CREATE TABLE sys_user_role
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT    NOT NULL,
    deleted     BIT   NOT NULL DEFAULT 0,

    create_by   BIGINT,
    create_time DATETIME NOT NULL ,
    update_by   BIGINT,
    update_time DATETIME,
    version     INT   NOT NULL DEFAULT 0,

    user_id     BIGINT    NOT NULL,
    role_id     BIGINT    NOT NULL
);

CREATE INDEX idx_user_role_user ON sys_user_role (user_id);
CREATE INDEX idx_user_role_role ON sys_user_role (role_id);



-- =====================================================
-- 菜单表（平台级）
-- 如果你希望菜单全局共享，可以去掉 tenant_id
-- =====================================================
CREATE TABLE sys_menu
(
    id                         BIGINT PRIMARY KEY,
    tenant_id                  BIGINT       NOT NULL,
    deleted                    BIT      NOT NULL DEFAULT 0,

    create_by                  BIGINT,
    create_time                DATETIME    NOT NULL ,
    update_by                  BIGINT,
    update_time                DATETIME,
    version                    INT      NOT NULL DEFAULT 0,

    pid                        BIGINT,
    name                       NVARCHAR(100) NOT NULL,
    type                       NVARCHAR(20)  NOT NULL,
    path                       NVARCHAR(255),
    active_path                NVARCHAR(255),
    component                  NVARCHAR(255),

    auth_code                  NVARCHAR(100),

    status                     SMALLINT     NOT NULL DEFAULT 1,
    sort_order                 INT               DEFAULT 0,

    meta_title                 NVARCHAR(200) NOT NULL,
    meta_icon                  NVARCHAR(100),
    meta_active_icon           NVARCHAR(100),

    meta_badge_type            NVARCHAR(50),
    meta_badge                 NVARCHAR(50),
    meta_badge_variants        NVARCHAR(50),

    meta_iframe_src            NVARCHAR(255),
    meta_link                  NVARCHAR(255),

    meta_keep_alive            BIT               DEFAULT 0,
    meta_affix_tab             BIT               DEFAULT 0,
    meta_hide_in_menu          BIT               DEFAULT 0,
    meta_hide_children_in_menu BIT               DEFAULT 0,
    meta_hide_in_breadcrumb    BIT               DEFAULT 0,
    meta_hide_in_tab           BIT               DEFAULT 0,

    remark                     NVARCHAR(MAX),

    CONSTRAINT chk_menu_type CHECK (
        type IN ('catalog', 'menu', 'button', 'embedded', 'link')
        )
);

CREATE INDEX idx_menu_tenant ON sys_menu (tenant_id);
CREATE INDEX idx_menu_pid ON sys_menu (pid);
CREATE INDEX idx_menu_status ON sys_menu (status);


-- =====================================================
-- 角色菜单关联表
-- =====================================================
CREATE TABLE sys_role_menu
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT    NOT NULL,
    deleted     BIT   NOT NULL DEFAULT 0,

    create_by   BIGINT,
    create_time DATETIME NOT NULL ,
    update_by   BIGINT,
    update_time DATETIME,
    version     INT   NOT NULL DEFAULT 0,

    role_id     BIGINT    NOT NULL,
    menu_id     BIGINT    NOT NULL
);

CREATE INDEX idx_role_menu_role ON sys_role_menu (role_id);
CREATE INDEX idx_role_menu_menu ON sys_role_menu (menu_id);

-- 为 sys_role_menu 表创建基于 id 的唯一索引
CREATE UNIQUE INDEX uk_role_menu_id
    ON sys_role_menu (id);

-- 为 sys_menu 表创建基于 id 的唯一索引
CREATE UNIQUE INDEX uk_menu_id
    ON sys_menu (id);

-- 为 sys_user_role 表创建基于 id 的唯一索引
CREATE UNIQUE INDEX uk_user_role_id
    ON sys_user_role (id);

-- 为 sys_user 表创建基于 id 的唯一索引
CREATE UNIQUE INDEX uk_user_id
    ON sys_user (id);

-- 为 sys_role 表创建基于 id 的唯一索引
CREATE UNIQUE INDEX uk_role_id
    ON sys_role (id);

-- 为 sys_dept 表创建基于 id 的唯一索引
CREATE UNIQUE INDEX uk_dept_id
    ON sys_dept (id);