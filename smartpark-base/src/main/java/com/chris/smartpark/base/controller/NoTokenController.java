package com.chris.smartpark.base.controller;

import com.chris.base.common.exception.CommonException;
import com.chris.base.common.utils.CacheDataUtils;
import com.chris.base.common.utils.CommonResponse;
import com.chris.base.common.utils.ValidateUtils;
import com.chris.base.modules.sys.entity.SysDataDictEntity;
import com.chris.smartpark.base.entity.BaseParkEntity;
import com.chris.smartpark.base.service.BaseParkService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NoTokenController {
    @Autowired
    private BaseParkService baseParkService;

    @Autowired
    private CacheDataUtils cacheDataUtils;

    @RequestMapping("/basepark/list.notoken")
    public CommonResponse list(){
        List<BaseParkEntity> baseParkList = baseParkService.queryList(Collections.EMPTY_MAP);

        return CommonResponse.ok().setData(baseParkList);
    }

    @RequestMapping("/sys/datadict/queryByType.notoken")
    public CommonResponse queryByType(@RequestParam String type) {
        if (ValidateUtils.isEmptyString(type)) {
            throw new CommonException("类别为空");
        }
        List<SysDataDictEntity> dataDicts = this.cacheDataUtils.getDataDictList();
        List<SysDataDictEntity> resultList = dataDicts.stream().filter(item -> ValidateUtils.equals(item.getType(), type)).collect(Collectors.toList());
        return CommonResponse.ok().put("list", resultList);
    }
}
