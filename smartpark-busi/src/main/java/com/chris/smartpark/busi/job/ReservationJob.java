package com.chris.smartpark.busi.job;

/**
 * Created by lisen on 2018/12/7.
 */

import com.chris.base.modules.sys.service.SysConfigService;
import com.chris.smartpark.busi.service.VisitorReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 变更预约单状态
 *
 * @author lisen
 * @date 2018/12/7
 */
@Slf4j
@Component
public class ReservationJob {
    @Autowired
    private VisitorReservationService visitorReservationService;
    @Autowired
    private SysConfigService sysConfigService;
    /**
     * 每一小时处理一次预约时间已结束却没到的预约单为已过期
     *
     */
    @Scheduled(cron = "0 0 0/1 * * ?")//一小时一次
 /*   @Scheduled(cron = "0 0/1 * * * ?")//一分钟一次*/
    public void change2Overdue(){
        log.info("每一小时处理一次预约时间已结束却没到的预约单为已过期定时任务开始");
        visitorReservationService.change2Overdue();
        log.info("每一小时处理一次预约时间已结束却没到的预约单为已过期定时任务结束");

    }
    /**
     * 每一小时处理一次预约时间已结束却没到的预约单为已过期
     *
     */
    @Scheduled(cron = "0 0/5 * * * ?")//一小时一次
 /*   @Scheduled(cron = "0 0/5 * * * ?")//一分钟一次*/
    public void promptForReservation(){
        log.info("每五分钟处理一次预约时间已结束却没到的预约单为已过期定时任务开始");
        String beforeHours = sysConfigService.getValue("BEFOREHOURS");
        visitorReservationService.sendSMSPrompt(beforeHours);
        log.info("每五分钟处理一次预约时间已结束却没到的预约单为已过期定时任务结束");

    }
}
