package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.VisitorIdcardEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访客身份信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
@Mapper
public interface VisitorIdcardDao extends BaseDao<VisitorIdcardEntity> {
	
}
