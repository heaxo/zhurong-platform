-- =====================================================
-- 租户表
-- =====================================================
CREATE TABLE sys_tenant
(
    id          BIGINT PRIMARY KEY,
    name        VARCHAR(200) NOT NULL,
    status      SMALLINT     NOT NULL DEFAULT 1,
    expire_time TIMESTAMP,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP,
    version     INTEGER      NOT NULL DEFAULT 0
);

CREATE INDEX idx_tenant_status ON sys_tenant (status);


-- =====================================================
-- 部门表
-- =====================================================
CREATE TABLE sys_dept
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT       NOT NULL,
    deleted     BOOLEAN      NOT NULL DEFAULT FALSE,

    create_by   BIGINT,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by   BIGINT,
    update_time TIMESTAMP,
    version     INTEGER      NOT NULL DEFAULT 0,

    pid         BIGINT,
    name        VARCHAR(100) NOT NULL,
    status      SMALLINT     NOT NULL DEFAULT 1,
    sort_order  INTEGER               DEFAULT 0,
    remark      TEXT
);

CREATE INDEX idx_dept_tenant ON sys_dept (tenant_id);
CREATE INDEX idx_dept_pid ON sys_dept (pid);
CREATE INDEX idx_dept_status ON sys_dept (status);

CREATE UNIQUE INDEX uk_dept_name
    ON sys_dept (tenant_id, name)
    WHERE deleted = FALSE;


-- =====================================================
-- 角色表
-- =====================================================
CREATE TABLE sys_role
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT       NOT NULL,
    deleted     BOOLEAN      NOT NULL DEFAULT FALSE,

    create_by   BIGINT,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by   BIGINT,
    update_time TIMESTAMP,
    version     INTEGER      NOT NULL DEFAULT 0,

    name        VARCHAR(100) NOT NULL,
    code        VARCHAR(100),
    status      SMALLINT     NOT NULL DEFAULT 1,
    data_scope  SMALLINT              DEFAULT 1,
    remark      TEXT
);

CREATE INDEX idx_role_tenant ON sys_role (tenant_id);
CREATE INDEX idx_role_status ON sys_role (status);

CREATE UNIQUE INDEX uk_role_name
    ON sys_role (tenant_id, name)
    WHERE deleted = FALSE;


-- =====================================================
-- 用户表
-- =====================================================
CREATE TABLE sys_user
(
    id              BIGINT PRIMARY KEY,
    tenant_id       BIGINT       NOT NULL,
    deleted         BOOLEAN      NOT NULL DEFAULT FALSE,

    create_by       BIGINT,
    create_time     TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by       BIGINT,
    update_time     TIMESTAMP,
    version         INTEGER      NOT NULL DEFAULT 0,

    username        VARCHAR(100) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    real_name       VARCHAR(100),
    dept_id         BIGINT,
    status          SMALLINT     NOT NULL DEFAULT 1,

    last_login_time TIMESTAMP,
    last_login_ip   VARCHAR(50),
    remark          TEXT
);

CREATE INDEX idx_user_tenant ON sys_user (tenant_id);
CREATE INDEX idx_user_dept ON sys_user (dept_id);
CREATE INDEX idx_user_status ON sys_user (status);

CREATE UNIQUE INDEX uk_user_username
    ON sys_user (tenant_id, username)
    WHERE deleted = FALSE;


-- =====================================================
-- 用户角色关联表
-- =====================================================
CREATE TABLE sys_user_role
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT    NOT NULL,
    deleted     BOOLEAN   NOT NULL DEFAULT FALSE,

    create_by   BIGINT,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by   BIGINT,
    update_time TIMESTAMP,
    version     INTEGER   NOT NULL DEFAULT 0,

    user_id     BIGINT    NOT NULL,
    role_id     BIGINT    NOT NULL
);

CREATE INDEX idx_user_role_user ON sys_user_role (user_id);
CREATE INDEX idx_user_role_role ON sys_user_role (role_id);

CREATE UNIQUE INDEX uk_user_role_unique
    ON sys_user_role (tenant_id, user_id, role_id)
    WHERE deleted = FALSE;


-- =====================================================
-- 菜单表（平台级）
-- 如果你希望菜单全局共享，可以去掉 tenant_id
-- =====================================================
CREATE TABLE sys_menu
(
    id                         BIGINT PRIMARY KEY,
    tenant_id                  BIGINT       NOT NULL,
    deleted                    BOOLEAN      NOT NULL DEFAULT FALSE,

    create_by                  BIGINT,
    create_time                TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by                  BIGINT,
    update_time                TIMESTAMP,
    version                    INTEGER      NOT NULL DEFAULT 0,

    pid                        BIGINT,
    name                       VARCHAR(100) NOT NULL,
    type                       VARCHAR(20)  NOT NULL,
    path                       VARCHAR(255),
    active_path                VARCHAR(255),
    component                  VARCHAR(255),

    auth_code                  VARCHAR(100),

    status                     SMALLINT     NOT NULL DEFAULT 1,
    sort_order                 INTEGER               DEFAULT 0,

    meta_title                 VARCHAR(200) NOT NULL,
    meta_icon                  VARCHAR(100),
    meta_active_icon           VARCHAR(100),

    meta_badge_type            VARCHAR(50),
    meta_badge                 VARCHAR(50),
    meta_badge_variants        VARCHAR(50),

    meta_iframe_src            VARCHAR(255),
    meta_link                  VARCHAR(255),

    meta_keep_alive            BOOLEAN               DEFAULT FALSE,
    meta_affix_tab             BOOLEAN               DEFAULT FALSE,
    meta_hide_in_menu          BOOLEAN               DEFAULT FALSE,
    meta_hide_children_in_menu BOOLEAN               DEFAULT FALSE,
    meta_hide_in_breadcrumb    BOOLEAN               DEFAULT FALSE,
    meta_hide_in_tab           BOOLEAN               DEFAULT FALSE,

    remark                     TEXT,

    CONSTRAINT chk_menu_type CHECK (
        type IN ('catalog', 'menu', 'button', 'embedded', 'link')
        )
);

CREATE INDEX idx_menu_tenant ON sys_menu (tenant_id);
CREATE INDEX idx_menu_pid ON sys_menu (pid);
CREATE INDEX idx_menu_status ON sys_menu (status);

CREATE UNIQUE INDEX uk_menu_name
    ON sys_menu (tenant_id, name)
    WHERE deleted = FALSE;

CREATE UNIQUE INDEX uk_menu_auth
    ON sys_menu (tenant_id, auth_code)
    WHERE deleted = FALSE AND auth_code IS NOT NULL;


-- =====================================================
-- 角色菜单关联表
-- =====================================================
CREATE TABLE sys_role_menu
(
    id          BIGINT PRIMARY KEY,
    tenant_id   BIGINT    NOT NULL,
    deleted     BOOLEAN   NOT NULL DEFAULT FALSE,

    create_by   BIGINT,
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_by   BIGINT,
    update_time TIMESTAMP,
    version     INTEGER   NOT NULL DEFAULT 0,

    role_id     BIGINT    NOT NULL,
    menu_id     BIGINT    NOT NULL
);

CREATE INDEX idx_role_menu_role ON sys_role_menu (role_id);
CREATE INDEX idx_role_menu_menu ON sys_role_menu (menu_id);

CREATE UNIQUE INDEX uk_role_menu_unique
    ON sys_role_menu (tenant_id, role_id, menu_id)
    WHERE deleted = FALSE;
