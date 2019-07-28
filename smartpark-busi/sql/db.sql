/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/11/4 16:14:34                           */
/*==============================================================*/

drop table if exists sp_car_info;

drop table if exists sp_companions;

drop table if exists sp_visitor_info;

drop table if exists sp_visitor_info_his;

drop table if exists sp_visitor_reservation;

/*==============================================================*/
/* Table: sp_car_info                                           */
/*==============================================================*/
create table sp_car_info
(
   id                   bigint not null auto_increment comment 'id',
   car_no               varchar(10) comment '车牌号',
   name                 varchar(20) comment '驾驶员姓名',
   phone                varchar(20) comment '手机号',
   reservation_id       bigint comment '预约单id',
   ext1                 varchar(50) comment '扩展字段1',
   ext2                 varchar(50) comment '扩展字段2',
   ext3                 varchar(50) comment '扩展字段3',
   create_time          datetime comment '创建时间',
   create_user_id       bigint comment '创建人id',
   update_time          datetime comment '更新时间',
   update_user_id       bigint comment '更新人id',
   remark               varchar(200) comment '备注',
   primary key (id)
);

alter table sp_car_info comment '车辆信息表';

/*==============================================================*/
/* Table: sp_companions                                         */
/*==============================================================*/
create table sp_companions
(
   id                   bigint not null auto_increment,
   reservation_id       int(10) comment '预约单id',
   name                 varchar(20) comment '姓名',
   physical_card_id     varchar(32),
   idcard_no            varchar(20) comment '身份证号',
   phone                varchar(20) comment '手机号',
   photo_url            varchar(128) comment '访客照片',
   ext1                 varchar(50) comment '扩展字段1',
   ext2                 varchar(50) comment '扩展字段2',
   ext3                 varchar(50) comment '扩展字段3',
   create_time          datetime comment '创建时间',
   create_user_id       bigint comment '创建人id',
   update_time          datetime comment '更新时间',
   update_user_id       bigint comment '更新人id',
   primary key (id)
);

alter table sp_companions comment '同行人员信息表';

/*==============================================================*/
/* Table: sp_visitor_info                                       */
/*==============================================================*/
create table sp_visitor_info
(
   id                   bigint not null auto_increment comment '访客id',
   name                 varchar(20) comment '姓名',
   physical_card_id     varchar(32) comment '物理卡id',
   idcard_no            varchar(20) comment '身份证号',
   phone                varchar(20) comment '手机号',
   type                 tinyint(4) comment '访客类型',
   photo_url            varchar(128) comment '访客照片',
   company              varchar(200) comment '单位名称',
   remark               varchar(200) comment '备注',
   ext1                 varchar(50) comment '扩展字段1',
   ext2                 varchar(50) comment '扩展字段2',
   ext3                 varchar(50) comment '扩展字段3',
   create_time          datetime comment '创建时间',
   create_user_id       bigint comment '创建人id',
   update_time          datetime comment '更新时间',
   update_user_id       bigint comment '更新人id',
   primary key (id)
);

alter table sp_visitor_info comment '访客信息表';

/*==============================================================*/
/* Table: sp_visitor_info_his                                   */
/*==============================================================*/
create table sp_visitor_info_his
(
   id                   bigint not null auto_increment,
   visitor_id           bigint comment '访客id',
   name                 varchar(20) comment '姓名',
   physical_card_id     varchar(32),
   idcard_no            varchar(20) comment '身份证号',
   phone                varchar(20) comment '手机号',
   type                 tinyint(4) comment '访客类型',
   photo_url            varchar(128) comment '访客照片',
   company              varchar(200) comment '单位名称',
   ext1                 varchar(50) comment '扩展字段1',
   ext2                 varchar(50) comment '扩展字段2',
   ext3                 varchar(50) comment '扩展字段3',
   create_time          datetime comment '创建时间',
   create_user_id       bigint comment '创建人id',
   update_time          datetime comment '更新时间',
   update_user_id       bigint comment '更新人id',
   remark               varchar(200) comment '备注',
   reservation_id       bigint(20) DEFAULT NULL COMMENT '预约单ID',
   primary key (id)
);

alter table sp_visitor_info_his comment '访客信息历史表';

/*==============================================================*/
/* Table: sp_visitor_reservation                                */
/*==============================================================*/
create table sp_visitor_reservation
(
   id                   bigint not null auto_increment comment '订单id',
   phone                varchar(20) comment '访客手机号',
   photo_url            varchar(128) comment '访客照片',
   visitor_id           bigint comment '访客id',
   staff_id             bigint comment '受访者id',
   staff_mobile         varchar(20) DEFAULT NULL COMMENT '被访人（员工）手机号',
   status               varchar(12) comment '订单状态',
   appoint_start_time   datetime comment '预约开始时间',
   appoint_end_time     datetime comment '预约结束时间',
   act_start_time       datetime comment '实际开始时间',
   act_end_time         datetime comment '实际结束时间',
   type                 varchar(12) comment '预约单类型',
   remark               varchar(200) comment '备注(来访目的)',
   companions           int(10) comment '同行人数',
   ext1                 varchar(50) comment '扩展字段1',
   ext2                 varchar(50) comment '扩展字段2',
   ext3                 varchar(50) comment '扩展字段3',
   create_time          datetime comment '创建时间',
   create_user_id       bigint comment '创建人id',
   update_time          datetime comment '更新时间',
   update_user_id       bigint comment '更新人id',
   primary key (id)
);

alter table sp_visitor_reservation comment '访客预约登记单';

drop table if exists sp_group;
CREATE TABLE `sp_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '群组ID',
  `name` varchar(50) NOT NULL COMMENT '群组名称',
  `group_no` varchar(20) DEFAULT NULL COMMENT '群组编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='群组表';

drop table if exists sp_group_door;
CREATE TABLE `sp_group_door` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '群组id',
  `door_id` bigint(20) DEFAULT NULL COMMENT '门id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='群组门表';

drop table if exists sp_group_station;
CREATE TABLE `sp_group_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint(20) DEFAULT NULL COMMENT '群组id',
  `station_id` bigint(20) DEFAULT NULL COMMENT '工位id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='群组工位表';


drop table if EXISTS sp_door_controller;

CREATE TABLE `sp_door_controller` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '门禁控制器id',
  `controller_name` varchar(50) DEFAULT NULL COMMENT '控制器名称',
  `controller_no` varchar(50) DEFAULT NULL COMMENT '控制器编号',
  `controller_ip` varchar(20) DEFAULT NULL COMMENT '控制器IP',
  `mac_addr` varchar(20) DEFAULT NULL COMMENT '控制器MAC地址',
  `controller_port` varchar(10) DEFAULT NULL COMMENT '控制器端口',
  `status` char(1) DEFAULT '1' COMMENT '状态 0 废弃 1正常',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='门禁控制器表';

drop table if EXISTS sp_door_readno;

CREATE TABLE `sp_door_readno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `read_no` tinyint(5) DEFAULT NULL COMMENT '读头号',
  `controller_id` bigint(20) DEFAULT NULL COMMENT '门禁控制器ID',
  `status` char(1) DEFAULT '1' COMMENT '状态 0 无效 1有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门读头信息';

drop table if EXISTS sp_door;

CREATE TABLE `sp_door` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `door_name` varchar(50) DEFAULT NULL COMMENT '门名称',
  `door_no` varchar(20) DEFAULT NULL COMMENT '门编号',
  `door_type` varchar(20) DEFAULT NULL COMMENT '门类型 1通道门 2房间门',
  `room_id` bigint(20) DEFAULT NULL COMMENT '所属房间',
  `floor_id` bigint(20) DEFAULT NULL COMMENT '所属楼层',
  `company_id` bigint(20) DEFAULT NULL COMMENT '所属公司，对应base_organization表ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门，对应base_organization表ID',
  `status` char(1) DEFAULT '1' COMMENT '状态 0 废弃 1正常',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `ext1` varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(100) DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(200) DEFAULT NULL COMMENT '扩展字段3',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
  `door_readno_id` bigint(20) DEFAULT NULL COMMENT '门读头ID，对应sp_door_readno表ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='门定义';


create table sp_access_record
(
   id                   bigint not null auto_increment comment 'id',
   car_no               varchar(10) comment '车牌号',
	 user_id						bigint(20) comment '用户ID',
   user_name                 varchar(20) comment '姓名',
	 owner_type						tinyint(1) comment '所属人类别，1：内部员工，2：外部员工 3：外包 4：访客',
   mobile                varchar(20) comment '手机号',
	 dept_name						varchar(20) comment '部门',
	 identity_type        tinyint(1) comment '标识类型，1：工号 2：身份证',
	 identity_value       varchar(30) comment 'identity_type=1，则为工号值，identity_type＝2，则为身份证号码',
	 capture_img          varchar(255) comment '抓拍图片',
   access_time          datetime comment '出入时间',
	 access_type					tinyint(1) comment '出入类别，1：进 0：出',
	 channel_id						bigint(20) comment '通道ID',
	 channel_name					varchar(30) comment '通道名称',
   ext1                 varchar(50) comment '扩展字段1',
   ext2                 varchar(50) comment '扩展字段2',
   ext3                 varchar(50) comment '扩展字段3',
   create_time          datetime comment '创建时间',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出入记录表';

create table sp_work_order
(
   id                   bigint not null auto_increment comment 'id',
   title                varchar(100) comment '标题',
   content              text comment '工单内容',
	 work_order_type			int(10) comment '工单类型',
	 status			          int(10) comment '状态',
   ext1                 varchar(50) comment '扩展字段1',
   ext2                 varchar(50) comment '扩展字段2',
   ext3                 varchar(50) comment '扩展字段3',
   create_time          datetime comment '创建时间',
	 `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人',
   `update_time` datetime DEFAULT NULL COMMENT '修改时间',
   `update_user_id` bigint(20) DEFAULT NULL COMMENT '修改人',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工单表';