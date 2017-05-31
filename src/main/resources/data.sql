prompt PL/SQL Developer import file
prompt Created on 2017年5月31日 by KR
set feedback off
set define off
prompt Dropping GOODS...
drop table GOODS cascade constraints;
prompt Dropping SYS_DEPT...
drop table SYS_DEPT cascade constraints;
prompt Dropping SYS_DICT...
drop table SYS_DICT cascade constraints;
prompt Dropping SYS_MENU...
drop table SYS_MENU cascade constraints;
prompt Dropping SYS_ROLE...
drop table SYS_ROLE cascade constraints;
prompt Dropping SYS_ROLE_MENU...
drop table SYS_ROLE_MENU cascade constraints;
prompt Dropping SYS_USER...
drop table SYS_USER cascade constraints;
prompt Dropping SYS_USER_ROLE...
drop table SYS_USER_ROLE cascade constraints;
prompt Creating GOODS...
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

prompt Creating SYS_DEPT...
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

prompt Creating SYS_DICT...
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

prompt Creating SYS_MENU...
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

prompt Creating SYS_ROLE...
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

prompt Creating SYS_ROLE_MENU...
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

prompt Creating SYS_USER...
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

prompt Creating SYS_USER_ROLE...
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

prompt Disabling triggers for GOODS...
alter table GOODS disable all triggers;
prompt Disabling triggers for SYS_DEPT...
alter table SYS_DEPT disable all triggers;
prompt Disabling triggers for SYS_DICT...
alter table SYS_DICT disable all triggers;
prompt Disabling triggers for SYS_MENU...
alter table SYS_MENU disable all triggers;
prompt Disabling triggers for SYS_ROLE...
alter table SYS_ROLE disable all triggers;
prompt Disabling triggers for SYS_ROLE_MENU...
alter table SYS_ROLE_MENU disable all triggers;
prompt Disabling triggers for SYS_USER...
alter table SYS_USER disable all triggers;
prompt Disabling triggers for SYS_USER_ROLE...
alter table SYS_USER_ROLE disable all triggers;
prompt Loading GOODS...
insert into GOODS (id, name, create_time, user_id, price, type, status, img_url)
values (2, '6plus', to_timestamp('31-05-2017 11:25:01.113000', 'dd-mm-yyyy hh24:mi:ss.ff'), 26, 6750, '1', '1', '/2017\05\31\201705311124559425.jpg');
commit;
prompt 1 records loaded
prompt Loading SYS_DEPT...
insert into SYS_DEPT (id, name, parent_id, remark, create_time, order_by)
values (1, '高速公路联网公司', null, null, null, 1);
insert into SYS_DEPT (id, name, parent_id, remark, create_time, order_by)
values (19, '人事部', 1, '联网公司-人事部', to_date('04-03-2017 16:52:50', 'dd-mm-yyyy hh24:mi:ss'), 1);
commit;
prompt 2 records loaded
prompt Loading SYS_DICT...
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (80, 'goods_type', '1', '极速专区', '商品类型-极速专区', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (33, 'user_status', '0', '可用', '用户状态-可用', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (34, 'user_status', '1', '禁用', '用户状态-禁用', 2);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (16, 'gender', '0', '男', '性别-男', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (17, 'gender', '1', '女', '性别-女', 2);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (26, 'user_type', '1', '移动端', '用户-移动端', 2);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (29, 'menu_type', '0', '根目录', '菜单类型-跟目录', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (25, 'user_type', '0', 'PC端', '用户-PC端', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (30, 'menu_type', '1', '一级菜单', '菜单类型-一级菜单', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (31, 'menu_type', '2', '二级菜单', '菜单类型-二级菜单', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (32, 'menu_type', '3', '按钮', '菜单类型-按钮', 1);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (78, 'goods_status', '2', '已发布', '商品状态-已发布', 2);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (79, 'goods_status', '3', '已下架', '商品状态-已下架', 3);
insert into SYS_DICT (id, dict_group, dict_code, dict_name, dict_desp, order_by)
values (77, 'goods_status', '1', '初始化', '商品状态-初始化', 1);
commit;
prompt 14 records loaded
prompt Loading SYS_MENU...
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (121, '新增商品', '3', 'addGoods()', null, 'corn:goods:add', 119, null, to_date('31-05-2017 15:27:08', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (122, '修改商品', '3', 'editGoods()', null, 'corn:goods:edit', 119, null, to_date('31-05-2017 15:28:16', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (123, '删除商品', '3', 'delGoods()', null, 'corn:goods:del', 119, null, to_date('31-05-2017 15:29:23', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (124, '保存商品', '3', 'saveGoods()', null, 'corn:goods:save', 119, null, to_date('31-05-2017 15:36:48', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (119, '商品管理', '2', '/corn/goods', null, 'corn:goods:list', 47, null, to_date('26-05-2017 11:04:38', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (47, '商品管理', '1', null, 'fa fa-home', null, 1, null, to_date('04-03-2017 14:28:00', 'dd-mm-yyyy hh24:mi:ss'), 20);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (25, '新增菜单', '3', 'addMenu()', null, 'sys:menu:add', 21, null, to_date('07-03-2017 15:59:27', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (27, '修改菜单', '3', 'updateMenu()', null, 'sys:menu:edit', 21, null, to_date('07-03-2017 16:02:03', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (28, '删除菜单', '3', 'delMenu()', null, 'sys:menu:del', 21, null, to_date('07-03-2017 16:02:28', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (21, '菜单管理', '2', '/admin/sysMenu?leftMenu=21', null, 'sys:menu:list', 15, null, to_date('06-03-2017 18:22:34', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (22, '数据字典', '2', '/admin/sysDict?leftMenu=22', null, 'sys:dict:list', 15, null, to_date('06-03-2017 18:23:09', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (23, '角色管理', '2', '/admin/sysRole?leftMenu=23', null, 'sys:role:list', 15, null, to_date('06-03-2017 18:23:52', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (24, '组织机构管理', '2', '/admin/sysDept?leftMenu=24', null, 'sys:dept:list', 15, null, to_date('06-03-2017 18:24:21', 'dd-mm-yyyy hh24:mi:ss'), 5);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (1, '根目录', '0', null, null, null, null, null, null, null);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (15, '系统管理', '1', null, 'fa fa-home', null, 1, null, to_date('04-03-2017 14:28:00', 'dd-mm-yyyy hh24:mi:ss'), 10);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (43, '新增用户', '3', 'addUser()', null, 'sys:user:add', 18, null, to_date('07-03-2017 17:14:00', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (18, '用户管理', '2', '/admin/sysUser?leftMenu=18', null, 'sys:user:list', 15, null, to_date('04-03-2017 15:02:58', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (29, '保存菜单', '4', '/admin/sysMenu/saveMenu', null, 'sys:menu:save', 21, null, to_date('07-03-2017 16:55:28', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (31, '新增数据字典', '3', 'addDict()', null, 'sys:dict:add', 22, null, to_date('07-03-2017 17:03:52', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (32, '修改数据字典', '3', 'updateDict()', null, 'sys:dict:edit', 22, null, to_date('07-03-2017 17:05:02', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (33, '删除数据字典', '3', 'delDict()', null, 'sys:dict:del', 22, null, to_date('07-03-2017 17:05:43', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (34, '保存数据字典', '4', '/admin/sysDict/saveDict', null, 'sys:dict:save', 22, null, to_date('07-03-2017 17:06:48', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (35, '新增角色', '3', 'addRole()', null, 'sys:role:add', 23, null, to_date('07-03-2017 17:07:53', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (36, '修改角色', '3', 'updateRole()', null, 'sys:role:edit', 23, null, to_date('07-03-2017 17:08:55', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (37, '删除角色', '3', 'delRole()', null, 'sys:role:del', 23, null, to_date('07-03-2017 17:09:33', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (38, '保存角色', '4', '/admin/sysRole/saveRole', null, 'sys:role:save', 23, null, to_date('07-03-2017 17:10:15', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (39, '新增部门', '3', 'addDept()', null, 'sys:dept:add', 24, null, to_date('07-03-2017 17:10:53', 'dd-mm-yyyy hh24:mi:ss'), 1);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (40, '修改部门', '3', 'updateDept()', null, 'sys:dept:edit', 24, null, to_date('07-03-2017 17:11:47', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (41, '删除部门', '3', 'delDept()', null, 'sys:dept:del', 24, null, to_date('07-03-2017 17:12:21', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (42, '保存部门', '4', '/admin/sysDept/saveDept', null, 'sys:dept:save', 24, null, to_date('07-03-2017 17:13:11', 'dd-mm-yyyy hh24:mi:ss'), 4);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (44, '修改用户', '3', 'updateUser()', null, 'sys:user:edit', 18, null, to_date('07-03-2017 17:14:40', 'dd-mm-yyyy hh24:mi:ss'), 2);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (45, '删除用户', '3', 'delUser()', null, 'sys:user:del', 18, null, to_date('07-03-2017 17:15:35', 'dd-mm-yyyy hh24:mi:ss'), 3);
insert into SYS_MENU (id, name, type, url, icon, permissions, parent_id, remark, create_time, order_by)
values (46, '保存用户', '4', '/admin/sysUser/saveUser', null, 'sys:user:save', 18, null, to_date('07-03-2017 17:16:14', 'dd-mm-yyyy hh24:mi:ss'), 4);
commit;
prompt 33 records loaded
prompt Loading SYS_ROLE...
insert into SYS_ROLE (id, name, create_time, remark)
values (11, '基本用户', to_timestamp('07-03-2017 14:19:49.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '基本用户');
insert into SYS_ROLE (id, name, create_time, remark)
values (6, '系统管理员', to_timestamp('06-03-2017 17:34:45.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), '拥有全部权利');
commit;
prompt 2 records loaded
prompt Loading SYS_ROLE_MENU...
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (61, 9, 1);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (62, 9, 15);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (63, 9, 18);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (57, 10, 1);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (58, 10, 15);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (59, 10, 18);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (60, 10, 17);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (73, 11, 22);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (74, 11, 24);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1123, 13, 1);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1124, 13, 15);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1125, 13, 21);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1126, 13, 25);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1127, 13, 27);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1128, 13, 28);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1129, 13, 29);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1363, 6, 1);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1364, 6, 47);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1365, 6, 119);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1366, 6, 121);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1367, 6, 122);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1368, 6, 123);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1369, 6, 124);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1370, 6, 15);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1371, 6, 21);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1372, 6, 25);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1373, 6, 27);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1374, 6, 28);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1375, 6, 29);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1376, 6, 22);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1377, 6, 31);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1378, 6, 32);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1379, 6, 33);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1380, 6, 34);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1381, 6, 23);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1382, 6, 35);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1383, 6, 36);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1384, 6, 37);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1385, 6, 38);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1386, 6, 24);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1387, 6, 39);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1388, 6, 40);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1389, 6, 41);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1390, 6, 42);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1391, 6, 18);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1392, 6, 43);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1393, 6, 44);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1394, 6, 45);
insert into SYS_ROLE_MENU (id, role_id, menu_id)
values (1395, 6, 46);
commit;
prompt 49 records loaded
prompt Loading SYS_USER...
insert into SYS_USER (id, real_name, salt, gender, password, phone_no, email, create_time, last_login_date, user_name, wechat_no, open_id, wechat_attention_status, dept_id, ext1, ext2, ext3, status)
values (26, '张三', null, '0', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '18703676954', null, to_timestamp('07-03-2017 15:54:14.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, 'admin', '123', null, null, 19, null, null, null, '0');
insert into SYS_USER (id, real_name, salt, gender, password, phone_no, email, create_time, last_login_date, user_name, wechat_no, open_id, wechat_attention_status, dept_id, ext1, ext2, ext3, status)
values (34, '11111', null, '0', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', '18736000723', null, to_timestamp('04-05-2017 15:27:55.000000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, '1111', null, null, null, null, null, null, null, '0');
commit;
prompt 2 records loaded
prompt Loading SYS_USER_ROLE...
insert into SYS_USER_ROLE (id, role_id, user_id)
values (7, 6, 25);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (8, 11, 25);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (14, 11, 27);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (5, 6, 23);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (6, 11, 23);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (3, 6, 21);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (4, 11, 21);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (11, 6, 26);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (12, 11, 26);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (15, 6, 33);
insert into SYS_USER_ROLE (id, role_id, user_id)
values (16, 6, 34);
commit;
prompt 11 records loaded
prompt Enabling triggers for GOODS...
alter table GOODS enable all triggers;
prompt Enabling triggers for SYS_DEPT...
alter table SYS_DEPT enable all triggers;
prompt Enabling triggers for SYS_DICT...
alter table SYS_DICT enable all triggers;
prompt Enabling triggers for SYS_MENU...
alter table SYS_MENU enable all triggers;
prompt Enabling triggers for SYS_ROLE...
alter table SYS_ROLE enable all triggers;
prompt Enabling triggers for SYS_ROLE_MENU...
alter table SYS_ROLE_MENU enable all triggers;
prompt Enabling triggers for SYS_USER...
alter table SYS_USER enable all triggers;
prompt Enabling triggers for SYS_USER_ROLE...
alter table SYS_USER_ROLE enable all triggers;
set feedback on
set define on
prompt Done.
