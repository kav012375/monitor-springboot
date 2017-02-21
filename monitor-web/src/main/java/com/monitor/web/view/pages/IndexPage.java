package com.monitor.web.view.pages;

import com.monitor.service.interfaces.request.log.InterfaceRequestLogService;
import com.monitor.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
@RestController
@RequestMapping("/")
public class IndexPage {
    @Autowired
    TaskService taskService;
    @Autowired
    InterfaceRequestLogService interfaceRequestLogService;

    @RequestMapping(value = "/")
    @ResponseBody
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        Long Times = interfaceRequestLogService.getRequestTimesByCurrentDay();
        Long runningTasks = taskService.countRunningTasksAmount();
        Long sumRunTimes = taskService.countRunTimesRemainder();
        mv.addObject("reqTimes",Times);
        mv.addObject("runningTasks",runningTasks);
        mv.addObject("sumRunTimes",sumRunTimes);
        mv.setViewName("index");
        return mv;
    }

}
