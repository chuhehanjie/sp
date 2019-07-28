package com.chris.smartpark.base.service;

import com.chris.base.common.tree.TreeNode;
import com.chris.smartpark.base.entity.BaseOrganizationEntity;

import java.util.List;
import java.util.Map;

/**
 * 组织机构
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 15.18
 */
public interface BaseOrganizationService {
	
	BaseOrganizationEntity queryObject(Long id);
	
	List<BaseOrganizationEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BaseOrganizationEntity baseOrganization);
	
	void update(BaseOrganizationEntity baseOrganization);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    List<TreeNode> queryBaseOrgHierarchyByParkId(Long parkId);
}
