package com.monitor.service.task.impl;

import com.monitor.dal.task.dao.TaskDAO;
import com.monitor.dal.task.entity.TaskDO;
import com.monitor.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public class TaskImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;

    @Override
    public Long countRunningTasksAmount() {
        return taskDAO.countRuningTaskAmount();
    }

    @Override
    public Long countRunTimesRemainder() {
        return taskDAO.getAllSumRunTimes();
    }

    @Override
    public List<TaskDO> getAllTasks() {
        return taskDAO.findAllTasks();
    }
}
