package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.WorkOrderEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 工单表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since Apr 29.19
 */
@Mapper
public interface WorkOrderDao extends BaseDao<WorkOrderEntity> {
	
}
