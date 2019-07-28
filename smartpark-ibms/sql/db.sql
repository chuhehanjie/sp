CREATE TABLE `ibms_subsystem` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '子系统ID',
  `name` varchar(64) NOT NULL COMMENT '子系统名称',
  `remark` varchar(500) COMMENT '子系统描述',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int(10) COMMENT '创建人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` int(10) COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ibms子系统表';


DROP TABLE IF EXISTS `ibms_collection_attr`;
CREATE TABLE IF NOT EXISTS `ibms_collection_attr` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '采集属性ID',
  `attr_name` varchar(32) DEFAULT NULL COMMENT '属性名称',
  `attr_unit` varchar(16) DEFAULT NULL COMMENT '属性单位',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据采集表';


DROP TABLE IF EXISTS `ibms_dev_alarm_record`;
CREATE TABLE IF NOT EXISTS `ibms_dev_alarm_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `device_id` int(10) DEFAULT NULL COMMENT '设备ID',
  `subsystem_id` int(10) DEFAULT NULL COMMENT '子系统ID',
  `alarm` tinyint(1) DEFAULT NULL COMMENT '是否告警',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备报警记录表';


DROP TABLE IF EXISTS `ibms_dev_collection_record`;
CREATE TABLE IF NOT EXISTS `ibms_dev_collection_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `device_id` int(10) DEFAULT NULL COMMENT '设备ID',
  `subsystem_id` int(10) DEFAULT NULL COMMENT '子系统ID',
  `attr_id` int(10) DEFAULT NULL COMMENT '属性ID',
  `value` varchar(32) DEFAULT NULL COMMENT '采集值',
  `collection_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '采集日期，如：2018-10-12',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '采集时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备采集记录表';


DROP TABLE IF EXISTS `ibms_dev_connect_record`;
CREATE TABLE IF NOT EXISTS `ibms_dev_connect_record` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `device_id` int(10) DEFAULT NULL COMMENT '设备ID',
  `subsystem_id` int(10) DEFAULT NULL COMMENT '子系统ID',
  `connect_status` tinyint(1) DEFAULT NULL COMMENT '连接状态，1：已连接，0：未连接',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备连接记录表';