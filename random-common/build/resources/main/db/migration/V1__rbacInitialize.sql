CREATE TABLE rbac_menu (
  menu_id BIGINT NOT NULL,
  create_date DATE,
  creator BIGINT,
  isvalid VARCHAR (1),
  commonts VARCHAR(100),
  menu_icon VARCHAR (100),
  nemu_name VARCHAR (30),
  menu_url VARCHAR (100),
  upper_menu BIGINT,
  role_id BIGINT,
  PRIMARY KEY (menu_id)
);
CREATE INDEX idx_menu_role ON rbac_menu(role_id);

CREATE TABLE rbac_permission (
  ps_id BIGINT NOT NULL,
  create_date DATE,
  creator BIGINT,
  isvalid VARCHAR (1),
  commonts VARCHAR(100),
  ps_description VARCHAR (100),
  ps_group VARCHAR (30),
  ps_name VARCHAR (30),
  ps_url VARCHAR (100),
  role_id BIGINT,
  ps_type VARCHAR (2),
  PRIMARY KEY (ps_id)
) ;
CREATE INDEX idx_ps_role ON rbac_permission(role_id);

CREATE TABLE rbac_role (
  role_id BIGINT NOT NULL,
  create_date DATE,
  creator BIGINT,
  isvalid VARCHAR (1),
  commonts VARCHAR(100),
  main_page VARCHAR (100),
  role_description VARCHAR (100),
  role_name VARCHAR (30),
  upper_role_id BIGINT,
  PRIMARY KEY (role_id)
);
CREATE TABLE rbac_user (
  user_id BIGINT NOT NULL,
  real_name VARCHAR (30),
  tel VARCHAR (20),
  user_name VARCHAR (30),
  user_pwd VARCHAR (60),
  validate_date DATE,
  create_date DATE,
  creator BIGINT,
  isvalid VARCHAR (1),
  commonts VARCHAR(100),
  activated boolean,
  PRIMARY KEY (user_id)
);
CREATE TABLE rbac_user_role (
  rid BIGINT NOT NULL,
  create_date DATE,
  creator BIGINT,
  isvalid VARCHAR (1),
  commonts VARCHAR(100),
  role_id BIGINT,
  user_id BIGINT,
  PRIMARY KEY (rid)
);
CREATE INDEX idx_ur_u ON rbac_user_role(user_id);
CREATE INDEX idx_ur_r ON rbac_user_role(role_id);