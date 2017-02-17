package com.monitor.dal.task.dao;

import com.monitor.dal.task.entity.TaskDO;

import java.util.List;

/**
 * Created by zeusw on 2016/12/20.
 */
public interface TaskDAO {
    /**
     * 通过任务执行状态，分组，项目名称查找任务
     * @param taskDO
     * @return
     */
    List<TaskDO> findTaskByStatusAndGroupAndProjectName(TaskDO taskDO);

    /**
     * 通过id查找任务
     * @param id
     * @return
     */
    TaskDO findTaskById(Long id);

    /**
     * 插入任务
     * @param taskDO
     * @return
     */
    Long insertTask(TaskDO taskDO);

    /**
     * 通过任务id更新任务
     * @param taskDO
     * @return
     */
    int updateTaskById(TaskDO taskDO);

    /**
     * 通过循环类型更新任务
     * @param taskDO
     * @return
     */
    int updateTaskByLoopType(TaskDO taskDO);

    /**
     * 通过任务状态，分组，项目名以及任务执行的剩余次数查找任务
     * @param taskDO
     * @return
     */
    List<TaskDO> findVailedTaskByStatusAndGroupAndProjectAndRuntimes(TaskDO taskDO);

    /**
     * 查询所有的任务
     * @return
     */
    List<TaskDO> findAllTasks();

    /**
     * 通过循环类型与状态查找任务
     * @param taskDO
     * @return
     */
    List<TaskDO> findTaskByLoopTypeAndStatus(TaskDO taskDO);

    /**
     * 通过循环类型查找任务
     * @param taskDO
     * @return
     */
    List<TaskDO> findTaskByLoopType(TaskDO taskDO);

    /**
     * 通过id删除任务
     * @param taskDO
     * @return
     */
    int deleteTaskByTaskId(TaskDO taskDO);

    /**
     * 统计所有运行中的任务
     * @return
     */
    Long countRuningTaskAmount();

    /**
     * 统计所有待运行的任务次数
     * @return
     */
    Long getAllSumRunTimes();
}
