package com.chris.smartpark;

import com.alibaba.fastjson.JSONObject;
import com.chris.IBMSApplication;
import com.chris.base.modules.sys.service.SysMenuService;
import com.chris.smartpark.ibms.entity.IBMSSubsystemEntity;
import com.chris.smartpark.ibms.service.IBMSSubsystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = IBMSApplication.class)
public class IBMSTest {
    @Autowired
    private IBMSSubsystemService ibmsSubsystemService;

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void querySubsystem() {
        List<IBMSSubsystemEntity> data = this.ibmsSubsystemService.queryList(new HashMap<>());
        for (int i = 0; i < data.size(); i++) {
            System.out.println("第" + (i + 1) + "个子系统——" + JSONObject.toJSONString(data.get(i)));
        }
        System.out.println("查询系统菜单列表：" + JSONObject.toJSONString(this.sysMenuService.queryList(new HashMap<>())));
    }
}
