package com.monitor.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.monitor.dal.task.constants.StatusEnum;
import com.monitor.dal.task.dao.TaskDAO;
import com.monitor.dal.task.entity.TaskDO;
import com.monitor.service.task.TaskService;
import com.monitor.service.task.dto.ActionDTO;
import com.monitor.service.task.dto.PositionDTO;
import com.monitor.service.task.dto.TaskDTO;
import com.monitor.service.task.dto.TaskDistributeDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
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

    @Override
    public String addNewTask(TaskDTO taskDTO) {
        try {
            /**
             * 创建DTO开始拼装JSON
             */
            TaskDistributeDTO taskDistributeDTO = new TaskDistributeDTO();
            TaskDO taskDO = new TaskDO();
            taskDAO.insertTask(taskDO);
            Long id = taskDO.getId();
            if(id<=0L){
                return "插入失败！";
            }
            taskDistributeDTO.setStatus(String.valueOf(StatusEnum.NotRun.getCode()));
            taskDistributeDTO.setUsername(taskDTO.getUsername());
            taskDistributeDTO.setPassword(taskDTO.getPassword());
            taskDistributeDTO.setMediaName(taskDTO.getMediaName());
            String[] positionsValueList = taskDTO.getPositions().split(";");
            String[] actionsValueList = taskDTO.getActions().split(";");
            List<PositionDTO> positionDTOs = new ArrayList<PositionDTO>();
            List<ActionDTO> actionDTOs = new ArrayList<ActionDTO>();
            for (String eve:positionsValueList) {
                PositionDTO pDO = new PositionDTO();
                pDO.setPosition(eve);
                positionDTOs.add(pDO);
            }
            for (String eve:actionsValueList) {
                ActionDTO aDO = new ActionDTO();
                aDO.setAction(eve);
                actionDTOs.add(aDO);
            }
            taskDistributeDTO.setPositionDTOs(positionDTOs);
            taskDistributeDTO.setActionDTOs(actionDTOs);
            taskDistributeDTO.setTaskUid(String.valueOf(id));
            String json = JSON.toJSONString(taskDistributeDTO);
            //将JSON中的DTOS替换成DOS
            json = json.replace("actionDTOs","actionDOs");
            json = json.replace("positionDTOs","positionDOs");
            taskDO.setId(id);
            taskDO.setTaskContent(json);
            taskDO.setStatus(StatusEnum.NotRun.getCode());
            taskDO.setRunTimes(Integer.parseInt(taskDTO.getRuntimes()));
            taskDO.setMgroup(taskDTO.getMgroup());
            taskDO.setProjectName(taskDTO.getProjectName());
            taskDO.setArticleType(taskDTO.getArtType());
            if(taskDTO.getIpFilter().equals("false")){
                taskDO.setIpFilter(false);
            }else{
                taskDO.setIpFilter(true);
            }
            if (taskDTO.getLooptype().equals("2")){
                taskDO.setLoopType(2);
            }else{
                taskDO.setLoopType(1);
                taskDO.setLoopRunTimes(Integer.parseInt(taskDTO.getRuntimes()));
            }
            taskDAO.updateTaskById(taskDO);
            return "添加成功！";
        }catch (Exception e){
            e.printStackTrace();
            return "系统异常！";
        }
    }
}
