package com.chris.smartpark.ibms.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.smartpark.ibms.entity.IBMSDevConnectRecordEntity;
import com.chris.smartpark.ibms.service.IBMSDevConnectRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 设备连接记录表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@RestController
@RequestMapping("/app/ibms/ibmsdevconnectrecord")
@Api("设备连接记录接口")
public class IBMSDevConnectRecordController {
    @Autowired
    private IBMSDevConnectRecordService ibmsDevConnectRecordService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表数据", notes = "查询列表数据")
    public CommonResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<IBMSDevConnectRecordEntity> ibmsDevConnectRecordList = ibmsDevConnectRecordService.queryList(query);
        int total = ibmsDevConnectRecordService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(ibmsDevConnectRecordList, total, query.getLimit(), query.getPage());

        return CommonResponse.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    public CommonResponse info(@PathVariable("id") Integer id) {
        IBMSDevConnectRecordEntity ibmsDevConnectRecord = ibmsDevConnectRecordService.queryObject(id);

        return CommonResponse.ok().put("ibmsDevConnectRecord", ibmsDevConnectRecord);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存", notes = "保存")
    public CommonResponse save(@RequestBody IBMSDevConnectRecordEntity ibmsDevConnectRecord) {
        ibmsDevConnectRecordService.save(ibmsDevConnectRecord);

        return CommonResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改")
    public CommonResponse update(@RequestBody IBMSDevConnectRecordEntity ibmsDevConnectRecord) {
        ibmsDevConnectRecordService.update(ibmsDevConnectRecord);

        return CommonResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除", notes = "删除")
    public CommonResponse delete(@RequestBody Integer[] ids) {
        ibmsDevConnectRecordService.deleteBatch(ids);

        return CommonResponse.ok();
    }

}
