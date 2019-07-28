package com.chris.smartpark;

import com.alibaba.fastjson.JSONObject;
import com.chris.BaseApplication;
import com.chris.base.common.tree.TreeNode;
import com.chris.base.modules.sys.entity.SysUserEntity;
import com.chris.base.modules.sys.service.SysRoleService;
import com.chris.base.modules.sys.service.SysUserService;
import com.chris.smartpark.base.dto.BaseStaffDTO;
import com.chris.smartpark.base.entity.BaseParkEntity;
import com.chris.smartpark.base.service.BaseDeviceInfoService;
import com.chris.smartpark.base.service.BaseOrganizationService;
import com.chris.smartpark.base.service.BaseParkService;
import com.chris.smartpark.base.service.BaseStaffService;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class BaseTest {
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private BaseParkService baseParkService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private BaseDeviceInfoService baseDeviceInfoService;

	@Autowired
	private BaseOrganizationService baseOrganizationService;

	@Autowired
	private BaseStaffService baseStaffService;

	@Test
	public void queryUser() {
		SysUserEntity user = this.sysUserService.queryByUserName("admin");
		System.out.println("查询用户信息：" + ToStringBuilder.reflectionToString(user));
	}

	@Test
	public void queryRole() {
		System.out.println("查询角色列表：" + JSONObject.toJSONString(this.sysRoleService.queryList(new HashMap<>())));
	}

	@Test
	public void queryPark() {
		BaseParkEntity parkEntity = this.baseParkService.queryObject(1);
		System.out.println("查询园区信息：" + JSONObject.toJSONString(parkEntity));
	}

	@Test
	public void queryDevice() {
		System.out.println("设备列表 ：" + JSONObject.toJSONString(this.baseDeviceInfoService.queryList(new HashMap<>())));
	}

    /**
     * 根据园区ID查询园区对应的组织机构列表
     * 2018-12-09
     */
	@Test
	public void queryBaseOrgList() {
		List<TreeNode> nodes = this.baseOrganizationService.queryBaseOrgHierarchyByParkId(1L);
		System.out.println("组织机构层次结构列表：" + JSONObject.toJSONString(nodes));
	}

	/**
     * 根据组织机构ID查询员工列表信息
     * 2018-12-09
     */
	@Test
	public void queryStaffListByOrgId() {
        List<BaseStaffDTO> staffList1 = this.baseStaffService.queryStaffListByOrgId("ORG07311000101");
        List<BaseStaffDTO> staffList2 = this.baseStaffService.queryStaffListByOrgId("ORG07311000102");
        List<BaseStaffDTO> staffList3 = this.baseStaffService.queryStaffListByOrgId("ORG07311000103");
		System.out.println("企信部下员工列表：" + JSONObject.toJSONString(staffList1));
		System.out.println("运维部下员工列表：" + JSONObject.toJSONString(staffList2));
		System.out.println("财务部下员工列表：" + JSONObject.toJSONString(staffList3));
	}



}
