package com.chris.smartpark.base.dao;

import com.chris.smartpark.base.entity.BaseOrganizationEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 组织机构
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 15.18
 */
@Mapper
public interface BaseOrganizationDao extends BaseDao<BaseOrganizationEntity> {
	
}
