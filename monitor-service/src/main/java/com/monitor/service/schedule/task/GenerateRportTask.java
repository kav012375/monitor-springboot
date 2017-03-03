package com.monitor.service.schedule.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monitor.dal.dailyReport.dao.DailyReportDAO;
import com.monitor.dal.dailyReport.dto.DailyRportQueryDTO;
import com.monitor.dal.dailyReport.entity.DailyReportDO;
import com.monitor.dal.scScheduleLog.dao.ScScheduleLogDAO;
import com.monitor.dal.scScheduleLog.entity.ScScheduleLogDO;
import com.monitor.dal.task.dao.TaskDAO;
import com.monitor.dal.task.entity.TaskDO;
import com.monitor.dal.taskInstance.dao.TaskInstanceDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/23.
 *
 * @author Zeus Feng
 * @date 2017/02/23
 */
@Component
public class GenerateRportTask {
    @Autowired
    DailyReportDAO dailyReportDAO;
    @Autowired
    TaskInstanceDAO taskInstanceDAO;
    @Autowired
    TaskDAO taskDAO;
    @Autowired
    ScScheduleLogDAO scScheduleLogDAO;

    public GenerateRportTask() {
        System.out.println("生成日报的定时任务已经被初始化");
    }

    @Scheduled(cron = "00 50 23 * * ?")
    public void generateDailyReportTask() throws Throwable{
        new Thread(new Runnable() {
            @Override
            public void run() {
                generateTaskReprot();
            }
        }).start();
    }

    private void generateTaskReprot(){
        //记录任务开始时间
        Long thisMethodStartTime = System.currentTimeMillis();

        //初始化数据爬取DTO
        DailyRportQueryDTO dailyRportQueryDTO = new DailyRportQueryDTO();
        //初始化执行日志
        ScScheduleLogDO scScheduleLogDO = new ScScheduleLogDO();

        scScheduleLogDO.setScheduleTime(new Timestamp(System.currentTimeMillis()));
        dailyRportQueryDTO.setEndTime(new Timestamp(System.currentTimeMillis()).toString());

        try {
            /**
             * 前提：如果任务执行过，则放弃执行
             */
            String startTimebeforePart = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
            dailyRportQueryDTO.setStartTime(startTimebeforePart+" 00:00:00");
            dailyRportQueryDTO.setEndTime(startTimebeforePart+" 23:59:59");
            List<DailyReportDO> dailyReportDOs = dailyReportDAO.findDailyReportContentByTime(dailyRportQueryDTO);
            if(dailyReportDOs.size()>0){
                System.out.println("已经执行过该任务，放弃执行");
                return;
            }
            dailyRportQueryDTO.setStartTime(null);
            /**
             * 第一步：获取今天所有执行过的不重复任务id
             */
            List<Long> taskids = taskInstanceDAO.findTaskIdListByTimeStamp(dailyRportQueryDTO);
            if (taskids.size()<=0){
                System.err.println("no task instance!");
                return;
            }
            /**
             * 第二步：获取每个任务的运行次数以及其他基本信息
             */
            for(Long id : taskids){
                DailyReportDO dailyReportDO = new DailyReportDO();
                dailyReportDO.setReportDate(new Timestamp(thisMethodStartTime));
                dailyReportDO.setTaskId(id);
                dailyRportQueryDTO.setTaskId(String.valueOf(id));
                //获取运行次数
                Long runTimes = taskInstanceDAO.countTaskInstanceByTaskId(dailyRportQueryDTO);
                dailyReportDO.setRunTimes(runTimes);
                //获取projectName和mediaName
                TaskDO taskDO = taskDAO.findTaskById(id);
                if (taskDO == null){
                    dailyReportDO.setProjectName("任务已经被删除");
                    dailyReportDO.setMediaName("任务已经被删除");
                }else{
                    dailyReportDO.setProjectName(taskDO.getProjectName());
                    JSONObject object = JSON.parseObject(taskDO.getTaskContent());
                    dailyReportDO.setMediaName(object.getString("mediaName"));
                }
                //插入报告
                dailyReportDAO.insertDailyReport(dailyReportDO);
            }
            //删除所有已经统计报表的instance
            taskInstanceDAO.deleteTaskInstanceByTimeStamp(dailyRportQueryDTO);
            Long thisMethodEndTime = System.currentTimeMillis();
            Long costTime = thisMethodEndTime - thisMethodStartTime;
            //记录任务执行日志
            scScheduleLogDO.setCostTime(costTime.toString());
            scScheduleLogDO.setTaskName("com.wulin.biz.timedTask.ReportTask.generateTaskReprot");
            scScheduleLogDAO.insertScScheduleTaskLog(scScheduleLogDO);
            //删除所有数据为0的记录
            dailyReportDAO.deleteZeroRunTimesReport();
        }catch (Exception e){
            System.err.println("when excute report task ,there is an exception,error is "+e.getMessage());
        }


    }
}
