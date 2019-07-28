DROP TABLE IF EXISTS `base_park`;
CREATE TABLE IF NOT EXISTS `base_park` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '园区ID',
  `name` varchar(64) DEFAULT NULL COMMENT '园区名称',
  `province` varchar(10) DEFAULT NULL COMMENT '园区所在省',
  `city` varchar(10) DEFAULT NULL COMMENT '园区所在城市',
  `area` varchar(10) DEFAULT NULL COMMENT '园区所在区县',
  `detail_address` varchar(128) DEFAULT NULL COMMENT '园区详细地址',
  `longitude` varchar(32) DEFAULT NULL COMMENT '经度',
  `latitude` varchar(32) DEFAULT NULL COMMENT '纬度',
  `building_time` datetime DEFAULT NULL COMMENT '始建时间',
  `building_area` varchar(32) DEFAULT NULL COMMENT '建筑面积',
  `remark` varchar(500) DEFAULT NULL COMMENT '园区简介',
  `park_pic_url` varchar(255) DEFAULT NULL COMMENT '园区图片',
  `status` char(1) DEFAULT '1' COMMENT '状态，1：有效 0：无效',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int(10) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='园区信息表';


DROP TABLE IF EXISTS `base_park_area`;
CREATE TABLE IF NOT EXISTS `base_park_area` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '区域ID',
  `area_name` varchar(64) DEFAULT NULL COMMENT '区域名称',
  `area_desc` varchar(500) DEFAULT NULL COMMENT '区域描述',
  `park_id` int(10) DEFAULT NULL COMMENT '园区ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int(10) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='园区区域信息表';


DROP TABLE IF EXISTS `base_building`;
CREATE TABLE IF NOT EXISTS `base_building` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '楼宇ID',
  `building_name` varchar(64) DEFAULT NULL COMMENT '楼宇名称',
  `address` varchar(128) DEFAULT NULL COMMENT '楼宇地址',
  `floors` int(2) DEFAULT NULL COMMENT '楼宇层数',
  `park_id` int(10) DEFAULT NULL COMMENT '园区ID',
  `area_id` int(10) DEFAULT NULL COMMENT '区域ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建人',
  `create_user_id` int(10) DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='园区楼宇信息表';


DROP TABLE IF EXISTS `base_floor`;
CREATE TABLE IF NOT EXISTS `base_floor` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '楼层ID',
  `floor_name` varchar(64) DEFAULT NULL COMMENT '楼层名称',
  `rooms` int(2) DEFAULT NULL COMMENT '楼层房间数',
  `company_id` int(10) DEFAULT NULL COMMENT '公司ID,对应base_organization表ID',
  `building_id` int(10) DEFAULT NULL COMMENT '楼宇ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int(10) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='园区楼层信息表';


DROP TABLE IF EXISTS `base_room`;
CREATE TABLE IF NOT EXISTS `base_room` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '房间ID',
  `room_no` varchar(16) DEFAULT NULL COMMENT '房间号',
  `room_name` varchar(32) DEFAULT NULL COMMENT '房间名称',
  `room_type` varchar(32) DEFAULT NULL COMMENT '房间类型',
  `floor_id` int(10) DEFAULT NULL COMMENT '楼层ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user_id` int(10) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `update_user_id` int(10) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='园区房间信息表';

DROP TABLE IF EXISTS base_staff;
CREATE TABLE base_staff
(
   id                   bigint not null auto_increment comment 'bigint',
   SRCUSERCODE                varchar(50) not null comment '门户UID如: EIP+员工编码删除唯一标识',
   TALIAS               varchar(50) not null comment '门户帐号删除唯一标识',
   IDCARDNUMBER         varchar(50) not null comment '身份证号码删除唯一标识',
   EMPLOYNUM            varchar(50) not null comment '员工编码HR删除用户唯一标识',
   USERNAME             varchar(50) not null comment '姓名',
   GENDER               varchar(50) not null comment '性别参照 6.216.21.	性别（GENDER）',
   SDEPTCODE            varchar(100) comment '所在组织唯一编码',
   POSITIONCODE         varchar(50) not null comment '岗位层级编6.226.22.	岗位层级（POSITIONCODE）',
   TITLE                varchar(100) not null comment '岗位名称',
   TITLECODE            varchar(50) comment '职务编码6.196.19.	职务编码（TITLECODE）',
   EMAIL                varchar(100) comment '邮件地址',
   MOBILE               varchar(50) not null comment '联系方式/手机号',
   OFFICETEL            varchar(50) comment '办公电话',
   CTUSERTYPE           varchar(50) not null comment '用户类型6.236.23.	用户类型（CTUSERTYPE）',
   STATUS               varchar(50) comment '用户状态1：有效 0：无效',
   OLDSDEPTCODE         varchar(100) comment '原所在组织唯一编码 用户修改组织时',
   CONTRACT_TYPE        varchar(100) comment '承包模式6.256.25.	承包模式（CONTRACT_TYPE）',
   dataType             varchar(50) comment '数据类型参照数据字典7.1, 7.1.	对象类型（dataType）数据类型为用户、用户主职、用户兼职、用户借调',
   hrOperType           varchar(50) comment 'HR业务操作类型7.27.2.	HR操作类型（hrOperType）',
   operType             varchar(50) comment '对应操作类型7.37.3.	对应操作类型（operType）',
   uniCode                 varchar(50) comment '员工编号即ctHrUserCode',
   hrID                 varchar(100) comment 'hr记录主键对应用户所在的一个组织',
   loginName            varchar(50) comment '集团用户帐号全省唯一，员工号@两位省公司编码,集团删除唯一标识',
   parentCode           varchar(50) comment '所在集团组织的唯一编码两位省公司编码+8位流水号',
   ctCorpCode           varchar(50) comment '公司编码两位省公司编码+8位流水号',
   ctStatus             varchar(50) comment '人员状态6.156.15.	人员状态（ctStatus）',
   ctBirthday           varchar(50) comment '生日yyyy-MM-dd',
   ctPosLevelType       varchar(50) comment '岗位等级体系新岗位体系、旧岗位体系 参照 6.7 岗位等级体系（ctPosLevelType）',
   ctPositionLevel      varchar(50) comment '岗位等级如果岗位体系选择为新岗位体系则参照岗位等级（新），如果岗位体系选择为旧岗位体系则参照岗位等级（旧）参照6.8/6.9 6.8.	岗位等级-旧（ctPositionLevel） 6.9.	岗位等级-新（ctPositionLevel）',
   ctPosLayerType       varchar(50) comment '岗位层级体系新岗位层级体系、旧岗位层级体系 参照 6.11 岗位层级体系（ctPosLayerType）',
   ctPositionLayer      varchar(50) comment '岗位层级参照数据字典6.12/6.13 参照 6.11 6.12.	岗位层级-旧（ctPositionLayer） 6.13.	岗位层级-新（ctPositionLayer）',
   ctPositionSequence   varchar(50) comment '岗位序列6.146.14.	岗位序列（ctPositionSequence）',
   ctGender             varchar(50) comment '参照数据字典6.46.4.	性别（ctGender）',
   reserveAccount       varchar(50) comment '备用帐号',
   userStatus           varchar(50) comment '1正常、2停用用户状态2',
   gctUserType          varchar(50) comment '参照 6.6, 由于与四级划小同步规范中的属性重名,由ctUserType改为gctUserType6.6.	员工分类（gctUserType）',
   showNum              varchar(50) comment '同级排序编号用户部门排序号 支持浮点',
   pType                varchar(50) comment '任职类型6.5区分是否主职组织6.5.	任职类型（pType）',
   ctTitle              varchar(50) comment '职务用户部门职务名称',
   ctMail               varchar(50) comment '用户部门邮件地址',
   ctPositionType       varchar(50) comment '用户部门基准岗位',
   ctPreferredMobile    varchar(50) comment '用户部门手机号',
   ctTelephoneNumber    varchar(50) comment '用户部门办公电话',
   ctPositionName       varchar(50) comment '用户部门岗位名称',
   retire               varchar(50) comment '是否离退休用户非空值-是离退休、空值-不是离退休',
   groupMail            varchar(200) comment '统一邮箱',
   registerSystem       varchar(200) comment '注册人员来源系统',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='员工用户表';

DROP TABLE IF EXISTS base_organization;
CREATE TABLE base_organization
(
   id                   bigint not null auto_increment,
   SDEPTCODE            varchar(100) not null comment '删除唯一标识',
   DEPTNAME             varchar(100) not null comment '组织显示名称',
   SPARENTCODE          varchar(100) not null comment '上级组织唯一编码',
   ORG_TYPE             varchar(50) not null comment '标准组织类型6.20.	组织属性（ORG_TYPE）',
   LEVEL_ORG_TYPE       varchar(50) not null comment '四级机构组织层级6.17.	四级机构组织层级（LEVEL_ORG_TYPE）',
   SALE_ORG_TYPE        varchar(50) not null comment '营销组织类型6.18.	营销组织类型（SALE_ORG_TYPE）',
   FULLNAME             varchar(100) comment '组织全称',
   SHORTNAME            varchar(100) comment '组织简称',
   STATUS               varchar(50) not null comment '机构状态 1：正常 0：停用',
   CITY_COUNTRY_TYPE    varchar(100) comment '区域6.24.	区域（CITY_COUNTRY_TYPE）',
   CONTRACT_TYPE        varchar(100) comment '承包模式6.25.	承包模式（CONTRACT_TYPE）',
   dataType             varchar(50) comment '数据类型',
   hrOperType           varchar(50) comment 'HR业务操作类型7.2.	HR操作类型（hrOperType）',
   operType             varchar(50) comment '对应操作类型7.3.	对应操作类型（operType）',
   uniCode              varchar(50) comment '组织机构编码即ctOu',
   ctOu                 varchar(50) comment '集团组织唯一编码两位省公司编码+8位流水号 集团删除唯一标识',
   parentCorpCode       varchar(50) comment '上级公司编码 如果该组织为公司，则为上级公司ctOu；如果该组织为部门，则为该部门所属公司ctOu',
   parentCode           varchar(50) comment '上级集团组织唯一编码上级组织ctOu',
   ctCorpType           varchar(50) comment '公司类型如果公司则必填6.2.	公司类型（ctCorpType）',
   ctDeptType           varchar(50) comment '部门类型如果部门则必填6.3.	部门类型（ctDeptType）',
   ctOrgType            varchar(50) comment '组织类型参照字典ctOrgType',
   ctDeptLevel          varchar(50) comment '部门层级参照数据字典6.16.	部门层级（ctDeptLevel）',
   showNum              varchar(50) comment '排序号支持浮点',
   deptStatus           varchar(50) comment '组织机构状态1：有效 2：无效',
   ctOrgManager         varchar(50) comment '组织主职领导存用户帐号',
   ctViceManager        varchar(50) comment '组织副职领导存用户帐号',
   IS_CNTRT_MGMT_UNIT   varchar(10) comment '是否划小承包单元OA选择6.27.	是否承包单元（IS_CNTRT_MGMT_UNIT）',
   CNTRT_MGMT_MODE_FLAG varchar(10) comment '划小承包模式 内包（绩效考核）对应绩效考核，内包（经营责任制）对应经营责任制，员工创业承包对应员工创业承包，外包人员承包对应社会外包6.28.	划小承包模式（CNTRT_MGMT_MODE_FLAG）',
   CNTRT_MGMT_UNIT_CD   varchar(50) comment '划小承包单元编码根据四级组织编码生成，是承包单元才有编码',
   CNTRT_MGMT_UNIT_NM   varchar(200) comment '划小承包单元名称用门户组织名称（简称）',
   CNTRT_MGMT_TYPE      varchar(10) comment '划小承包类型业务部门对应，OA选择并带出对应值6.29.	划小承包类型（CNTRT_MGMT_TYPE）',
   CNTRT_MGMT_CHECK_TYPE varchar(10) comment '划小承包考核类型是承包才允许手动输入6.30.	划小承包考核类型（CNTRT_MGMT_CHECK_TYPE）',
   CNTRT_MGMT_UNIT_LEVEL varchar(200) comment '划小承包单元层级用门户四级组织层级 5级不可选（自动匹配四级）',
   CNTRT_MGMT_STATE     varchar(50) comment '划小承包状态承包对应（待承包、已承包）、未承包',
   park_id bigint(20) DEFAULT NULL COMMENT '园区ID',
   primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';





