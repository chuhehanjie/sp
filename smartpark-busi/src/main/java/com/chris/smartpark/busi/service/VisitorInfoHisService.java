package com.chris.smartpark.busi.service;

import com.chris.smartpark.busi.dto.UserAndCarsDTO;
import com.chris.smartpark.busi.entity.VisitorInfoHisEntity;

import java.util.List;
import java.util.Map;

/**
 * 访客信息历史表
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public interface VisitorInfoHisService {
	
	VisitorInfoHisEntity queryObject(Long id);

	VisitorInfoHisEntity queryByReservationId(Long reservationId);

	List<VisitorInfoHisEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(VisitorInfoHisEntity visitorInfoHis);
	
	void update(VisitorInfoHisEntity visitorInfoHis);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    List<VisitorInfoHisEntity> queryByIdcardNo(String idcardNo);

}
