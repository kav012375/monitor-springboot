package com.monitor.web.view.pages;

import com.monitor.service.article.ArticleService;
import com.monitor.service.task.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
@RestController
@RequestMapping(value = "/task")
public class TaskPage {
    @Autowired
    TaskService taskService;
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/manager")
    @ResponseBody
    public ModelAndView taskPageTaskManager(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/monitor/taskquery");
        modelAndView.addObject("datasource", taskService.getAllTasks());
        modelAndView.addObject("artList",articleService.getAllArticleTypes());
        return modelAndView;
    }
    @RequestMapping(value = "/add")
    @ResponseBody
    public ModelAndView taskPageTaskAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("artList",articleService.getAllArticleTypes());
        modelAndView.setViewName("/taskconfig/normalTaskConfig");
        return modelAndView;
    }
}
