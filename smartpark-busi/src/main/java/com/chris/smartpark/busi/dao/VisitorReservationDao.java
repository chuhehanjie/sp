package com.chris.smartpark.busi.dao;

import com.chris.base.common.utils.Query;
import com.chris.smartpark.busi.dto.CosonDoorCtrlReqDTO;
import com.chris.smartpark.busi.dto.ReservationOrderQryDTO;
import com.chris.smartpark.busi.entity.VisitorReservationEntity;
import com.chris.base.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 访客预约登记单
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 04.18
 */
@Mapper
public interface VisitorReservationDao extends BaseDao<VisitorReservationEntity> {
    List<VisitorReservationEntity> queryEffectRecord(String idcardNo);

    /**
     * 更新预约单审核不通过原因和状态
     * @param visitorReservationEntity
     */
    void updateRejectReasonAndStatus(VisitorReservationEntity visitorReservationEntity);

    void updateStatus(VisitorReservationEntity visitorReservationEntity);

    List<VisitorReservationEntity> queryByIdcardAndStatus(@Param("idcardNo") String idcardNo, @Param("status") String status);

    List<ReservationOrderQryDTO> queryReservationOrdersByOpenId(Map<String, Object> params);

    List<VisitorReservationEntity> queryByStatusAndTime();

    List<VisitorReservationEntity> queryByStatusAndTime2(@Param("beforeHours") String beforeHours);

    int countReservationOrdersByOpenId(Map<String, Object> params);

    int countReservationOrders(Query query);

    List<ReservationOrderQryDTO> searchReservationOrders(Query query);

    List<CosonDoorCtrlReqDTO> queryCosonDoorCtrlParams(Long id);
}
