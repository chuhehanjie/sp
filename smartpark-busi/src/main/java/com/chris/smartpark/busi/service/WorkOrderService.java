package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.entity.WorkOrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 工单表
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since Apr 29.19
 */
public interface WorkOrderService {
	
	WorkOrderEntity queryObject(Long id);
	
	List<WorkOrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(WorkOrderEntity workOrder);
	
	void update(WorkOrderEntity workOrder);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
