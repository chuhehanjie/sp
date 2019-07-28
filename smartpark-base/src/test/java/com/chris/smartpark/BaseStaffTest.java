package com.chris.smartpark;

import com.alibaba.fastjson.JSONObject;
import com.chris.BaseApplication;
import com.chris.smartpark.base.entity.BaseOrganizationEntity;
import com.chris.smartpark.base.entity.BaseStaffEntity;
import com.chris.smartpark.base.service.BaseOrganizationService;
import com.chris.smartpark.base.service.BaseStaffService;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BaseApplication.class)
public class BaseStaffTest {
	@Autowired
	private BaseStaffService baseStaffService;

	@Autowired
	private BaseOrganizationService baseOrgService;

	@Test
	public void addStaff() {
		BaseStaffEntity baseStaff = new BaseStaffEntity();
		baseStaff.setSrcusercode("EIP1008611");
		baseStaff.setTalias("0731ZHANGSAN");
		baseStaff.setIdcardnumber("430302199801220798");
		baseStaff.setEmploynum("ENP1001");
		baseStaff.setUsername("张三");
		baseStaff.setGender("男");
		baseStaff.setSdeptcode("ORG10086");
		/**
		 * 0100	一岗 总部部门总监、省公司正职领导
		 * 0200	二岗 总部部门副总监、省公司副职领导
		 * 0300	三岗 总部正处长、省公司部门主任、本地网正职领导
		 * 0400	四岗 总部副处长、省公司部门副主任、本地网副职领导
		 * 0500	五岗 高级业务经理
		 * 0600	六岗 业务主管
		 * 0700	七岗
		 * 0800	八岗
		 * 0900	九岗
		 * 1000	十岗
		 * 1100	十一岗
		 * 1200	十二岗
		 * 1300	十三岗
		 * 1400	十四岗
		 * 1500	十五岗
		 * 9900	其他
		**/
		baseStaff.setPositioncode("0800");
		baseStaff.setTitle("工程师");
		/**
		 * 1101	总经理
		 1102	副总经理
		 1103	资深经理
		 1104	部门经理
		 1105	部门副经理
		 1201	资深专家(技术类)
		 1202	资深经理(非技术类)
		 1203	高级专家(技术类)
		 1204	高级督导(非技术类)
		 1205	高级工程师(技术类)
		 1206	高级经理(非技术类)
		 1207	经理
		 1208	高级主管
		 1209	主管
		 1210	主办
		 1211	助理
		 1212	业务管理员
		 2101	总经理
		 2102	副总经理级
		 2103	资深经理
		 2104	部门经理
		 2105	部门副经理
		 2201	资深专家(技术类)
		 2202	资深经理(非技术类)
		 2203	高级专家(技术类)
		 2204	高级督导(非技术类)
		 2205	高级工程师(技术类)
		 2206	高级经理(非技术类)
		 2207	经理
		 2208	高级主管
		 2209	主管
		 2210	主办
		 2211	助理
		 2212	业务管理员
		 3101	总经理
		 3102	副总经理级
		 3103	资深经理
		 3104	部门经理
		 3105	部门副经理
		 3201	资深专家(技术类)
		 3202	资深经理(非技术类)
		 3203	高级专家(技术类)
		 3204	高级督导(非技术类)
		 3205	高级工程师(技术类)
		 3206	高级经理(非技术类)
		 3207	经理
		 3208	高级主管
		 3209	主管
		 3210	主办
		 3211	助理
		 3212	业务管理员
		 4101	总经理
		 4102	副总经理级
		 4103	资深经理
		 4104	部门经理
		 4105	部门副经理
		 4201	资深专家(技术类)
		 4202	资深经理(非技术类)
		 4203	高级专家(技术类)
		 4204	高级督导(非技术类)
		 4205	高级工程师(技术类)
		 4206	高级经理(非技术类)
		 4207	经理
		 4208	高级主管
		 4209	主管
		 4210	主办
		 4211	助理
		 4212	业务管理员

		 */
		baseStaff.setTitlecode("3201");
		baseStaff.setEmail("zhangsan666@163.com");
		baseStaff.setMobile("18875270068");
		baseStaff.setOfficetel("0731-85778866");
		/**
		 * 用户类型编码	用户类型名称 	对应人力编码
		 1	内部员工/合同工	111、101、10102、10103、10104、104、10401、10402、
		 10407、10403、1040101、1040102、1040103、1040104、
		 1040105、1040106、301、30101、30102、30103、401、
		 402、403、999
		 2	外部员工/派遣工	103、10301、10302、10303、10304
		 3	外包	901
		 4	外聘	无,门户自定义
		 5	承包员工	106
		 6	乡话人员	90501
		 9	其它	除合同、派遣、外包、外聘、承包员工、乡话人员外。
		 102、10201、10202、10203、105、902、90201、
		 90202、90203、90204、90205、90206、90207、90208、
		 90209、90210、90211、90212、905、90502、
		 90503、90504、90505
		 */
		baseStaff.setCtusertype("1");
		baseStaff.setStatus("1"); // 1：有效 0：无效
		baseStaff.setOldsdeptcode(null);
		baseStaff.setContractType("0"); // 承包模式 0：无 1：内包（绩效考核）2：内包（经营责任制）3：员工创业承包 4：外包人员承包
		/**
		 * 1	组织机构
		 2	用户
		 4	用户主职
		 8	用户兼职
		 16	用户借调
		 */
		baseStaff.setDatatype("2");
		/**
		 * 0	初始化用户	初始化用户
		 1	新增用户	新增用户
		 2	更新用户基本信息	更新用户基本信息
		 3	用户主职跨部门变动	用户主职跨部门变动
		 4	用户主职部门内变动	用户主职部门内变动
		 5	用户主职跨省跨单位变动	用户主职跨省跨单位变动
		 6	用户主职省内跨单位变动	用户主职省内跨单位变动
		 7	援藏开始	借调新增
		 8	援藏信息更新（含部门变动）	借调更新
		 9	援藏结束	借调删除
		 10	借调、交流、挂职开始	兼职/借调新增
		 11	借调、交流、挂职信息更新（含部门变动）	兼职/借调更新
		 12	借调、交流、挂职结束	兼职/借调删除
		 13	派驻海外	用户停用
		 14	返聘	用户更新
		 15	（离）退休	用户停用
		 16	员工解除	用户停用
		 17	企业解除	用户停用
		 */
		baseStaff.setHropertype("0");
		/**
		 * 1	新增
		 2	删除
		 4	更新基本信息
		 8	更新所属组织(可同时更新基本信息)
		 16	状态变更(可同时更新基本信息和所属组织)
		 */
		baseStaff.setOpertype("1");
		baseStaff.setUnicode("UNI1008866"); // 即ctHrUserCode
		baseStaff.setHrid("ORG1006655"); // 对应用户所在的一个组织
		baseStaff.setLoginname("ronaldo"); // 全省唯一，员工号@两位省公司编码 集团删除唯一标识
		baseStaff.setParentcode("TR12345678"); // 两位省公司编码+8位流水号
		baseStaff.setCtcorpcode("HN87654321"); // 两位省公司编码+8位流水号
		/**
		 * 101	一般在岗
		 * 2	不在岗
		 */
		baseStaff.setCtstatus(""); // 人员状态
		baseStaff.setCtbirthday("1998-01-22"); // yyyy-MM-dd
		/**
		 * 100	一岗
		 200	二岗
		 300	三岗
		 400	四岗
		 500	五岗
		 600	六岗
		 700	七岗
		 800	八岗
		 900	九岗
		 1000	十岗
		 1100	十一岗
		 1200	十二岗
		 1300	十三岗
		 1400	十四岗
		 1500	十五岗
		 9000	集团领导岗
		 9900	其他
		 9999	无岗位层级
		 */
		baseStaff.setCtposleveltype("600"); // 旧岗位等级体系
		baseStaff.setCtpositionlevel("600"); // 如果岗位体系选择为新岗位体系则参照岗位等级（新），如果岗位体系选择为旧岗位体系则参照岗位等级（旧）
		baseStaff.setCtposlayertype("800");
		baseStaff.setCtpositionlayer("123");
		baseStaff.setCtpositionsequence("111");
		/**
		 * Z01	男
		 * Z02	女
		 */
		baseStaff.setCtgender("Z01");
		baseStaff.setReserveaccount("222");
		baseStaff.setUserstatus("1"); // 1正常、2停用
		/**
		 * 101	合同制员工
		 111	合同制员工
		 102	其他从业人员
		 103	派遣制用工
		 104	不在岗（原名：离开本单位保留关系更换为不在岗）
		 105	非全日制用工
		 106	承包员工
		 301	离退休退职人员
		 401	离职
		 402	调离(内部调动)
		 403	在职死亡
		 901	外包人员
		 902	其它相关人员
		 */
		baseStaff.setGctusertype("101"); // 员工类型
		baseStaff.setShownum("");
		/**
		 * 1	主职
		 2	兼职
		 3	借调
		 */
		baseStaff.setPtype("1"); // 任职类型
		baseStaff.setCttitle("");
		baseStaff.setCtmail("");
		baseStaff.setCtpositiontype("");
		baseStaff.setCtpreferredmobile("");
		baseStaff.setCttelephonenumber("");
		baseStaff.setCtpositionname("");
		baseStaff.setRetire(null); // 非空值-是离退休、空值-不是离退休
		baseStaff.setGroupmail("");
		baseStaff.setRegistersystem("");
		System.out.println("员工信息请求JSON = " + JSONObject.toJSONString(baseStaff));
		this.baseStaffService.save(baseStaff);
		System.out.println("保存员工信息成功！员工ID = " + baseStaff.getId());
	}

	@Test
	public void addCompany() {
		BaseOrganizationEntity company = new BaseOrganizationEntity();
		company.setSdeptcode("ORG073110001"); // 标准组织唯一编码
		company.setDeptname("中国电信长沙分公司"); // 组织名称
		company.setSparentcode("ORG073110000"); // 上级组织唯一编码
		/**
		 * 1	单位
		 * 2	部门
		 */
		company.setOrgType("1");
		/**
		 * 0	集团
		 1	一级 省
		 2	二级 市
		 3	三级 县
		 4	四级 支局
		 */
		company.setLevelOrgType("2"); // 四级机构组织层级
		/**
		 * 编号	名称	上级编号	层级
		 2001	市州分公司		2
		 2002	省公司本部部门		2
		 2003	省直属单位		2
		 2004	二级团队		2
		 3001	省公司本部部门内设分部		3
		 3002	省直属单位内设分部		3
		 3003	县分公司		3
		 3004	城市分局		3
		 3005	三级团队		3
		 3006	二级机构本部		3
		 31	市州分公司部门		3
		 3101	市州分公司销售服务部门	31	3
		 3102	市州分公司网络运营部门	31	3
		 3103	市州分公司支撑服务部门	31	3
		 4001	支撑类四级经营单元		4
		 4002	县分公司部门		4
		 4003	县分公司班组		4
		 4004	其它四级组织机构		4
		 4005	四级团队		4
		 4006	三级机构本部		4
		 41	地域型收入类	　	4
		 4101	农村支局	41	4
		 4102	城市营业部	41	4
		 42	发展类	　	4
		 4201	三级及以上营业厅	42	4
		 4202	核心商圈班组	42	4
		 4203	大连锁班组	42	4
		 4204	渠道运营班组	42	4
		 4205	终端运营班组 	42	4
		 4206	农村渠道班组 	42	4
		 4207	渠道分部	42	4
		 43	特征型收入类	　	4
		 4301	商客营业部	43	4
		 4302	专业市场营业部	43	4
		 4303	商务楼宇营业部	43	4
		 4304	产业园区营业部	43	4
		 4305	系统集成分部	43	4
		 4306	商业客户中心	43	4
		 4307	校园分部	43	4
		 4308	高校营业部	43	4
		 4309	中小学营业部 	43	4
		 4310	清单级分部	43	4
		 4311	政企分部	43	4
		 44	维系类	　	4
		 4401	VIP维系班组	44	4
		 4402	大众维系班组	44	4
		 4403	维系分部	44	4
		 45	四级维护责任单元	　	4
		 4501	城市区域维护分部	45	4
		 4502	城市装维分部	45	4
		 4503	农村区域维护中心	45	4
		 4504	接入网络优化支撑分部	45	4
		 4505	接入设备维护分部	45	4
		 4506	接入线路维护分部	45	4
		 4507	县接入维护中心	45	4
		 4508	县客户端装维中心	45	4
		 4509	政企客户支撑分部	45	4
		 4510	无线维护分部	45	4
		 */
		company.setSaleOrgType("2001"); // 营销组织类型
		company.setFullname("中国电信长沙分公司");
		company.setShortname("长电");
		company.setStatus("1"); // 1：正常 0：停用
		/**
		 * 0	市区
		 * 1	县域
		 * 2	农村
		 */
		company.setCityCountryType("0"); // 区域
		company.setContractType("0"); // 承包模式
		company.setDatatype("1"); // 1为组织机构
		company.setHropertype("0");
		company.setOpertype("1");
		// 组织机构编码	uniCode	即ctOu
		company.setUnicode("UNI1234");
		// 集团组织唯一编码	ctOu	两位省公司编码+8位流水号 集团删除唯一标识
		company.setCtou("");
		// 上级公司编码	parentCorpCode	如果该组织为公司，则为上级公司ctOu；如果该组织为部门，则为该部门所属公司ctOu
		company.setParentcorpcode("");
		// 上级集团组织唯一编码	parentCode	上级组织ctOu
		company.setParentcode("");
		// 公司类型	ctCorpType	如果公司则必填 参照 6.2
		company.setCtcorptype("");
		//部门类型	ctDeptType	如果部门则必填 参照 6.3
		company.setCtdepttype("");
		// 组织类型	ctOrgType	参照数据字典6.1
		company.setCtorgtype("");
		// 部门层级	ctDeptLevel	参照数据字典6.16
		company.setCtdeptlevel("");
		// 排序号	showNum	（支持浮点）
		company.setShownum("");
		// 组织机构状态	deptStatus	1：有效 2：无效
		company.setDeptstatus("1");
		// 组织主职领导	ctOrgManager	存用户帐号
		company.setCtorgmanager("");
		// 组织副职领导	ctViceManager	存用户帐号
		company.setCtvicemanager("");
		// 是否划小承包单元	IS_CNTRT_MGMT_UNIT	OA选择
		company.setIsCntrtMgmtUnit("");
		// 划小承包模式	CNTRT_MGMT_MODE_FLAG	内包（绩效考核）对应绩效考核，内包（经营责任制）对应经营责任制，员工创业承包对应员工创业承包，外包人员承包对应社会外包
		company.setCntrtMgmtModeFlag("");
		// 划小承包单元编码	CNTRT_MGMT_UNIT_CD	根据四级组织编码生成，是承包单元才有编码
		company.setCntrtMgmtUnitCd("");
		// 划小承包单元名称	CNTRT_MGMT_UNIT_NM	用门户组织名称（简称）
		company.setCntrtMgmtUnitNm("");
		// 划小承包类型	CNTRT_MGMT_TYPE	业务部门对应，OA选择并带出对应值
		company.setCntrtMgmtType("");
		// 划小承包考核类型	CNTRT_MGMT_CHECK_TYPE	是承包才允许手动输入
		company.setCntrtMgmtCheckType("");
		// 划小承包单元层级	CNTRT_MGMT_UNIT_LEVEL	用门户四级组织层级 5级不可选（自动匹配四级）
		company.setCntrtMgmtUnitLevel("");
		// 划小承包状态	CNTRT_MGMT_STATE	承包对应（待承包、已承包）、未承包
		company.setCntrtMgmtState("");
		System.out.println("公司级组织机构请求JSON = " + JSONObject.toJSONString(company));
		this.baseOrgService.save(company);
		System.out.println("保存公司级组织机构信息成功！组织机构ID = " + company.getId());
	}

	@Test
	public void addDept() {
		BaseOrganizationEntity company = new BaseOrganizationEntity();
		company.setSdeptcode("ORG07311000101"); // 标准组织唯一编码
		company.setDeptname("企业信息化部"); // 组织名称
		company.setSparentcode("ORG073110001"); // 上级组织唯一编码
		/**
		 * 1	单位
		 * 2	部门
		 */
		company.setOrgType("2");
		/**
		 * 0	集团
		 1	一级 省
		 2	二级 市
		 3	三级 县
		 4	四级 支局
		 */
		company.setLevelOrgType("2"); // 四级机构组织层级
		/**
		 * 编号	名称	上级编号	层级
		 2001	市州分公司		2
		 2002	省公司本部部门		2
		 2003	省直属单位		2
		 2004	二级团队		2
		 3001	省公司本部部门内设分部		3
		 3002	省直属单位内设分部		3
		 3003	县分公司		3
		 3004	城市分局		3
		 3005	三级团队		3
		 3006	二级机构本部		3
		 31	市州分公司部门		3
		 3101	市州分公司销售服务部门	31	3
		 3102	市州分公司网络运营部门	31	3
		 3103	市州分公司支撑服务部门	31	3
		 4001	支撑类四级经营单元		4
		 4002	县分公司部门		4
		 4003	县分公司班组		4
		 4004	其它四级组织机构		4
		 4005	四级团队		4
		 4006	三级机构本部		4
		 41	地域型收入类	　	4
		 4101	农村支局	41	4
		 4102	城市营业部	41	4
		 42	发展类	　	4
		 4201	三级及以上营业厅	42	4
		 4202	核心商圈班组	42	4
		 4203	大连锁班组	42	4
		 4204	渠道运营班组	42	4
		 4205	终端运营班组 	42	4
		 4206	农村渠道班组 	42	4
		 4207	渠道分部	42	4
		 43	特征型收入类	　	4
		 4301	商客营业部	43	4
		 4302	专业市场营业部	43	4
		 4303	商务楼宇营业部	43	4
		 4304	产业园区营业部	43	4
		 4305	系统集成分部	43	4
		 4306	商业客户中心	43	4
		 4307	校园分部	43	4
		 4308	高校营业部	43	4
		 4309	中小学营业部 	43	4
		 4310	清单级分部	43	4
		 4311	政企分部	43	4
		 44	维系类	　	4
		 4401	VIP维系班组	44	4
		 4402	大众维系班组	44	4
		 4403	维系分部	44	4
		 45	四级维护责任单元	　	4
		 4501	城市区域维护分部	45	4
		 4502	城市装维分部	45	4
		 4503	农村区域维护中心	45	4
		 4504	接入网络优化支撑分部	45	4
		 4505	接入设备维护分部	45	4
		 4506	接入线路维护分部	45	4
		 4507	县接入维护中心	45	4
		 4508	县客户端装维中心	45	4
		 4509	政企客户支撑分部	45	4
		 4510	无线维护分部	45	4
		 */
		company.setSaleOrgType("31"); // 营销组织类型
		company.setFullname("中国电信长沙分公司企业信息化部");
		company.setShortname("企信部");
		company.setStatus("1"); // 1：正常 0：停用
		/**
		 * 0	市区
		 * 1	县域
		 * 2	农村
		 */
		company.setCityCountryType("0"); // 区域
		company.setContractType("0"); // 承包模式
		company.setDatatype("1"); // 1为组织机构
		company.setHropertype("0");
		company.setOpertype("1");
		// 组织机构编码	uniCode	即ctOu
		company.setUnicode("UNI1234001");
		// 集团组织唯一编码	ctOu	两位省公司编码+8位流水号 集团删除唯一标识
		company.setCtou("");
		// 上级公司编码	parentCorpCode	如果该组织为公司，则为上级公司ctOu；如果该组织为部门，则为该部门所属公司ctOu
		company.setParentcorpcode("");
		// 上级集团组织唯一编码	parentCode	上级组织ctOu
		company.setParentcode("");
		// 公司类型	ctCorpType	如果公司则必填 参照 6.2
		company.setCtcorptype("");
		//部门类型	ctDeptType	如果部门则必填 参照 6.3
		company.setCtdepttype("");
		// 组织类型	ctOrgType	参照数据字典6.1
		company.setCtorgtype("");
		// 部门层级	ctDeptLevel	参照数据字典6.16
		company.setCtdeptlevel("");
		// 排序号	showNum	（支持浮点）
		company.setShownum("");
		// 组织机构状态	deptStatus	1：有效 2：无效
		company.setDeptstatus("1");
		// 组织主职领导	ctOrgManager	存用户帐号
		company.setCtorgmanager("");
		// 组织副职领导	ctViceManager	存用户帐号
		company.setCtvicemanager("");
		// 是否划小承包单元	IS_CNTRT_MGMT_UNIT	OA选择
		company.setIsCntrtMgmtUnit("");
		// 划小承包模式	CNTRT_MGMT_MODE_FLAG	内包（绩效考核）对应绩效考核，内包（经营责任制）对应经营责任制，员工创业承包对应员工创业承包，外包人员承包对应社会外包
		company.setCntrtMgmtModeFlag("");
		// 划小承包单元编码	CNTRT_MGMT_UNIT_CD	根据四级组织编码生成，是承包单元才有编码
		company.setCntrtMgmtUnitCd("");
		// 划小承包单元名称	CNTRT_MGMT_UNIT_NM	用门户组织名称（简称）
		company.setCntrtMgmtUnitNm("");
		// 划小承包类型	CNTRT_MGMT_TYPE	业务部门对应，OA选择并带出对应值
		company.setCntrtMgmtType("");
		// 划小承包考核类型	CNTRT_MGMT_CHECK_TYPE	是承包才允许手动输入
		company.setCntrtMgmtCheckType("");
		// 划小承包单元层级	CNTRT_MGMT_UNIT_LEVEL	用门户四级组织层级 5级不可选（自动匹配四级）
		company.setCntrtMgmtUnitLevel("");
		// 划小承包状态	CNTRT_MGMT_STATE	承包对应（待承包、已承包）、未承包
		company.setCntrtMgmtState("");
		System.out.println("部门级组织机构请求JSON = " + JSONObject.toJSONString(company));
		this.baseOrgService.save(company);
		System.out.println("部门级保存组织机构信息成功！组织机构ID = " + company.getId());
	}

    /**
     * 根据条件查询员工信息
     */
	@Test
	public void queryStaffByCondition() {
        List<BaseStaffEntity> staffList = this.baseStaffService.queryList(ImmutableMap.of("mobile", "15873230762"));
        System.out.println("员工列表JSON ＝ " + JSONObject.toJSONString(staffList));
    }

	/**
	 * 根据手机号查询员工所在园区名称
	 */
	@Test
    public void queryStaffParkName() {
		System.out.println("员工所有园区名称为 = " + this.baseStaffService.queryParkInfoByStaffMobile("18975841003"));
	}

}
