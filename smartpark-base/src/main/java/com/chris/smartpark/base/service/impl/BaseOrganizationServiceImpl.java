package com.chris.smartpark.base.service.impl;

import com.chris.base.common.tree.TreeNode;
import com.chris.base.common.utils.ValidateUtils;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.chris.smartpark.base.dao.BaseOrganizationDao;
import com.chris.smartpark.base.entity.BaseOrganizationEntity;
import com.chris.smartpark.base.service.BaseOrganizationService;


@Service("baseOrganizationService")
public class BaseOrganizationServiceImpl implements BaseOrganizationService {
	@Autowired
	private BaseOrganizationDao baseOrganizationDao;
	
	@Override
	public BaseOrganizationEntity queryObject(Long id){
		return baseOrganizationDao.queryObject(id);
	}
	
	@Override
	public List<BaseOrganizationEntity> queryList(Map<String, Object> map){
		return baseOrganizationDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return baseOrganizationDao.queryTotal(map);
	}
	
	@Override
	public void save(BaseOrganizationEntity baseOrganization){
		baseOrganizationDao.save(baseOrganization);
	}
	
	@Override
	public void update(BaseOrganizationEntity baseOrganization){
		baseOrganizationDao.update(baseOrganization);
	}
	
	@Override
	public void delete(Long id){
		baseOrganizationDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		baseOrganizationDao.deleteBatch(ids);
	}

	@Override
	public List<TreeNode> queryBaseOrgHierarchyByParkId(Long parkId) {
		// 根据园区ID查询对应园区所有的组织机构
		List<BaseOrganizationEntity> baseOrgList = this.queryList(ImmutableMap.of("parkId", parkId));
		if (ValidateUtils.isNotEmptyCollection(baseOrgList)) {
			List<TreeNode> nodes = Lists.newArrayList();
			for (BaseOrganizationEntity baseOrg: baseOrgList) {
			    this.createBaseOrgNode(baseOrgList, baseOrg, nodes);
			}
			return nodes;
		}
		return Collections.EMPTY_LIST;
	}

	private void createBaseOrgNode(List<BaseOrganizationEntity> baseOrgList, BaseOrganizationEntity baseOrg, List<TreeNode> nodes) {
		BaseOrganizationEntity parentOrg = this.findParentOrg(baseOrgList, baseOrg.getSparentcode());
		if (ValidateUtils.isEmpty(parentOrg)) {
            TreeNode node = TreeNode.createNode(baseOrg.getSdeptcode(), baseOrg.getDeptname(), null, null);
            if (!nodes.contains(node)) {
                this.addChildrenNode(node, baseOrgList);
                nodes.add(node);
            }
        } else {
            this.createBaseOrgNode(baseOrgList, parentOrg, nodes);
        }
	}

	private BaseOrganizationEntity findParentOrg(List<BaseOrganizationEntity> baseOrgList, String parentcode) {
		return baseOrgList.stream().filter(baseOrg -> ValidateUtils.equals(baseOrg.getSdeptcode(), parentcode)).findFirst().orElse(null);
	}

	private void addChildrenNode(TreeNode parentNode, List<BaseOrganizationEntity> baseOrgList) {
		List<TreeNode> childrenNodes = Lists.newArrayList();
		baseOrgList.forEach(baseOrg -> {
			if (ValidateUtils.equals(baseOrg.getSparentcode(), parentNode.getNodeId())) {
                TreeNode childNode = TreeNode.createNode(baseOrg.getSdeptcode(), baseOrg.getDeptname(), parentNode.getNodeId(), null);
                this.addChildrenNode(childNode, baseOrgList);
                childrenNodes.add(childNode);
			}
		});
		parentNode.setChildren(childrenNodes);
	}

}
