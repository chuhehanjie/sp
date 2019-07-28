package com.chris.smartpark.base.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 员工用户
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 15.18
 */
public class BaseStaffEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//bigint
	private Long id;
	//门户UID如: EIP+员工编码删除唯一标识
	private String srcusercode;
	//门户帐号删除唯一标识
	private String talias;
	//身份证号码删除唯一标识
	private String idcardnumber;
	//员工编码HR删除用户唯一标识
	private String employnum;
	//姓名
	private String username;
	//性别参照 6.216.21.	性别（GENDER）
	private String gender;
	//所在组织唯一编码
	private String sdeptcode;
	//岗位层级编6.226.22.	岗位层级（POSITIONCODE）
	private String positioncode;
	//岗位名称
	private String title;
	//职务编码6.196.19.	职务编码（TITLECODE）
	private String titlecode;
	//邮件地址
	private String email;
	//联系方式/手机号
	private String mobile;
	//办公电话
	private String officetel;
	//用户类型6.236.23.	用户类型（CTUSERTYPE）
	private String ctusertype;
	//用户状态1：有效 0：无效
	private String status;
	//原所在组织唯一编码 用户修改组织时
	private String oldsdeptcode;
	//承包模式6.256.25.	承包模式（CONTRACT_TYPE）
	private String contractType;
	//数据类型参照数据字典7.1, 7.1.	对象类型（dataType）数据类型为用户、用户主职、用户兼职、用户借调
	private String datatype;
	//HR业务操作类型7.27.2.	HR操作类型（hrOperType）
	private String hropertype;
	//对应操作类型7.37.3.	对应操作类型（operType）
	private String opertype;
	//员工编号即ctHrUserCode
	private String unicode;
	//hr记录主键对应用户所在的一个组织
	private String hrid;
	//集团用户帐号全省唯一，员工号@两位省公司编码 集团删除唯一标识
            
	private String loginname;
	//所在集团组织的唯一编码两位省公司编码+8位流水号
	private String parentcode;
	//公司编码两位省公司编码+8位流水号
	private String ctcorpcode;
	//人员状态6.156.15.	人员状态（ctStatus）
	private String ctstatus;
	//生日yyyy-MM-dd
	private String ctbirthday;
	//岗位等级体系新岗位体系、旧岗位体系参照 6.7 6.7.	岗位等级体系（ctPosLevelType）
	private String ctposleveltype;
	//岗位等级如果岗位体系选择为新岗位体系则参照岗位等级（新），如果岗位体系选择为旧岗位体系则参照岗位等级（旧） 参照6.8/6.9 岗位等级-旧（ctPositionLevel） 6.9.岗位等级-新（ctPositionLevel）
	private String ctpositionlevel;
	//岗位层级体系新岗位层级体系、旧岗位层级体系 参照 6.11 岗位层级体系（ctPosLayerType）
            
	private String ctposlayertype;
	//岗位层级参照数据字典6.12/6.13 参照 6.11 6.12.	岗位层级-旧（ctPositionLayer） 6.13.	岗位层级-新（ctPositionLayer）
	private String ctpositionlayer;
	//岗位序列6.146.14.	岗位序列（ctPositionSequence）
	private String ctpositionsequence;
	//参照数据字典6.46.4.	性别（ctGender）
	private String ctgender;
	//备用帐号
	private String reserveaccount;
	//1正常、2停用用户状态2
	private String userstatus;
	//参照 6.6, 由于与四级划小同步规范中的属性重名,由ctUserType改为gctUserType6.6.	员工分类（gctUserType）
	private String gctusertype;
	//同级排序编号用户部门排序号 支持浮点
	private String shownum;
	//任职类型6.5区分是否主职组织6.5.	任职类型（pType）
	private String ptype;
	//职务用户部门职务名称
	private String cttitle;
	//用户部门邮件地址
	private String ctmail;
	//用户部门基准岗位
	private String ctpositiontype;
	//用户部门手机号
	private String ctpreferredmobile;
	//用户部门办公电话
	private String cttelephonenumber;
	//用户部门岗位名称
	private String ctpositionname;
	//是否离退休用户非空值-是离退休、空值-不是离退休
	private String retire;
	//统一邮箱
	private String groupmail;
	//注册人员来源系统
	private String registersystem;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
	public void setSrcusercode(String srcusercode) {
		this.srcusercode = srcusercode;
	}

	public String getSrcusercode() {
		return srcusercode;
	}
	public void setTalias(String talias) {
		this.talias = talias;
	}

	public String getTalias() {
		return talias;
	}
	public void setIdcardnumber(String idcardnumber) {
		this.idcardnumber = idcardnumber;
	}

	public String getIdcardnumber() {
		return idcardnumber;
	}
	public void setEmploynum(String employnum) {
		this.employnum = employnum;
	}

	public String getEmploynum() {
		return employnum;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return gender;
	}
	public void setSdeptcode(String sdeptcode) {
		this.sdeptcode = sdeptcode;
	}

	public String getSdeptcode() {
		return sdeptcode;
	}
	public void setPositioncode(String positioncode) {
		this.positioncode = positioncode;
	}

	public String getPositioncode() {
		return positioncode;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	public void setTitlecode(String titlecode) {
		this.titlecode = titlecode;
	}

	public String getTitlecode() {
		return titlecode;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}
	public void setOfficetel(String officetel) {
		this.officetel = officetel;
	}

	public String getOfficetel() {
		return officetel;
	}
	public void setCtusertype(String ctusertype) {
		this.ctusertype = ctusertype;
	}

	public String getCtusertype() {
		return ctusertype;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
	public void setOldsdeptcode(String oldsdeptcode) {
		this.oldsdeptcode = oldsdeptcode;
	}

	public String getOldsdeptcode() {
		return oldsdeptcode;
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
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}

	public String getHrid() {
		return hrid;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getLoginname() {
		return loginname;
	}
	public void setParentcode(String parentcode) {
		this.parentcode = parentcode;
	}

	public String getParentcode() {
		return parentcode;
	}
	public void setCtcorpcode(String ctcorpcode) {
		this.ctcorpcode = ctcorpcode;
	}

	public String getCtcorpcode() {
		return ctcorpcode;
	}
	public void setCtstatus(String ctstatus) {
		this.ctstatus = ctstatus;
	}

	public String getCtstatus() {
		return ctstatus;
	}
	public void setCtbirthday(String ctbirthday) {
		this.ctbirthday = ctbirthday;
	}

	public String getCtbirthday() {
		return ctbirthday;
	}
	public void setCtposleveltype(String ctposleveltype) {
		this.ctposleveltype = ctposleveltype;
	}

	public String getCtposleveltype() {
		return ctposleveltype;
	}
	public void setCtpositionlevel(String ctpositionlevel) {
		this.ctpositionlevel = ctpositionlevel;
	}

	public String getCtpositionlevel() {
		return ctpositionlevel;
	}
	public void setCtposlayertype(String ctposlayertype) {
		this.ctposlayertype = ctposlayertype;
	}

	public String getCtposlayertype() {
		return ctposlayertype;
	}
	public void setCtpositionlayer(String ctpositionlayer) {
		this.ctpositionlayer = ctpositionlayer;
	}

	public String getCtpositionlayer() {
		return ctpositionlayer;
	}
	public void setCtpositionsequence(String ctpositionsequence) {
		this.ctpositionsequence = ctpositionsequence;
	}

	public String getCtpositionsequence() {
		return ctpositionsequence;
	}
	public void setCtgender(String ctgender) {
		this.ctgender = ctgender;
	}

	public String getCtgender() {
		return ctgender;
	}
	public void setReserveaccount(String reserveaccount) {
		this.reserveaccount = reserveaccount;
	}

	public String getReserveaccount() {
		return reserveaccount;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}

	public String getUserstatus() {
		return userstatus;
	}
	public void setGctusertype(String gctusertype) {
		this.gctusertype = gctusertype;
	}

	public String getGctusertype() {
		return gctusertype;
	}
	public void setShownum(String shownum) {
		this.shownum = shownum;
	}

	public String getShownum() {
		return shownum;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getPtype() {
		return ptype;
	}
	public void setCttitle(String cttitle) {
		this.cttitle = cttitle;
	}

	public String getCttitle() {
		return cttitle;
	}
	public void setCtmail(String ctmail) {
		this.ctmail = ctmail;
	}

	public String getCtmail() {
		return ctmail;
	}
	public void setCtpositiontype(String ctpositiontype) {
		this.ctpositiontype = ctpositiontype;
	}

	public String getCtpositiontype() {
		return ctpositiontype;
	}
	public void setCtpreferredmobile(String ctpreferredmobile) {
		this.ctpreferredmobile = ctpreferredmobile;
	}

	public String getCtpreferredmobile() {
		return ctpreferredmobile;
	}
	public void setCttelephonenumber(String cttelephonenumber) {
		this.cttelephonenumber = cttelephonenumber;
	}

	public String getCttelephonenumber() {
		return cttelephonenumber;
	}
	public void setCtpositionname(String ctpositionname) {
		this.ctpositionname = ctpositionname;
	}

	public String getCtpositionname() {
		return ctpositionname;
	}
	public void setRetire(String retire) {
		this.retire = retire;
	}

	public String getRetire() {
		return retire;
	}
	public void setGroupmail(String groupmail) {
		this.groupmail = groupmail;
	}

	public String getGroupmail() {
		return groupmail;
	}
	public void setRegistersystem(String registersystem) {
		this.registersystem = registersystem;
	}

	public String getRegistersystem() {
		return registersystem;
	}
}
