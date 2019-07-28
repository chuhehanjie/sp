package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.MyCarEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 我的车辆表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
@Mapper
public interface MyCarDao extends BaseDao<MyCarEntity> {
	
}
