package com.monitor.web.controller;

import com.monitor.dal.User.entity.UserDO;
import com.monitor.security.encryption.EncryptionService;
import com.monitor.service.constants.ErrorCodeEnum;
import com.monitor.service.constants.SessionEnum;
import com.monitor.service.constants.UserReturnCodeEnum;
import com.monitor.service.user.UserService;
import com.monitor.web.controller.params.UserRequestParamsEnum;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
@RestController
@RequestMapping(value = "/control")
public class User {

    @Autowired
    UserService userService;
    @Autowired
    EncryptionService encryptionService;

    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "用户登录",notes = "用户登录服务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "USER_ACCT",value = "用户账户",required = true,dataType = "字符串"),
            @ApiImplicitParam(name = "USER_PWD",value = "用户密码",required = true,dataType = "字符串")})
    public String controlUserLogin(
            @Param("USER_ACCT") String USER_ACCT,
            @Param("USER_PWD") String USER_PWD,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            @ApiIgnore HttpSession httpSession
    ){
        UserDO userDO = new UserDO();
        userDO.setUSER_ACCT(USER_ACCT);
        userDO.setUSER_PWD(USER_PWD);
        try {
            String result = userService.userLogin(userDO);
            if(result.equals(UserReturnCodeEnum.USER_LOGIN_SUCCESS.getIndex().toString())){
                //用户登录成功
                String userLastLoginTime = (new Date()).toString();
                String accessToken = encryptionService.Md5Creator(userDO.getUSER_ACCT()+userLastLoginTime);
                httpSession.setAttribute(SessionEnum.CurrentLoginUserAcct.getEnumKey(),userDO.getUSER_ACCT());
                httpSession.setAttribute(SessionEnum.CurrentLoginTime.getEnumKey(),userLastLoginTime);
                httpSession.setAttribute(SessionEnum.CurrentAccessToken.getEnumKey(), accessToken);
                return result;
            }else
            {
                //用户登录失败
                return result;
            }
        }catch (Exception e){
            e.printStackTrace();
            return ErrorCodeEnum.SYSTEM_ERROR.getDescription();
        }
    }

    @RequestMapping(value = "/register.do")
    @ResponseBody
    @ApiOperation(value = "用户注册",notes = "用户注册服务")
    public String controlUserRegister(
            HttpServletRequest request,
            HttpServletResponse response,
            @Param("USER_ACCT") String USER_ACCT,
            @Param("USER_PWD") String USER_PWD,
            @Param("USER_CCODE") String USER_CCODE

    ){
        UserDO userDO = new UserDO();
        HttpSession httpSession = request.getSession();
        userDO.setUSER_ACCT(USER_ACCT);
        userDO.setUSER_PWD(USER_PWD);
        userDO.setREG_IP(encryptionService.GetRealIpAddr(request));
        String validata_code = USER_CCODE.toLowerCase();
        try {
            //判断验证码是否正确
            if((validata_code.compareTo(httpSession.getAttribute("validateCode").toString().toLowerCase())!=0)){
                return UserReturnCodeEnum.USER_CCODE_IS_NULL_OR_INCORRECT.getIndex().toString();
            }
            //TODO:更换成用户注册的方法
            return userService.userRegister(userDO);
        }catch (Exception e){
            e.printStackTrace();
            return ErrorCodeEnum.SYSTEM_ERROR.getIndex().toString();
        }
    }

}
