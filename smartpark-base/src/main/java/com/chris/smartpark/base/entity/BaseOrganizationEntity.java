package com.chris.smartpark.base.entity;

import java.io.Serializable;


/**
 * 组织机构
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 15.18
 */
public class BaseOrganizationEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//删除唯一标识
	private String sdeptcode;
	//组织显示名称
	private String deptname;
	//上级组织唯一编码
	private String sparentcode;
	//标准组织类型6.20.	组织属性（ORG_TYPE）
	private String orgType;
	//四级机构组织层级6.17.	四级机构组织层级（LEVEL_ORG_TYPE）
	private String levelOrgType;
	//营销组织类型6.18.	营销组织类型（SALE_ORG_TYPE）
	private String saleOrgType;
	//组织全称
	private String fullname;
	//组织简称
	private String shortname;
	//机构状态 1：正常 0：停用
	private String status;
	//区域6.24.	区域（CITY_COUNTRY_TYPE）
	private String cityCountryType;
	//承包模式6.25.	承包模式（CONTRACT_TYPE）
	private String contractType;
	//数据类型
	private String datatype;
	//HR业务操作类型7.2.	HR操作类型（hrOperType）
	private String hropertype;
	//对应操作类型7.3.	对应操作类型（operType）
	private String opertype;
	//组织机构编码即ctOu
	private String unicode;
	//集团组织唯一编码两位省公司编码+8位流水号 集团删除唯一标识
            
	private String ctou;
	//上级公司编码 如果该组织为公司，则为上级公司ctOu； 如果该组织为部门，则为该部门所属公司ctOu
	private String parentcorpcode;
	//上级集团组织唯一编码上级组织ctOu
	private String parentcode;
	//公司类型如果公司则必填6.2.	公司类型（ctCorpType）
	private String ctcorptype;
	//部门类型如果部门则必填6.3.	部门类型（ctDeptType）
	private String ctdepttype;
	//组织类型参照字典ctOrgType
	private String ctorgtype;
	//部门层级参照数据字典6.16.	部门层级（ctDeptLevel）
	private String ctdeptlevel;
	//排序号支持浮点
	private String shownum;
	//组织机构状态1：有效 2：无效
	private String deptstatus;
	//组织主职领导存用户帐号
	private String ctorgmanager;
	//组织副职领导存用户帐号
	private String ctvicemanager;
	//是否划小承包单元OA选择6.27.	是否承包单元（IS_CNTRT_MGMT_UNIT）
	private String isCntrtMgmtUnit;
	//划小承包模式 内包（绩效考核）对应绩效考核，内包（经营责任制）对应经营责任制，员工创业承包对应员工创业承包，外包人员承包对应社会外包6.28.	划小承包模式（CNTRT_MGMT_MODE_FLAG）
	private String cntrtMgmtModeFlag;
	//划小承包单元编码根据四级组织编码生成，是承包单元才有编码
	private String cntrtMgmtUnitCd;
	//划小承包单元名称用门户组织名称（简称）
	private String cntrtMgmtUnitNm;
	//划小承包类型业务部门对应，OA选择并带出对应值6.29.	划小承包类型（CNTRT_MGMT_TYPE）
	private String cntrtMgmtType;
	//划小承包考核类型是承包才允许手动输入6.30.	划小承包考核类型（CNTRT_MGMT_CHECK_TYPE）
	private String cntrtMgmtCheckType;
	//划小承包单元层级用门户四级组织层级 5级不可选（自动匹配四级）
	private String cntrtMgmtUnitLevel;
	//划小承包状态承包对应（待承包、已承包）、未承包
	private String cntrtMgmtState;

	/**
	 * 园区ID
	 */
	private Long parkId;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setSdeptcode(String sdeptcode) {
		this.sdeptcode = sdeptcode;
	}

	public String getSdeptcode() {
		return sdeptcode;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getDeptname() {
		return deptname;
	}
	public void setSparentcode(String sparentcode) {
		this.sparentcode = sparentcode;
	}

	public String getSparentcode() {
		return sparentcode;
	}
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrgType() {
		return orgType;
	}
	public void setLevelOrgType(String levelOrgType) {
		this.levelOrgType = levelOrgType;
	}

	public String getLevelOrgType() {
		return levelOrgType;
	}
	public void setSaleOrgType(String saleOrgType) {
		this.saleOrgType = saleOrgType;
	}

	public String getSaleOrgType() {
		return saleOrgType;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFullname() {
		return fullname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getShortname() {
		return shortname;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setCityCountryType(String cityCountryType) {
		this.cityCountryType = cityCountryType;
	}

	public String getCityCountryType() {
		return cityCountryType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractType() {
		return contractType;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getDatatype() {
		return datatype;
	}
	public void setHropertype(String hropertype) {
		this.hropertype = hropertype;
	}

	public String getHropertype() {
		return hropertype;
	}
	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	public String getOpertype() {
		return opertype;
	}
	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	public String getUnicode() {
		return unicode;
	}
	public void setCtou(String ctou) {
		this.ctou = ctou;
	}

	public String getCtou() {
		return ctou;
	}
	public void setParentcorpcode(String parentcorpcode) {
		this.parentcorpcode = parentcorpcode;
	}

	public String getParentcorpcode() {
		return parentcorpcode;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getParentcode() {
		return parentcode;
	}
	public void setCtcorptype(String ctcorptype) {
		this.ctcorptype = ctcorptype;
	}

	public String getCtcorptype() {
		return ctcorptype;
	}
	public void setCtdepttype(String ctdepttype) {
		this.ctdepttype = ctdepttype;
	}

	public String getCtdepttype() {
		return ctdepttype;
	}
	public void setCtorgtype(String ctorgtype) {
		this.ctorgtype = ctorgtype;
	}

	public String getCtorgtype() {
		return ctorgtype;
	}
	public void setCtdeptlevel(String ctdeptlevel) {
		this.ctdeptlevel = ctdeptlevel;
	}

	public String getCtdeptlevel() {
		return ctdeptlevel;
	}
	public void setShownum(String shownum) {
		this.shownum = shownum;
	}

	public String getShownum() {
		return shownum;
	}
	public void setDeptstatus(String deptstatus) {
		this.deptstatus = deptstatus;
	}

	public String getDeptstatus() {
		return deptstatus;
	}
	public void setCtorgmanager(String ctorgmanager) {
		this.ctorgmanager = ctorgmanager;
	}

	public String getCtorgmanager() {
		return ctorgmanager;
	}
	public void setCtvicemanager(String ctvicemanager) {
		this.ctvicemanager = ctvicemanager;
	}

	public String getCtvicemanager() {
		return ctvicemanager;
	}
	public void setIsCntrtMgmtUnit(String isCntrtMgmtUnit) {
		this.isCntrtMgmtUnit = isCntrtMgmtUnit;
	}

	public String getIsCntrtMgmtUnit() {
		return isCntrtMgmtUnit;
	}
	public void setCntrtMgmtModeFlag(String cntrtMgmtModeFlag) {
		this.cntrtMgmtModeFlag = cntrtMgmtModeFlag;
	}

	public String getCntrtMgmtModeFlag() {
		return cntrtMgmtModeFlag;
	}
	public void setCntrtMgmtUnitCd(String cntrtMgmtUnitCd) {
		this.cntrtMgmtUnitCd = cntrtMgmtUnitCd;
	}

	public String getCntrtMgmtUnitCd() {
		return cntrtMgmtUnitCd;
	}
	public void setCntrtMgmtUnitNm(String cntrtMgmtUnitNm) {
		this.cntrtMgmtUnitNm = cntrtMgmtUnitNm;
	}

	public String getCntrtMgmtUnitNm() {
		return cntrtMgmtUnitNm;
	}
	public void setCntrtMgmtType(String cntrtMgmtType) {
		this.cntrtMgmtType = cntrtMgmtType;
	}

	public String getCntrtMgmtType() {
		return cntrtMgmtType;
	}
	public void setCntrtMgmtCheckType(String cntrtMgmtCheckType) {
		this.cntrtMgmtCheckType = cntrtMgmtCheckType;
	}

	public String getCntrtMgmtCheckType() {
		return cntrtMgmtCheckType;
	}
	public void setCntrtMgmtUnitLevel(String cntrtMgmtUnitLevel) {
		this.cntrtMgmtUnitLevel = cntrtMgmtUnitLevel;
	}

	public String getCntrtMgmtUnitLevel() {
		return cntrtMgmtUnitLevel;
	}
	public void setCntrtMgmtState(String cntrtMgmtState) {
		this.cntrtMgmtState = cntrtMgmtState;
	}

	public String getCntrtMgmtState() {
		return cntrtMgmtState;
	}

	public Long getParkId() {
		return parkId;
	}

	public void setParkId(Long parkId) {
		this.parkId = parkId;
	}
}
