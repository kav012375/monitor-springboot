package com.monitor.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.monitor.dal.infArticles.dao.InfArticlesDAO;
import com.monitor.dal.infArticles.entity.InfArticlesDO;
import com.monitor.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.monitor.dal.interfaceRequestLog.entity.InterfaceRequestLogDO;
import com.monitor.dal.task.constants.StatusEnum;
import com.monitor.dal.task.dao.TaskDAO;
import com.monitor.dal.task.entity.TaskDO;
import com.monitor.dal.taskInstance.dao.TaskInstanceDAO;
import com.monitor.dal.taskInstance.entity.TaskInstanceDO;
import com.monitor.dal.userPwdConfig.dao.UserPwdConfigDAO;
import com.monitor.dal.userPwdConfig.entity.UserPwdConfigDO;
import com.monitor.service.interfaces.request.log.InterfaceRequestLogService;
import com.monitor.service.task.TaskService;
import com.monitor.service.task.dto.ActionDTO;
import com.monitor.service.task.dto.PositionDTO;
import com.monitor.service.task.dto.TaskDTO;
import com.monitor.service.task.dto.TaskDistributeDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public class TaskImpl implements TaskService {

    @Autowired
    TaskDAO taskDAO;
    @Autowired
    InterfaceRequestLogService interfaceRequestLogService;
    @Autowired
    TaskInstanceDAO taskInstanceDAO;
    @Autowired
    UserPwdConfigDAO userPwdConfigDAO;
    @Autowired
    InfArticlesDAO infArticlesDAO;

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
            if (id <= 0L) {
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
            for (String eve : positionsValueList) {
                PositionDTO pDO = new PositionDTO();
                pDO.setPosition(eve);
                positionDTOs.add(pDO);
            }
            for (String eve : actionsValueList) {
                ActionDTO aDO = new ActionDTO();
                aDO.setAction(eve);
                actionDTOs.add(aDO);
            }
            taskDistributeDTO.setPositionDTOs(positionDTOs);
            taskDistributeDTO.setActionDTOs(actionDTOs);
            taskDistributeDTO.setTaskUid(String.valueOf(id));
            String json = JSON.toJSONString(taskDistributeDTO);
            //将JSON中的DTOS替换成DOS
            json = json.replace("actionDTOs", "actionDOs");
            json = json.replace("positionDTOs", "positionDOs");
            taskDO.setId(id);
            taskDO.setTaskContent(json);
            taskDO.setStatus(StatusEnum.NotRun.getCode());
            taskDO.setRunTimes(Integer.parseInt(taskDTO.getRuntimes()));
            taskDO.setMgroup(taskDTO.getMgroup());
            taskDO.setProjectName(taskDTO.getProjectName());
            taskDO.setArticleType(taskDTO.getArtType());
            if (taskDTO.getIpFilter().equals("false")) {
                taskDO.setIpFilter(false);
            } else {
                taskDO.setIpFilter(true);
            }
            if (taskDTO.getLooptype().equals("2")) {
                taskDO.setLoopType(2);
            } else {
                taskDO.setLoopType(1);
                taskDO.setLoopRunTimes(Integer.parseInt(taskDTO.getRuntimes()));
            }
            taskDAO.updateTaskById(taskDO);
            return "添加成功！";
        } catch (Exception e) {
            e.printStackTrace();
            return "系统异常！";
        }
    }

    @Override
    public String getTaskActionAndPosition(Long taskId) {
        try {
            TaskDO taskDO = taskDAO.findTaskById(taskId);
            if (taskDO == null){
                return "任务不存在";
            }
            JSONObject object = JSON.parseObject(taskDO.getTaskContent());
            String result = "";
            JSONArray jsonArray = object.getJSONArray("actionDOs");
            for (Object s : jsonArray){
                JSONObject tmpObj = JSON.parseObject(s.toString());
                result = result+tmpObj.getString("action")+";";
            }
            jsonArray = object.getJSONArray("positionDOs");
            result = result + "&&&&";
            for (Object s : jsonArray){
                JSONObject tmpObj = JSON.parseObject(s.toString());
                result = result+tmpObj.getString("position")+";";
            }
            return result;

        }catch (Exception e){
            e.printStackTrace();
            return "获取异常";
        }
    }

    @Override
    public String deleteTask(Long taskId) {
        try {
            if (taskId <= 0L){
                return "传入的taskid为空，无法执行删除操作！";
            }
            TaskDO taskDO = new TaskDO();
            taskDO.setId(taskId);
            int deleteNum = taskDAO.deleteTaskByTaskId(taskDO);
            if (deleteNum<=0){
                return "删除失败，请稍候再试";
            }
            return "删除成功";
        }catch (Exception e){
            e.printStackTrace();
            return "删除失败，错误原因:系统异常！";
        }
    }

    @Override
    public String modifyTask(TaskDTO taskDTO) {
        //TODO：检查孔字符
        if(taskDTO.getActions().equals("default")||taskDTO.getPositions().equals("default")
                || taskDTO.getActions().equals("")||taskDTO.getPositions().equals("")){
            return "动作或位置为空！";
        }
        TaskDO taskDO = new TaskDO();
        taskDO.setId(Long.parseLong(taskDTO.getId()));
        taskDO.setLoopRunTimes(Integer.parseInt(taskDTO.getLoopruntimes()));
        taskDO.setMgroup(taskDTO.getMgroup());
        taskDO.setProjectName(taskDTO.getProjectName());
        taskDO.setArticleType(taskDTO.getArtType());
        if(taskDTO.getIpFilter().equals("false")){
            taskDO.setIpFilter(false);
        }else{
            taskDO.setIpFilter(true);
        }
        //获取当前任务的content
        TaskDO oldTaskDO = taskDAO.findTaskById(Long.parseLong(taskDTO.getId()));

        TaskDistributeDTO taskDistributeDTO = new TaskDistributeDTO();
        String content = oldTaskDO.getTaskContent();
        JSONObject object = JSON.parseObject(content);

        taskDistributeDTO.setTaskUid(object.getString("taskUid"));
        taskDistributeDTO.setMediaName(object.getString("mediaName"));
        taskDistributeDTO.setStatus(object.getString("status"));
        taskDistributeDTO.setUsername(object.getString("username"));
        taskDistributeDTO.setPassword(object.getString("password"));

        //组装新的task_content
        List<PositionDTO> positionDTOs = new ArrayList<PositionDTO>();
        List<ActionDTO> actionDTOs = new ArrayList<ActionDTO>();
        String[] positionsValueList = taskDTO.getPositions().split(";");
        String[] actionsValueList = taskDTO.getActions().split(";");
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

        taskDistributeDTO.setActionDTOs(actionDTOs);
        taskDistributeDTO.setPositionDTOs(positionDTOs);
        String json = JSON.toJSONString(taskDistributeDTO);
        //将JSON中的DTOS替换成DOS
        json = json.replace("actionDTOs","actionDOs");
        json = json.replace("positionDTOs","positionDOs");
        taskDO.setTaskContent(json);

        int updateNum = taskDAO.updateTaskById(taskDO);
        if(updateNum < 1){
            return "更新失败，请联系管理员！";
        }else{
            return "更新成功！";
        }
    }

    @Override
    public String getTask(String request, String ipAddress) {
        TaskDO taskDO = new TaskDO();
        TaskInstanceDO taskInstanceDO = new TaskInstanceDO();
        Random random = new Random();
        List<TaskDO> taskDOs = new ArrayList<TaskDO>();
        try {
            //获取请求
            JSONObject jsonObject = JSON.parseObject(request);
            String projectName = jsonObject.getString("projectName");
            String mgroup = jsonObject.getString("group");
            String ip = jsonObject.getString("ip");
            String accessToken = jsonObject.getString("password");
            String accountType = jsonObject.getString("type");
            if (projectName == null || mgroup == null || ip == null || accessToken == null) {
                return "参数不完整";
            }
            taskDO.setProjectName(projectName);
            taskDO.setMgroup(mgroup);
            taskDO.setStatus(0);

            //获取未完成的任务
            taskDOs = taskDAO.findVailedTaskByStatusAndGroupAndProjectAndRuntimes(taskDO);
            if (taskDOs.size() < 1) {
                return "无任务可以领用";
            }
            /**
             * 随机下发逻辑
             */
            int iszie = taskDOs.size();
            int randomIndex = random.nextInt(iszie);
            taskDO = taskDOs.get(randomIndex);
            //判断该任务是否需要过滤ip
            if(taskDO.isIpFilter()){
                //检查请求的ip是否重复
                InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO =
                        interfaceRequestLogService.assembleInterfaceRequestLogQyueryUtil(taskDO,ipAddress);
                boolean checResult = interfaceRequestLogService.checkDuplicateIp(interfaceRequestLogQueryDTO);
                if (!checResult) {
                    //发现重复IP过滤掉
                    return "重复的请求IP，被过滤";
                }
            }
            //获取到任务并且ip检查通过之后，将任务次数减1
            taskDO.setRunTimes(taskDO.getRunTimes() - 1);
            if (taskDO.getRunTimes() == 0) {
                taskDO.setStatus(StatusEnum.RunFinish.getCode());
            }
            /**
             * 逻辑结束
             */
            //更新任务次数和状态
            int taskUpdataNum = taskDAO.updateTaskById(taskDO);
            if (taskUpdataNum < 1) {
                System.out.println("更新task表失败");
                return "更新task表失败";
            }
            taskInstanceDO.setTaskId(taskDO.getId());
            taskInstanceDO.setContent(taskDO.getTaskContent());
            taskInstanceDO.setGmtCreate(new java.sql.Timestamp(System.currentTimeMillis()));
            taskInstanceDO.setIp(ip);
            taskInstanceDAO.insertTaskInstance(taskInstanceDO);
            Long uid = taskInstanceDO.getUid();
            if (uid <= 0) {
                System.out.println("更新task_instance表失败");
//                return;
            }
            JSONObject object = JSON.parseObject(taskDO.getTaskContent());
            object.remove("taskUid");
            object.put("taskUid", uid.toString());
            taskInstanceDO.setContent(JSONObject.toJSONString(object));
            int taskInstanceUpdateNum = taskInstanceDAO.updateTaskInstanceByUid(taskInstanceDO);
            if (taskInstanceUpdateNum <= 0) {
                System.out.println("第二次更新task_instance表失败");
//                return;
            }
            String content = taskInstanceDO.getContent();
            //账号类型不为空，则获取账号信息
            if (accountType != null) {
                //根据账户类型从账号库中随机选择一个账号密码
                UserPwdConfigDO userPwdConfigDO = userPwdConfigDAO.findRandomUserPwdByProject(accountType);
                if (userPwdConfigDO != null) {
                    JSONObject tmpObj = JSON.parseObject(content);
                    if (userPwdConfigDO.getAccount() != null && userPwdConfigDO.getPwd() != null) {
                        tmpObj.remove("username");
                        tmpObj.remove("password");
                        tmpObj.put("username", userPwdConfigDO.getAccount());
                        tmpObj.put("password", userPwdConfigDO.getPwd());
                        content = JSONObject.toJSONString(tmpObj);
                    }
                }
            }
            //评论类型不为空，则下发评论
            if((!taskDO.getArticleType().equals(""))&&(!taskDO.getArticleType().equals("null"))&&(taskDO.getArticleType()!=null)){
                //获取相关评论
                List<InfArticlesDO> infArticlesDOs = infArticlesDAO.findArticlesContentByArticleTypeId(
                        Integer.parseInt(taskDO.getArticleType()));
                int infSize = infArticlesDOs.size();
                if (infSize>=1){
                    int randomInf = random.nextInt(infSize);
                    String articlesContent = infArticlesDOs.get(randomInf).getArticleContent();
                    JSONObject infObj = JSON.parseObject(content);
                    infObj.put("articles",articlesContent);
                    content = JSONObject.toJSONString(infObj);
                }
            }
            //记录请求日志
            InterfaceRequestLogDO interfaceRequestLogDO = new InterfaceRequestLogDO();
            interfaceRequestLogDO.setInterfaceName("getTask");
            interfaceRequestLogDO.setRequestTime(new Timestamp(System.currentTimeMillis()));
            interfaceRequestLogDO.setIpAddress(ipAddress);
            interfaceRequestLogDO.setProjectName(taskDO.getProjectName());
            interfaceRequestLogDO.setMgroup(taskDO.getMgroup());
            interfaceRequestLogService.saveRequestRecord(interfaceRequestLogDO);

            return content;
        } catch (Exception e) {
            e.printStackTrace();
            return "系统异常，异常原因："+e.getMessage();
        }finally {
            //销毁大数据
            taskDOs.clear();
            taskDOs = null;
        }
    }
}