package com.chris.smartpark.busi.dao;

import com.chris.base.modules.sys.dao.BaseDao;
import com.chris.smartpark.busi.entity.VisitorInfoHisEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 访客信息历史表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
@Mapper
public interface VisitorInfoHisDao extends BaseDao<VisitorInfoHisEntity> {

    List<VisitorInfoHisEntity> queryByIdcardNo(String idcardNo);
}
