package com.chris.smartpark.base.controller;

import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.PageUtils;
import com.chris.base.common.utils.Query;
import com.chris.smartpark.base.entity.BaseRoomEntity;
import com.chris.smartpark.base.service.BaseRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 园区房间信息表
 *
 * @author chris
 * @email forzamilan0607@gmail.com
 * @since Oct 16.18
 */
@RestController
@RequestMapping("/app/base/room")
@Api("园区房间信息接口")
public class BaseRoomController {
    @Autowired
    private BaseRoomService baseRoomService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询列表数据", notes = "查询列表数据")
    public CommonResponse list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        Query query = new Query(params);

        List<BaseRoomEntity> baseRoomList = baseRoomService.queryList(query);
        int total = baseRoomService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(baseRoomList, total, query.getLimit(), query.getPage());

        return CommonResponse.ok().put("page", pageUtil);
    }


    /**
     * 信息
     */
    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID查询房间", notes = "根据ID查询房间")
    public CommonResponse info(@PathVariable("id") Integer id) {
        BaseRoomEntity baseRoom = baseRoomService.queryObject(id);

        return CommonResponse.ok().put("baseRoom", baseRoom);
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation(value = "保存", notes = "保存")
    public CommonResponse save(@RequestBody BaseRoomEntity baseRoom) {
        baseRoomService.save(baseRoom);

        return CommonResponse.ok();
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改", notes = "修改")
    public CommonResponse update(@RequestBody BaseRoomEntity baseRoom) {
        baseRoomService.update(baseRoom);

        return CommonResponse.ok();
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ApiOperation(value = "删除", notes = "删除")
    public CommonResponse delete(@RequestBody Integer[] ids) {
        baseRoomService.deleteBatch(ids);

        return CommonResponse.ok();
    }

}
