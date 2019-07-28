package com.chris.smartpark.base.dao;

import com.chris.smartpark.base.entity.TbUserEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户
 * 
 * @author chris
 * @email 258321511@qq.com
 * @since May 28.19
 */
@Mapper
public interface TbUserDao extends BaseDao<TbUserEntity> {

    void updateUsernameByOpenId(TbUserEntity user);
}
