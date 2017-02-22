package com.monitor.service.task;

import com.monitor.dal.task.entity.TaskDO;
import com.monitor.service.task.dto.TaskDTO;

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

    /**
     * 新增任务接口
     * @param taskDTO
     * @return
     */
    String addNewTask(TaskDTO taskDTO);

    /**
     * 获取指定任务的位置和动作
     * @param taskId
     * @return
     */
    String getTaskActionAndPosition(Long taskId);

    /**
     * 删除任务
     * @param taskId
     * @return
     */
    String deleteTask(Long taskId);

    /**
     * 修改任务
     * @param taskDTO
     * @return
     */
    String modifyTask(TaskDTO taskDTO);

    /**
     * 获得任务
     * @param request
     * @param ipAddress
     * @return
     */
    String getTask(String request,String ipAddress);
}
