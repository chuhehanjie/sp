package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.SearchHisEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 搜索历史表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 15.19
 */
@Mapper
public interface SearchHisDao extends BaseDao<SearchHisEntity> {
	
}
