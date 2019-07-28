package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.AccessRecordEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 出入记录表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since Apr 29.19
 */
@Mapper
public interface AccessRecordDao extends BaseDao<AccessRecordEntity> {
	
}
