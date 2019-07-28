package com.chris.smartpark.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.smartpark.base.entity.BaseParkEntity;
import com.chris.smartpark.base.service.BaseParkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 园区信息表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@RestController
@RequestMapping("/basepark")
@Api("园区信息接口")
public class BaseParkController {

    /**
     * 日志打印
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseParkController.class);

    private static final Integer DEFAULT_PARK = 1;

    @Autowired
    private BaseParkService baseParkService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "查询列表数据", notes = "查询列表数据")
    public CommonResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<BaseParkEntity> baseParkList = baseParkService.queryList(query);
        int total = baseParkService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(baseParkList, total, query.getLimit(), query.getPage());

        return CommonResponse.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info.notoken", method = RequestMethod.POST)
    @ApiOperation(value = "根据ID查询园区", notes = "根据ID查询园区")
    public CommonResponse info(Integer id) {
        if(ValidateUtils.isEmpty(id)){
            id = DEFAULT_PARK;
        }
        BaseParkEntity basePark = baseParkService.queryObject(id);
        LOGGER.info("park info :{}", JSONObject.toJSONString(basePark));
        return CommonResponse.ok().setData(basePark);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存", notes = "保存")
    public CommonResponse save(@RequestBody BaseParkEntity basePark) {
        baseParkService.save(basePark);

        return CommonResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改")
    public CommonResponse update(@RequestBody BaseParkEntity basePark) {
        baseParkService.update(basePark);

        return CommonResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "删除", notes = "删除")
    public CommonResponse delete(@RequestBody Integer[] ids) {
        baseParkService.deleteBatch(ids);

        return CommonResponse.ok();
    }

}
