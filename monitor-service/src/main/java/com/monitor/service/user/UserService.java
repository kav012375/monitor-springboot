package com.monitor.service.user;

import com.monitor.dal.User.entity.UserDO;

import javax.servlet.http.HttpSession;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public interface UserService {
    /**
     * 用户登录服务
     * @param userDO
     * @return
     */
    String userLogin(UserDO userDO);

    /**
     * 用户注册服务
     * @param userDO
     * @return
     */
    String userRegister(UserDO userDO);

    /**
     * @Date 2016/11/17 10:31
     * @Author guoyang.fgy
     * 查询用户是否登陆
     * @param httpSession 当前的请求session
     * @return 已经登陆返回true
     */
    boolean checkUserLoginStatus(HttpSession httpSession);
}
