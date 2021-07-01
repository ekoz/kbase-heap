CREATE DATABASE `ibothub-auth` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE `ibothub-auth`.`auth_user` (
	id BIGINT auto_increment NOT NULL primary key,
	username varchar(100) NULL COMMENT '账号',
	username_cn varchar(100) NULL COMMENT '用户名',
	password varchar(100) NULL COMMENT '密码',
	job_number varchar(100) NULL COMMENT '工号',
	sex int NULL COMMENT '性别，1-男；0-女',
	email varchar(200) NULL COMMENT '邮箱',
	phone varchar(100) NULL COMMENT '电话号码',
	birthday DATETIME NULL COMMENT '生日',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
	del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE `ibothub-auth`.`auth_dept` (
	id BIGINT auto_increment NOT NULL primary key,
	name varchar(200) NULL COMMENT '部门名称',
	parent_id BIGINT NULL COMMENT '上级部门',
	key_ varchar(200) NULL COMMENT '部门标识',
	remark varchar(1000) NULL COMMENT '备注',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
    del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE `ibothub-auth`.`auth_role` (
	id BIGINT auto_increment NOT NULL primary key,
	name varchar(200) NULL COMMENT '名称',
	key_ varchar(200) NULL COMMENT '标识',
	remark varchar(1000) NULL COMMENT '备注',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
    del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE `ibothub-auth`.`auth_permission` (
	id BIGINT auto_increment NOT NULL primary key,
	name varchar(200) NULL COMMENT '名称',
	key_ varchar(200) NULL COMMENT '标识',
	alias varchar(200) NULL COMMENT '路径',
    icon_ varchar(200) NULL COMMENT '图标',
    parent_id BIGINT NULL COMMENT '上级权限id',
    order_ INT NULL COMMENT '排序',
	remark varchar(1000) NULL COMMENT '备注',
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	creator varchar(100) NULL COMMENT '创建人',
	modify_time DATETIME DEFAULT CURRENT_TIMESTAMP() NOT NULL COMMENT '创建日期',
	modifier varchar(100) NULL COMMENT '修改人',
    del_flag INT DEFAULT 0 NOT NULL COMMENT '删除标识，1-已删除；0-未删除'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;

CREATE TABLE `ibothub-auth`.`auth_role_permission` (
	id BIGINT auto_increment NOT NULL primary key,
	role_id BIGINT NULL COMMENT '角色id',
	permission_id BIGINT NULL COMMENT '权限id'
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;


