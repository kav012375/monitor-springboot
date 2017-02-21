package com.monitor.security.encryption;


import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
@Service
public interface EncryptionService {
    /* 对字符串进行加密,加密方式为MD5?
     * 版本:v1.0? * 作者:冯国阳?
     * 日期:2016-06-20? *
     * 存在特殊字符返回True,不存在返回false? *
     * */
    String Md5Creator(String input);

    /**
     * 获取用户请求的真实Ip
     * @param httpServletRequest 用户请求体
     * @return
     */
    String GetRealIpAddr(HttpServletRequest httpServletRequest);
}
