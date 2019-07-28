package com.chris.smartpark.ibms.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.smartpark.ibms.entity.IBMSDevAlarmRecordEntity;
import com.chris.smartpark.ibms.service.IBMSDevAlarmRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 设备报警记录表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@RestController
@RequestMapping("/app//ibms/devalarmrecord")
@Api("设备报警记录接口")
public class IBMSDevAlarmRecordController {
    @Autowired
    private IBMSDevAlarmRecordService ibmsDevAlarmRecordService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表数据", notes = "查询列表数据")
    public CommonResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<IBMSDevAlarmRecordEntity> ibmsDevAlarmRecordList = ibmsDevAlarmRecordService.queryList(query);
        int total = ibmsDevAlarmRecordService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(ibmsDevAlarmRecordList, total, query.getLimit(), query.getPage());

        return CommonResponse.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    public CommonResponse info(@PathVariable("id") Integer id) {
        IBMSDevAlarmRecordEntity ibmsDevAlarmRecord = ibmsDevAlarmRecordService.queryObject(id);

        return CommonResponse.ok().put("ibmsDevAlarmRecord", ibmsDevAlarmRecord);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存", notes = "保存")
    public CommonResponse save(@RequestBody IBMSDevAlarmRecordEntity ibmsDevAlarmRecord) {
        ibmsDevAlarmRecordService.save(ibmsDevAlarmRecord);

        return CommonResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改")
    public CommonResponse update(@RequestBody IBMSDevAlarmRecordEntity ibmsDevAlarmRecord) {
        ibmsDevAlarmRecordService.update(ibmsDevAlarmRecord);

        return CommonResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除", notes = "删除")
    public CommonResponse delete(@RequestBody Integer[] ids) {
        ibmsDevAlarmRecordService.deleteBatch(ids);

        return CommonResponse.ok();
    }

}
