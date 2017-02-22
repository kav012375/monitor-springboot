package com.monitor.web.controller;

import com.monitor.security.encryption.EncryptionService;
import com.monitor.service.task.TaskService;
import com.monitor.service.task.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zeus Feng on 2017/2/22.
 *
 * @author Zeus Feng
 * @date 2017/02/22
 */
@RestController
@RequestMapping(value = "/task",method = RequestMethod.POST)
public class Task {
    @Autowired
    TaskService taskService;
    @Autowired
    EncryptionService encryptionService;

    @ResponseBody
    @RequestMapping(value = "/addtask")
    public String taskActionAddNewTask(
            @RequestParam("mediaName") String mediaName,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("positions") String positions,
            @RequestParam("actions") String actions,
            @RequestParam("runtimes") String runtimes,
            @RequestParam("mgroup") String mgroup,
            @RequestParam("projectName") String projectName,
            @RequestParam("looptype") String looptype,
            @RequestParam("artType") String artType,
            @RequestParam("ipFilter") String ipFilter){

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setMediaName(mediaName);
        taskDTO.setUsername(username);
        taskDTO.setPassword(password);
        taskDTO.setPositions(positions);
        taskDTO.setActions(actions);
        taskDTO.setRuntimes(runtimes);
        taskDTO.setMgroup(mgroup);
        taskDTO.setProjectName(projectName);
        taskDTO.setLooptype(looptype);
        taskDTO.setLoopruntimes("");
        taskDTO.setArtType(artType);
        taskDTO.setIpFilter(ipFilter);
        return taskService.addNewTask(taskDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/getTaskActionAndPosition",method = RequestMethod.POST)
    public String taskActionGetTaskActionAndPosition(@RequestParam("id") String taskId){
        return taskService.getTaskActionAndPosition(Long.parseLong(taskId));
    }

    @RequestMapping(value = "/deleteTask")
    @ResponseBody
    public String taskActionDeleteTask(@RequestParam("taskid") String taskId){
       return taskService.deleteTask(Long.parseLong(taskId));
    }

    @ResponseBody
    @RequestMapping(value = "/modifytask")
    public String taskActionModifyTask(
            @RequestParam("taskid") String taskId,
            @RequestParam("mgroup") String mgroup,
            @RequestParam("projectname") String projectname,
            @RequestParam("looptimes") String looptimes,
            @RequestParam("actions") String actions,
            @RequestParam("positions") String positions,
            @RequestParam("artType") String artType,
            @RequestParam("ipFilter") String ipFilter
    ){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskId);
        taskDTO.setMgroup(mgroup);
        taskDTO.setProjectName(projectname);
        taskDTO.setLoopruntimes(looptimes);
        taskDTO.setActions(actions);
        taskDTO.setPositions(positions);
        taskDTO.setArtType(artType);
        taskDTO.setIpFilter(ipFilter);
        return taskService.modifyTask(taskDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/gettask")
    public String taskActionGetTask(
            HttpServletRequest httpServletRequest,
            @RequestParam("request") String request
    ){
        return taskService.getTask(request,encryptionService.GetRealIpAddr(httpServletRequest));
    }
}
