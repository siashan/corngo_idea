------------------------------------------
-- Export file for user CORNGO          --
-- Created by KR on 2017/5/31, 17:08:41 --
------------------------------------------

set define off
spool obj.log

prompt
prompt Creating table GOODS
prompt ====================
prompt
create table GOODS
(
  id          NUMBER(18) not null,
  name        VARCHAR2(300),
  create_time TIMESTAMP(6),
  user_id     NUMBER(18),
  price       NUMBER,
  type        VARCHAR2(30),
  status      VARCHAR2(30),
  img_url     VARCHAR2(500)
)
;
comment on table GOODS
  is '商品表';
comment on column GOODS.id
  is 'ID';
comment on column GOODS.name
  is '商品名称';
comment on column GOODS.create_time
  is '创建时间';
comment on column GOODS.user_id
  is '发布人';
comment on column GOODS.price
  is '价格';
comment on column GOODS.type
  is '类型';
comment on column GOODS.status
  is '状态';
comment on column GOODS.img_url
  is '图片存放地址';
alter table GOODS
  add constraint PK_GOODS_ID primary key (ID);

prompt
prompt Creating table SYS_DEPT
prompt =======================
prompt
create table SYS_DEPT
(
  id          NUMBER(18) not null,
  name        VARCHAR2(100),
  parent_id   NUMBER(18),
  remark      VARCHAR2(100),
  create_time DATE,
  order_by    NUMBER(2)
)
;
comment on column SYS_DEPT.id
  is 'id';
comment on column SYS_DEPT.name
  is '组织机构名称';
comment on column SYS_DEPT.parent_id
  is '父id';
comment on column SYS_DEPT.remark
  is '备注';
comment on column SYS_DEPT.create_time
  is '创建时间';
comment on column SYS_DEPT.order_by
  is '排序字段';
alter table SYS_DEPT
  add constraint PK_SYS_DEPT_ID primary key (ID);

prompt
prompt Creating table SYS_DICT
prompt =======================
prompt
create table SYS_DICT
(
  id         NUMBER(18) not null,
  dict_group VARCHAR2(60),
  dict_code  VARCHAR2(60),
  dict_name  VARCHAR2(100),
  dict_desp  VARCHAR2(100),
  order_by   NUMBER(3)
)
;
comment on column SYS_DICT.id
  is 'id';
comment on column SYS_DICT.dict_group
  is '组别';
comment on column SYS_DICT.dict_code
  is 'code值';
comment on column SYS_DICT.dict_name
  is '名称';
comment on column SYS_DICT.dict_desp
  is '描述';
comment on column SYS_DICT.order_by
  is '排序';
alter table SYS_DICT
  add constraint PK_SYS_DICT_ID primary key (ID);

prompt
prompt Creating table SYS_MENU
prompt =======================
prompt
create table SYS_MENU
(
  id          NUMBER(18) not null,
  name        VARCHAR2(30),
  type        VARCHAR2(2),
  url         VARCHAR2(300),
  icon        VARCHAR2(30),
  permissions VARCHAR2(100),
  parent_id   NUMBER(18),
  remark      VARCHAR2(100),
  create_time DATE,
  order_by    NUMBER(2)
)
;
comment on column SYS_MENU.id
  is 'id';
comment on column SYS_MENU.name
  is '菜单名称';
comment on column SYS_MENU.type
  is '菜单类型';
comment on column SYS_MENU.url
  is '菜单url';
comment on column SYS_MENU.icon
  is '图标';
comment on column SYS_MENU.permissions
  is '权限';
comment on column SYS_MENU.parent_id
  is '父id';
comment on column SYS_MENU.remark
  is '备注';
comment on column SYS_MENU.create_time
  is '创建时间';
comment on column SYS_MENU.order_by
  is '排序字段';
alter table SYS_MENU
  add constraint PK_SYS_MENU_ID primary key (ID);

prompt
prompt Creating table SYS_ROLE
prompt =======================
prompt
create table SYS_ROLE
(
  id          NUMBER(18) not null,
  name        VARCHAR2(100) not null,
  create_time TIMESTAMP(6) default sysdate,
  remark      VARCHAR2(100)
)
;
comment on column SYS_ROLE.id
  is '主键 自增';
comment on column SYS_ROLE.name
  is '角色名称';
comment on column SYS_ROLE.create_time
  is '创建时间';
comment on column SYS_ROLE.remark
  is '备注';
alter table SYS_ROLE
  add constraint SYS_ROLE_ID primary key (ID);

prompt
prompt Creating table SYS_ROLE_MENU
prompt ============================
prompt
create table SYS_ROLE_MENU
(
  id      NUMBER(18) not null,
  role_id NUMBER(18),
  menu_id NUMBER(18)
)
;
comment on column SYS_ROLE_MENU.id
  is '主键 自增';
comment on column SYS_ROLE_MENU.role_id
  is '角色id';
comment on column SYS_ROLE_MENU.menu_id
  is '菜单id';
alter table SYS_ROLE_MENU
  add constraint SYS_ROLE_MENU_ID primary key (ID);

prompt
prompt Creating table SYS_USER
prompt =======================
prompt
create table SYS_USER
(
  id                      NUMBER(18) not null,
  real_name               VARCHAR2(10),
  salt                    VARCHAR2(12),
  gender                  VARCHAR2(2),
  password                VARCHAR2(100),
  phone_no                VARCHAR2(50),
  email                   VARCHAR2(100),
  create_time             TIMESTAMP(6) default sysdate,
  last_login_date         TIMESTAMP(6),
  user_name               VARCHAR2(30),
  wechat_no               VARCHAR2(100),
  open_id                 VARCHAR2(200),
  wechat_attention_status VARCHAR2(2),
  dept_id                 NUMBER(18),
  ext1                    VARCHAR2(10),
  ext2                    VARCHAR2(10),
  ext3                    VARCHAR2(10),
  status                  VARCHAR2(2)
)
;
comment on column SYS_USER.id
  is '主键 自增';
comment on column SYS_USER.real_name
  is '真实姓名';
comment on column SYS_USER.salt
  is '加密盐';
comment on column SYS_USER.gender
  is '性别';
comment on column SYS_USER.password
  is '登录密码';
comment on column SYS_USER.phone_no
  is '联系电话';
comment on column SYS_USER.email
  is '邮箱';
comment on column SYS_USER.create_time
  is '创建日期';
comment on column SYS_USER.last_login_date
  is '最近登录时间';
comment on column SYS_USER.user_name
  is '用户名';
comment on column SYS_USER.wechat_no
  is '微信号';
comment on column SYS_USER.open_id
  is 'openID';
comment on column SYS_USER.wechat_attention_status
  is '微信关注状态';
comment on column SYS_USER.dept_id
  is '所属部门id';
alter table SYS_USER
  add constraint ID primary key (ID);

prompt
prompt Creating table SYS_USER_ROLE
prompt ============================
prompt
create table SYS_USER_ROLE
(
  id      NUMBER(18) not null,
  role_id NUMBER(18),
  user_id NUMBER(18)
)
;
comment on column SYS_USER_ROLE.id
  is '主键 自增';
comment on column SYS_USER_ROLE.role_id
  is '角色id';
comment on column SYS_USER_ROLE.user_id
  is '用户id';
alter table SYS_USER_ROLE
  add constraint SYS_USER_ROLE_ID primary key (ID);

prompt
prompt Creating sequence SEQ_GOODS
prompt ===========================
prompt
create sequence SEQ_GOODS
minvalue 1
maxvalue 999999999999999999999999999
start with 5
increment by 1
cache 2;

prompt
prompt Creating sequence SEQ_SYS_DEPT
prompt ==============================
prompt
create sequence SEQ_SYS_DEPT
minvalue 1
maxvalue 999999999999999999999999999
start with 27
increment by 1
cache 2;

prompt
prompt Creating sequence SEQ_SYS_DICT
prompt ==============================
prompt
create sequence SEQ_SYS_DICT
minvalue 1
maxvalue 999999999999999999999999999
start with 81
increment by 1
cache 2;

prompt
prompt Creating sequence SEQ_SYS_MENU
prompt ==============================
prompt
create sequence SEQ_SYS_MENU
minvalue 1
maxvalue 999999999999999999999999999
start with 125
increment by 1
cache 2;

prompt
prompt Creating sequence SEQ_SYS_ROLE
prompt ==============================
prompt
create sequence SEQ_SYS_ROLE
minvalue 1
maxvalue 999999999999999999999999999
start with 15
increment by 1
cache 2;

prompt
prompt Creating sequence SEQ_SYS_ROLE_MENU
prompt ===================================
prompt
create sequence SEQ_SYS_ROLE_MENU
minvalue 1
maxvalue 999999999999999999999999999
start with 1397
increment by 1
cache 2;

prompt
prompt Creating sequence SEQ_SYS_USER
prompt ==============================
prompt
create sequence SEQ_SYS_USER
minvalue 1
maxvalue 999999999999999999999999999
start with 35
increment by 1
cache 2;

prompt
prompt Creating sequence SEQ_SYS_USER_ROLE
prompt ===================================
prompt
create sequence SEQ_SYS_USER_ROLE
minvalue 1
maxvalue 999999999999999999999999999
start with 17
increment by 1
cache 2;


spool off
