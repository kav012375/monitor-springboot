package com.monitor.service.task;

import com.monitor.dal.task.entity.TaskDO;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public interface TaskService {
    /**
     * 计算正在执行中的日任务数
     * @return 任务数
     */
    Long countRunningTasksAmount();

    /**
     * 计算剩余待任务量
     * @return
     */
    Long countRunTimesRemainder();

    /**
     * 获取所有的任务配置
     * @return
     */
    List<TaskDO> getAllTasks();



}
