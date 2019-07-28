package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.ContactEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 联系人表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 16.19
 */
@Mapper
public interface ContactDao extends BaseDao<ContactEntity> {
	
}
