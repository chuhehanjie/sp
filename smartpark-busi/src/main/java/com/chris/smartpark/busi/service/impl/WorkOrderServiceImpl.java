package com.chris.smartpark.busi.service.impl;

import com.chris.base.common.utils.DateUtils;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.app.cache.AppLoginUser;
import com.chris.base.modules.app.cache.AppLoginUserCacheUtils;
import com.chris.base.modules.oss.entity.SysAttachmentEntity;
import com.chris.base.modules.oss.service.SysAttachmentService;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.chris.smartpark.busi.dao.WorkOrderDao;
import com.chris.smartpark.busi.entity.WorkOrderEntity;
import com.chris.smartpark.busi.service.WorkOrderService;



@Service("workOrderService")
public class WorkOrderServiceImpl implements WorkOrderService {

	@Autowired
	private WorkOrderDao workOrderDao;

	@Autowired
	private SysAttachmentService sysAttachmentService;
	
	@Override
	public WorkOrderEntity queryObject(Long id){
		return workOrderDao.queryObject(id);
	}
	
	@Override
	public List<WorkOrderEntity> queryList(Map<String, Object> map){
		List<WorkOrderEntity> workOrders = workOrderDao.queryList(map);
		if (ValidateUtils.isNotEmptyCollection(workOrders)) {
			workOrders.forEach(workOrderItem -> {
				// 根据工单ID查询附件信息
				SysAttachmentEntity sysAttachmentParam = new SysAttachmentEntity();
				sysAttachmentParam.setObjId(workOrderItem.getId());
				sysAttachmentParam.setObjSource("WORK_ORDER");
				List<SysAttachmentEntity> attachmentList = this.sysAttachmentService.queryAttachmentsByCondition(sysAttachmentParam);
				if (ValidateUtils.isNotEmptyCollection(attachmentList)) {
					workOrderItem.setAttachmentList(attachmentList);
				}
			});
		}
		return workOrders;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return workOrderDao.queryTotal(map);
	}
	
	@Override
	public void save(WorkOrderEntity workOrder){
		workOrder.setStatus(1); // 设置状态为新建
		// 根据 openId 获取用户信息
		AppLoginUser appLoginUser = AppLoginUserCacheUtils.getAppLoginUser(workOrder.getOpenId());
		if (ValidateUtils.isNotEmpty(appLoginUser)) {
			workOrder.setCreateUserId(appLoginUser.getUserId());
			workOrder.setCreateTime(DateUtils.currentDate());
		}
		this.workOrderDao.save(workOrder);
		this.setAttachmentListRela(workOrder);
	}

	private void setAttachmentListRela(WorkOrderEntity workOrder) {
		if (ValidateUtils.isNotEmptyCollection(workOrder.getAttachmentList())) {
			workOrder.getAttachmentList().forEach(attachemnt -> {
				attachemnt.setObjSource("WORK_ORDER");
				attachemnt.setObjId(workOrder.getId());
			});
			this.sysAttachmentService.updateBatch(workOrder.getAttachmentList());
		}
	}

	@Override
	public void update(WorkOrderEntity workOrder){
		AppLoginUser appLoginUser = AppLoginUserCacheUtils.getAppLoginUser(workOrder.getOpenId());
		if (ValidateUtils.isNotEmpty(appLoginUser)) {
			workOrder.setUpdateUserId(appLoginUser.getUserId());
			workOrder.setUpdateTime(DateUtils.currentDate());
		}
		// TODO 首先应该删除对应的附件
		this.setAttachmentListRela(workOrder);
		this.workOrderDao.update(workOrder);
	}
	
	@Override
	public void delete(Long id){
		workOrderDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		workOrderDao.deleteBatch(ids);
	}
	
}
