package com.chris.smartpark.busi.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.smartpark.busi.common.VisitorConstants;
import com.chris.smartpark.busi.dto.*;
import com.chris.smartpark.busi.entity.VisitorReservationEntity;
import com.chris.smartpark.busi.service.CarInfoService;
import com.chris.smartpark.busi.service.VisitorInfoService;
import com.chris.smartpark.busi.service.VisitorReservationService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 批量预约单处理
 * @author ziqian.zhang
 * @email 643723180@qq.com
 * @since Nov 28.18
 */
@Slf4j
@RestController
@RequestMapping("/app/batchreservation")
public class BatchReservationController {
	@Autowired
	private VisitorReservationService visitorReservationService;
	@Autowired
	private VisitorInfoService visitorInfoService;
	@Autowired
	private CarInfoService carInfoService;

	/**
	 * 分页查询预约单
	 * @param params
	 * @return
	 */
	@PostMapping("/list")
	public CommonResponse list(@RequestBody Map<String, Object> params){
		Query query = new Query(params);
		List<VisitorReservationEntity> resultList = this.visitorReservationService.queryList(query);
		int total = this.visitorReservationService.queryTotal(params);
		PageUtils pageUtil = new PageUtils(resultList, total, query.getLimit(), query.getPage());
		return CommonResponse.ok().put(VisitorConstants.Keys.PAGE, pageUtil);
	}
	@PostMapping("/batchImport")
	public CommonResponse batchImport(@RequestParam("file") MultipartFile file) {
		//ExcelImportUtil.importExcel()

		//MultipartFile 转 file
		// 获取文件名
		String fileName = file.getOriginalFilename();
		// 获取文件后缀
		String prefix=fileName.substring(fileName.lastIndexOf("."));
		// 用uuid作为文件名，防止生成的临时文件重复
		final File excelFile;
		try {
			excelFile = File.createTempFile(UUID.randomUUID().toString().replaceAll("-",""), prefix);
			file.transferTo(excelFile);
		} catch (IOException e) {
			e.printStackTrace();
			return CommonResponse.error(e.getMessage());
		}
		//File excelFile=new File("D:\\批量预约模板.xlsx");
		visitorReservationService.batchImportReservation(excelFile);

		//程序结束时，删除临时文件
		deleteFile(excelFile);
		return CommonResponse.ok();
	}
	/**
	 * 删除
	 *
	 * @param files
	 */
	private void deleteFile(File... files) {
		for (File file : files) {
			if (file.exists()) {
				file.delete();
			}
		}
	}

	public static void main(String[] args) {
		ImportParams importParams = new ImportParams();
		//设置需要校验
        importParams.setNeedVerfiy(true);
        importParams.setVerfiyGroup(new Class[]{BatchReservationImportDTO.ReservationImportValidGroup.class});
		// 设置读取第一个 sheet 页（预约单信息）
		importParams.setStartSheetIndex(0);
        ExcelImportResult<BatchReservationImportValidateDTO> reservationImportResult = ExcelImportUtil.importExcelMore(new File("D:\\批量预约模板.xlsx"), BatchReservationImportValidateDTO.class, importParams);
        List<BatchReservationImportValidateDTO> reservationImportList = reservationImportResult.getList();
        System.out.println("校验正确的列表：" + JSONObject.toJSONString(reservationImportList));

        System.out.println("是否校验失败 = " + reservationImportResult.isVerfiyFail());
        if (ValidateUtils.isNotEmptyCollection(reservationImportResult.getFailList())) {
            reservationImportResult.getFailList().forEach(item -> {
                System.out.println("错误消息：" + item.getErrorMsg());
            });

        }
        //获取校验结果

		// 设置读取第二个 sheet 页（车辆信息）
		importParams.setStartSheetIndex(1);
        List<BatchCarInfoImportDTO> carInfoImportDTOS = ExcelImportUtil.importExcel(new File("D:\\批量预约模板.xlsx"), BatchCarInfoImportDTO.class, importParams);
        System.out.println(JSONObject.toJSONString(carInfoImportDTOS));

        // 设置读取第三个 sheet 页（同行人信息）
		importParams.setStartSheetIndex(2);
        List<BatchCompanionsImportDTO> companionsImportDTOS = ExcelImportUtil.importExcel(new File("D:\\批量预约模板.xlsx"), BatchCompanionsImportDTO.class, importParams);
        System.out.println(JSONObject.toJSONString(companionsImportDTOS));

	}

}
