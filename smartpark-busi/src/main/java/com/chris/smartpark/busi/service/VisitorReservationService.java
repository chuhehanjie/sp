package com.chris.smartpark.busi.service;

import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.smartpark.busi.dto.AuthIdCardDTO;
import com.chris.smartpark.busi.dto.ReservationOrderApproveDTO;
import com.chris.smartpark.busi.dto.ReservationOrderDTO;
import com.chris.smartpark.busi.entity.VisitorIdcardEntity;
import com.chris.smartpark.busi.entity.VisitorReservationEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 访客预约登记单
 * 
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Nov 11.18
 */
public interface VisitorReservationService {

	ReservationOrderDTO queryReservationOrderDetail(Long id);
	
	List<VisitorReservationEntity> queryList(Map<String, Object> map);

	/**
	 * 根据openid分页查询员工对应的预约单
	 * @param params
	 * @return
	 */
	PageUtils queryReservationOrdersByOpenId(Map<String, Object> params);

	List<VisitorReservationEntity> queryEffectRecord(String  idcardNo);

	int queryTotal(Map<String, Object> map);
	
	void save(VisitorReservationEntity visitorReservation);
	
	void update(VisitorReservationEntity visitorReservation);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
	/**
	 * 定时任务
	 * @return
	 */
	void sendSMSPrompt(String beforeHours);
	@Transactional
	void change2Overdue();
	@Transactional
    Long createReservationOrder(ReservationOrderDTO reservationOrderDTO);
	@Transactional
	void checkIdCardAndGetAuth(VisitorIdcardEntity visitorIdcardEntity);

	CommonResponse checkIdCard(AuthIdCardDTO authIdCardDTO);
	@Transactional
	CommonResponse saveCardAndGetAuth(AuthIdCardDTO authIdCardDTO);

	VisitorReservationEntity queryObjectById(Long id);

	@Transactional
	void approve(ReservationOrderApproveDTO authorizeDTO);

	/**
	 * 根据身份证号和状态查询预约单
	 * @param idcardNo
	 * @param status
	 * @return
	 */
    List<VisitorReservationEntity> queryByIdcardAndStatus(String idcardNo, String status);

	/**
	 * 批量导入预约单
	 * @param excelFile
	 */
	@Transactional
	void batchImportReservation(File excelFile);

	/**
	 * 上传访客照片
	 * @param file 文件对象
	 * @param reservationOrderId 预约单ID
	 */
	@Transactional
    void uploadVisitorPhoto(MultipartFile file, String reservationOrderId) throws IOException;

    PageUtils searchReservationOrders(Map<String, Object> params);
}
