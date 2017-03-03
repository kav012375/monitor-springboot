package com.monitor.service;

import com.monitor.service.article.ArticleService;
import com.monitor.service.article.ArticlesInformationService;
import com.monitor.service.article.impl.ArticleImpl;
import com.monitor.service.article.impl.ArticlesInformationImpl;
import com.monitor.service.interfaces.request.log.InterfaceRequestLogService;
import com.monitor.service.interfaces.request.log.impl.InterfaceRequestLogImpl;
import com.monitor.service.report.DailyReportService;
import com.monitor.service.report.impl.DailyReportImpl;
import com.monitor.service.task.TaskService;
import com.monitor.service.task.impl.TaskImpl;
import com.monitor.service.user.UserCheckService;
import com.monitor.service.user.UserService;
import com.monitor.service.user.impl.UserCheckImpl;
import com.monitor.service.user.impl.UserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
@Service
public class ServiceConfig {

    @Bean
    @Scope(value = "prototype")
    public TaskService taskService(){
        TaskService taskService
                = new TaskImpl();
        return taskService;
    }

    @Bean
    public InterfaceRequestLogService interfaceRequestLogService(){
        InterfaceRequestLogService interfaceRequestLogService
                = new InterfaceRequestLogImpl();
        return interfaceRequestLogService;
    }

    @Bean
    public UserCheckService userCheckService(){
        UserCheckService userCheckService
                = new UserCheckImpl();
        return userCheckService;
    }

    @Bean
    public UserService userService(){
        UserService userService
                = new UserImpl();
        return userService;
    }

    @Bean
    public ArticleService articleService(){
        ArticleService articleService
                = new ArticleImpl();
        return articleService;
    }

    @Bean
    public DailyReportService dailyReportService(){
        DailyReportService dailyReportService
                = new DailyReportImpl();
        return dailyReportService;
    }

    @Bean
    public ArticlesInformationService articlesInformationService(){
        ArticlesInformationService articlesInformationService
                = new ArticlesInformationImpl();
        return articlesInformationService;
    }
}
