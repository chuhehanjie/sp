package com.chris.smartpark.busi.dao;

import com.chris.smartpark.busi.entity.VisitorInfoEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 访客信息表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
@Mapper
public interface VisitorInfoDao extends BaseDao<VisitorInfoEntity> {
    VisitorInfoEntity selectByIdcardNo(VisitorInfoEntity visitorInfo);
}
