package com.chris.smartpark;

import com.alibaba.fastjson.JSONObject;
import com.chris.IBMSApplication;
import com.chris.smartpark.common.Attribute;
import com.chris.smartpark.ibms.dao.IBMSDevCollectionRecordDao;
import com.chris.smartpark.ibms.entity.IBMSDetailEntity;
import com.chris.smartpark.ibms.entity.IBMSDevCollectionRecordEntity;
import com.chris.smartpark.ibms.service.IBMSDevCollectionRecordService;
import com.chris.smartpark.ibms.service.IBMSService;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hewei
 * @version V.1.0.0
 * @date Created in 14:35 2018/10/30
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = IBMSApplication.class)
public class IBMSDevCollectionTest {

    @Autowired
    private IBMSDevCollectionRecordService ibmsDevCollectionRecordService;
    @Autowired
    private IBMSDevCollectionRecordDao ibmsDevCollectionRecordDao;
    @Autowired
    private IBMSService ibmsService;

    @Test
    public void countElectricity() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("type", 2);
        map.put("date", "2018-09");
        System.out.println(ibmsDevCollectionRecordDao.countElectricity(map));
        ibmsDevCollectionRecordService.countElectricity(new HashMap<>());
    }

    @Test
    public void getYesterday() {
//        Calendar cal=Calendar.getInstance();
//        System.out.println(new SimpleDateFormat("yyyy-MM").format(cal.getTime()));
//        cal.add(Calendar.DATE,-1);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
//        cal.add(Calendar.DATE,1);
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
//        cal.add(Calendar.MONTH,-1);
//        System.out.println(new SimpleDateFormat("yyyy-MM").format(cal.getTime()));
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        String first = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        System.out.println(c.getTime());
        System.out.println("===============first:" + first);

        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime());
        System.out.println("===============last:" + last);
    }

    @Test
    public void detail() {
        IBMSDetailEntity detail = ibmsService.detail();
        System.out.println(JSONObject.toJSONString(detail));
    }

    @Test
    public void addElectricRecord() {
        Calendar c = Calendar.getInstance();
        for (int i = 1; i <= c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            IBMSDevCollectionRecordEntity ibmsDevCollectionRecordEntity = new IBMSDevCollectionRecordEntity();
            ibmsDevCollectionRecordEntity.setAttrId(Attribute.ELECTRIC.getAttrId());
            ibmsDevCollectionRecordEntity.setSubsystemId(1);
            ibmsDevCollectionRecordEntity.setDeviceId(1);
            ibmsDevCollectionRecordEntity.setValue("80");
            c.add(Calendar.MONTH, 0);
            c.set(Calendar.DAY_OF_MONTH, i);//设置为1号,当前日期既为本月第一天
            //String first = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(c.getTime());
            ibmsDevCollectionRecordEntity.setCreateTime(c.getTime());
            ibmsDevCollectionRecordEntity.setCollectionDate(c.getTime());
            ibmsDevCollectionRecordDao.save(ibmsDevCollectionRecordEntity);
        }
    }

    @Test
    public void addWaterTemperature() {
        Calendar c = Calendar.getInstance();
        for (int i = 1; i <= c.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            for (int j = 0; j <= c.getActualMaximum(Calendar.HOUR_OF_DAY); j++) {
                IBMSDevCollectionRecordEntity ibmsDevCollectionRecordEntity = new IBMSDevCollectionRecordEntity();
                //ibmsDevCollectionRecordEntity.setAttrId(Attribute.SUPPLY_WATER_TEMPERATURE.getAttrId());
                ibmsDevCollectionRecordEntity.setAttrId(Attribute.BACK_WATER_TEMPERATURE.getAttrId());
                ibmsDevCollectionRecordEntity.setSubsystemId(1);
                ibmsDevCollectionRecordEntity.setDeviceId(1);
                ibmsDevCollectionRecordEntity.setValue("60");
                c.set(Calendar.DAY_OF_MONTH, i);//设置为1号,当前日期既为本月第一天
                c.set(Calendar.HOUR_OF_DAY, j);
//                String first = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
//                System.out.println(first);
                ibmsDevCollectionRecordEntity.setCreateTime(c.getTime());
                ibmsDevCollectionRecordEntity.setCollectionDate(c.getTime());
                ibmsDevCollectionRecordDao.save(ibmsDevCollectionRecordEntity);
            }
        }
    }

}
