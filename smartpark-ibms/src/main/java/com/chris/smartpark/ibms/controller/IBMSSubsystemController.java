package com.chris.smartpark.ibms.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.smartpark.ibms.entity.IBMSSubsystemEntity;
import com.chris.smartpark.ibms.entity.IBMSSubsystemStateEntity;
import com.chris.smartpark.ibms.service.IBMSSubsystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * ibms子系统表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 06.18
 */
@RestController
@RequestMapping("/app/ibms/ibmssubsystem")
@Api("子系统接口")
public class IBMSSubsystemController {
    @Autowired
    private IBMSSubsystemService ibmsSubsystemService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表", notes = "查询列表")
    public CommonResponse list() {

        List<IBMSSubsystemEntity> ibmsSubsystemList = ibmsSubsystemService.queryList(null);

        return CommonResponse.ok().setData(ibmsSubsystemList);
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/listState", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表", notes = "查询列表")
    public CommonResponse listState() {
        List<IBMSSubsystemStateEntity> ibmsSubsystemStateList = ibmsSubsystemService.queryListState();
        return CommonResponse.ok().setData(ibmsSubsystemStateList);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询子系统", notes = "根据ID查询子系统")
    public CommonResponse info(@PathVariable("id") Integer id) {
        IBMSSubsystemEntity ibmsSubsystem = ibmsSubsystemService.queryObject(id);
        return CommonResponse.ok().setData(ibmsSubsystem);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存", notes = "保存")
    public CommonResponse save(@RequestBody IBMSSubsystemEntity ibmsSubsystem) {
        ibmsSubsystemService.save(ibmsSubsystem);

        return CommonResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改")
    public CommonResponse update(@RequestBody IBMSSubsystemEntity ibmsSubsystem) {
        ibmsSubsystemService.update(ibmsSubsystem);

        return CommonResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除", notes = "删除")
    public CommonResponse delete(@RequestBody Integer[] ids) {
        ibmsSubsystemService.deleteBatch(ids);

        return CommonResponse.ok();
    }

}
