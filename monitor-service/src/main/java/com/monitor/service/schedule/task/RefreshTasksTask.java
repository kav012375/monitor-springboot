package com.monitor.service.schedule.task;

import com.monitor.dal.scScheduleLog.dao.ScScheduleLogDAO;
import com.monitor.dal.scScheduleLog.entity.ScScheduleLogDO;
import com.monitor.dal.task.constants.StatusEnum;
import com.monitor.dal.task.dao.TaskDAO;
import com.monitor.dal.task.entity.TaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/23.
 *
 * @author Zeus Feng
 * @date 2017/02/23
 */
@Component
public class RefreshTasksTask {
    @Autowired
    TaskDAO taskDAO;
    @Autowired
    ScScheduleLogDAO scScheduleLogDAO;

    public RefreshTasksTask() {
        System.out.println("重置任务的定时任务已经被初始化");
    }

    @Scheduled(cron = "0 30 0 * * ?")
    public void refreshDailyLoopTask(){
        try {
            final Long startTime = System.currentTimeMillis();
            /**
             * 获取所有的已经运行完的，并且循环类型为日循环的任务
             */
            List<TaskDO> taskDOs = new ArrayList<TaskDO>();
            TaskDO queryTaskDO = new TaskDO();
            queryTaskDO.setLoopType(1);
//            queryTaskDO.setStatus(StatusEnum.RunFinish.getCode());
//            taskDOs = taskDAO.findTaskByLoopTypeAndStatus(queryTaskDO);
            taskDOs = taskDAO.findTaskByLoopType(queryTaskDO);
            if(taskDOs.size()<=0){
                System.out.println("No tasks need to be refreshed");
                return;
            }
            for (TaskDO t : taskDOs){
                System.out.println("开始更新数据,taskid = "+t.getId());
                t.setStatus(StatusEnum.NotRun.getCode());
                t.setRunTimes(t.getLoopRunTimes());
                int updateNum = taskDAO.updateTaskById(t);
                if (updateNum <=0){
                    System.err.println("更新task数据表失败，失败的taskid = "+t.getId());
                }
                System.out.println("更新任务id为task"+t.getId()+"的任务成功");
            }
            final Long endTime = System.currentTimeMillis();
            /**
             * 启动写日志任务
             */
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String costTime = String.valueOf(endTime-startTime);
                    ScScheduleLogDO scScheduleLogDO = new ScScheduleLogDO();
                    scScheduleLogDO.setScheduleTime(new Timestamp(System.currentTimeMillis()));
                    scScheduleLogDO.setTaskName("com.wulin.biz.timedTask.refreshDailyLoopTask");
                    scScheduleLogDO.setCostTime(costTime);
                    scScheduleLogDAO.insertScScheduleTaskLog(scScheduleLogDO);
                }
            }).start();
        }catch (Exception e){
            System.err.println("定时任务：refreshDailyLoopTask 执行失败，失败原因："+e.getMessage());
        }
    }
}
