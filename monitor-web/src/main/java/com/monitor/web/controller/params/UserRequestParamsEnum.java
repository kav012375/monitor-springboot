package com.monitor.web.controller.params;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public enum UserRequestParamsEnum {
    /**
     * 用户账户参数名
     */
    PARAM_USER_ACCT("USER_ACCT"),
    /**
     * 用户密码参数名
     */
    PARAM_USER_PWD("USER_PWD");

    UserRequestParamsEnum(String param){
        this.param_string = param;
    }


    private String param_string;

    public String getParam_string() {
        return param_string;
    }

    public void setParam_string(String param_string) {
        this.param_string = param_string;
    }
}
