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
  is '��Ʒ��';
comment on column GOODS.id
  is 'ID';
comment on column GOODS.name
  is '��Ʒ����';
comment on column GOODS.create_time
  is '����ʱ��';
comment on column GOODS.user_id
  is '������';
comment on column GOODS.price
  is '�۸�';
comment on column GOODS.type
  is '����';
comment on column GOODS.status
  is '״̬';
comment on column GOODS.img_url
  is 'ͼƬ��ŵ�ַ';
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
  is '��֯��������';
comment on column SYS_DEPT.parent_id
  is '��id';
comment on column SYS_DEPT.remark
  is '��ע';
comment on column SYS_DEPT.create_time
  is '����ʱ��';
comment on column SYS_DEPT.order_by
  is '�����ֶ�';
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
  is '���';
comment on column SYS_DICT.dict_code
  is 'codeֵ';
comment on column SYS_DICT.dict_name
  is '����';
comment on column SYS_DICT.dict_desp
  is '����';
comment on column SYS_DICT.order_by
  is '����';
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
  is '�˵�����';
comment on column SYS_MENU.type
  is '�˵�����';
comment on column SYS_MENU.url
  is '�˵�url';
comment on column SYS_MENU.icon
  is 'ͼ��';
comment on column SYS_MENU.permissions
  is 'Ȩ��';
comment on column SYS_MENU.parent_id
  is '��id';
comment on column SYS_MENU.remark
  is '��ע';
comment on column SYS_MENU.create_time
  is '����ʱ��';
comment on column SYS_MENU.order_by
  is '�����ֶ�';
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
  is '���� ����';
comment on column SYS_ROLE.name
  is '��ɫ����';
comment on column SYS_ROLE.create_time
  is '����ʱ��';
comment on column SYS_ROLE.remark
  is '��ע';
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
  is '���� ����';
comment on column SYS_ROLE_MENU.role_id
  is '��ɫid';
comment on column SYS_ROLE_MENU.menu_id
  is '�˵�id';
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
  is '���� ����';
comment on column SYS_USER.real_name
  is '��ʵ����';
comment on column SYS_USER.salt
  is '������';
comment on column SYS_USER.gender
  is '�Ա�';
comment on column SYS_USER.password
  is '��¼����';
comment on column SYS_USER.phone_no
  is '��ϵ�绰';
comment on column SYS_USER.email
  is '����';
comment on column SYS_USER.create_time
  is '��������';
comment on column SYS_USER.last_login_date
  is '�����¼ʱ��';
comment on column SYS_USER.user_name
  is '�û���';
comment on column SYS_USER.wechat_no
  is '΢�ź�';
comment on column SYS_USER.open_id
  is 'openID';
comment on column SYS_USER.wechat_attention_status
  is '΢�Ź�ע״̬';
comment on column SYS_USER.dept_id
  is '��������id';
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
  is '���� ����';
comment on column SYS_USER_ROLE.role_id
  is '��ɫid';
comment on column SYS_USER_ROLE.user_id
  is '�û�id';
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
