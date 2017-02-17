package com.monitor.service;

import com.monitor.dal.task.dao.TaskDAO;
import com.monitor.dal.task.entity.TaskDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zeusw on 2017/2/17.
 *
 * @author zeusw
 * @date 2017/02/17
 */
@Service
public class TestService {
    @Autowired
    TaskDAO taskDAO;


    public String testTasks(){
        return taskDAO.findTaskById(149L).getProjectName();
    }
}
