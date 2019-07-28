package com.chris.smartpark.base.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 28.19
 */
public class TbUserEntity  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long userId;
	//用户名
	private String username;
	//手机号
	private String mobile;
	//密码
	private String password;
	//微信openid
	private String openId;
	//创建时间
	private Date createTime;

	private Long roleId;

	private String roleName;

	public TbUserEntity() {
	}

	public TbUserEntity(String openId, String username) {
		this.openId = openId;
		this.username = username;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return mobile;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getOpenId() {
		return openId;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
