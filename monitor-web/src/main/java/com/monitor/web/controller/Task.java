package com.monitor.web.controller;

import com.monitor.service.task.TaskService;
import com.monitor.service.task.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
