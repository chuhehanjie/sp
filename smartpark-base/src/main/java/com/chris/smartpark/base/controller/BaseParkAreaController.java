package com.chris.smartpark.base.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.smartpark.base.entity.BaseParkAreaEntity;
import com.chris.smartpark.base.service.BaseParkAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 园区区域信息表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@RestController
@RequestMapping("/app/base/parkarea")
@Api("园区区域信息接口")
public class BaseParkAreaController {
    @Autowired
    private BaseParkAreaService baseParkAreaService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表数据", notes = "查询列表数据")
    public CommonResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<BaseParkAreaEntity> baseParkAreaList = baseParkAreaService.queryList(query);
        int total = baseParkAreaService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(baseParkAreaList, total, query.getLimit(), query.getPage());

        return CommonResponse.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询区域", notes = "根据ID查询区域")
    public CommonResponse info(@PathVariable("id") Integer id) {
        BaseParkAreaEntity baseParkArea = baseParkAreaService.queryObject(id);

        return CommonResponse.ok().put("baseParkArea", baseParkArea);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存", notes = "保存")
    public CommonResponse save(@RequestBody BaseParkAreaEntity baseParkArea) {
        baseParkAreaService.save(baseParkArea);

        return CommonResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改")
    public CommonResponse update(@RequestBody BaseParkAreaEntity baseParkArea) {
        baseParkAreaService.update(baseParkArea);

        return CommonResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除", notes = "删除")
    public CommonResponse delete(@RequestBody Integer[] ids) {
        baseParkAreaService.deleteBatch(ids);

        return CommonResponse.ok();
    }

}
