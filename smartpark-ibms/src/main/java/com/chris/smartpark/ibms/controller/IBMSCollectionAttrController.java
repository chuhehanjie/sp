package com.chris.smartpark.ibms.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.smartpark.ibms.entity.IBMSCollectionAttrEntity;
import com.chris.smartpark.ibms.service.IBMSCollectionAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 数据采集表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@RestController
@RequestMapping("/app/ibms/collectionattr")
@Api("数据采集接口")
public class IBMSCollectionAttrController {
    @Autowired
    private IBMSCollectionAttrService ibmsCollectionAttrService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表数据", notes = "查询列表数据")
    public CommonResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<IBMSCollectionAttrEntity> ibmsCollectionAttrList = ibmsCollectionAttrService.queryList(query);
        int total = ibmsCollectionAttrService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(ibmsCollectionAttrList, total, query.getLimit(), query.getPage());

        return CommonResponse.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    public CommonResponse info(@PathVariable("id") Integer id) {
        IBMSCollectionAttrEntity ibmsCollectionAttr = ibmsCollectionAttrService.queryObject(id);

        return CommonResponse.ok().put("ibmsCollectionAttr", ibmsCollectionAttr);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存", notes = "保存")
    public CommonResponse save(@RequestBody IBMSCollectionAttrEntity ibmsCollectionAttr) {
        ibmsCollectionAttrService.save(ibmsCollectionAttr);

        return CommonResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改")
    public CommonResponse update(@RequestBody IBMSCollectionAttrEntity ibmsCollectionAttr) {
        ibmsCollectionAttrService.update(ibmsCollectionAttr);

        return CommonResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除", notes = "删除")
    public CommonResponse delete(@RequestBody Integer[] ids) {
        ibmsCollectionAttrService.deleteBatch(ids);

        return CommonResponse.ok();
    }

}
