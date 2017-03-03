package com.monitor.service.schedule.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Zeus Feng on 2017/2/23.
 *
 * @author Zeus Feng
 * @date 2017/02/23
 */
@Component
public class TickTask {

    @Scheduled(fixedRate = 20000)
    public void testTick(){
        //System.out.println("我是定时任务，每20秒执行一次");
    }
}
