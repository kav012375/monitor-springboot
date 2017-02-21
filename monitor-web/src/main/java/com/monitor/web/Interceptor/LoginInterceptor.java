package com.monitor.web.Interceptor;

import com.monitor.service.user.UserService;
import com.monitor.service.user.impl.UserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //定义不需要进行拦截的URL
        String[] nonFilterUri = new String[]{
                "login", "/images","/sbAdmin","/user","/css","/getCode.do","/register.do","/task/gettask","/file","/error"
        };
        String preferUrl = request.getRequestURI();
        for(String url:nonFilterUri){
            if(preferUrl.contains(url)){
                return super.preHandle(request, response, handler);
            }
        }
        //检测是否已经登录
       HttpSession httpSession = request.getSession();
        if (httpSession == null){
            response.sendRedirect("/user/login");
        }else{
            //不能使用注入，实际证明拦截器中无法使用注入
            UserService userService = new UserImpl();
            boolean chcekResult = userService.checkUserLoginStatus(httpSession);
            if(!chcekResult){
                response.sendRedirect("/user/login");
            }else{
                return super.preHandle(request, response, handler);
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

        super.postHandle(request, response, handler, modelAndView);
    }

    @Bean
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }

}
