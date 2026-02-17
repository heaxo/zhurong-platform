-- =========================
-- 部门表
-- =========================
CREATE TABLE sys_dept
(
    id          UUID PRIMARY KEY,
    pid         UUID,
    name        VARCHAR(100) NOT NULL,
    status      SMALLINT     NOT NULL DEFAULT 1,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    remark      TEXT,
    CONSTRAINT chk_dept_status CHECK (status IN (0, 1)),
    CONSTRAINT fk_dept_parent
        FOREIGN KEY (pid) REFERENCES sys_dept (id) ON DELETE CASCADE
);

CREATE INDEX idx_dept_pid ON sys_dept (pid);


-- =========================
-- 角色表
-- =========================
CREATE TABLE sys_role
(
    id          UUID PRIMARY KEY,
    name        VARCHAR(100) NOT NULL UNIQUE,
    status      SMALLINT     NOT NULL DEFAULT 1,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    remark      TEXT,
    CONSTRAINT chk_role_status CHECK (status IN (0, 1))
);

CREATE INDEX idx_role_status ON sys_role (status);


-- =========================
-- 用户表
-- =========================
CREATE TABLE sys_user
(
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(100) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL,
    real_name   VARCHAR(100),
    dept_id     UUID,
    status      SMALLINT     NOT NULL DEFAULT 1,
    create_time TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT chk_user_status CHECK (status IN (0, 1)),
    CONSTRAINT fk_user_dept
        FOREIGN KEY (dept_id) REFERENCES sys_dept (id)
);

CREATE INDEX idx_user_dept ON sys_user (dept_id);


-- =========================
-- 用户角色关联
-- =========================
CREATE TABLE sys_user_role
(
    user_id BIGINT NOT NULL,
    role_id UUID   NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user
        FOREIGN KEY (user_id) REFERENCES sys_user (id) ON DELETE CASCADE,
    CONSTRAINT fk_role
        FOREIGN KEY (role_id) REFERENCES sys_role (id) ON DELETE CASCADE
);


-- =========================
-- 菜单/权限表（完整版）
-- =========================
CREATE TABLE sys_menu
(
    id                         INTEGER PRIMARY KEY,
    pid                        INTEGER               DEFAULT 0,

    name                       VARCHAR(100) NOT NULL UNIQUE,
    type                       VARCHAR(20)  NOT NULL, -- catalog/menu/button/embedded/link
    path                       VARCHAR(255) UNIQUE,
    active_path                VARCHAR(255),
    component                  VARCHAR(255),

    auth_code                  VARCHAR(100) UNIQUE,

    status                     SMALLINT     NOT NULL DEFAULT 1,

    -- meta 信息
    meta_title                 VARCHAR(200) NOT NULL,
    meta_icon                  VARCHAR(100),
    meta_active_icon           VARCHAR(100),
    meta_order                 INTEGER               DEFAULT 0,

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

    create_time                TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT chk_menu_status CHECK (status IN (0, 1)),
    CONSTRAINT chk_menu_type CHECK (
        type IN ('catalog', 'menu', 'button', 'embedded', 'link')
        )
);

CREATE INDEX idx_menu_pid ON sys_menu (pid);
CREATE INDEX idx_menu_type ON sys_menu (type);
CREATE INDEX idx_menu_status ON sys_menu (status);


-- =========================
-- 角色菜单关联
-- =========================
CREATE TABLE sys_role_menu
(
    role_id UUID    NOT NULL,
    menu_id INTEGER NOT NULL,
    PRIMARY KEY (role_id, menu_id),
    CONSTRAINT fk_role_menu_role
        FOREIGN KEY (role_id) REFERENCES sys_role (id) ON DELETE CASCADE,
    CONSTRAINT fk_role_menu_menu
        FOREIGN KEY (menu_id) REFERENCES sys_menu (id) ON DELETE CASCADE
);
