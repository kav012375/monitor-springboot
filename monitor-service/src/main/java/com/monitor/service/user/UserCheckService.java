package com.monitor.service.user;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public interface UserCheckService {
    /* 检查输入字符串中是否包含特殊字符
     * 版本:v1.0
     * 作者:冯国阳
     * 日期:2016-06-17
     * 存在特殊字符返回True,不存在返回false
     * */
    boolean IsCheckSpecialCharactors(String input);
    /* 检查输入字符串中是否包含中文
     * 版本:v1.0
     * 作者:冯国阳
     * 日期:2016-06-17
     * 存在特殊字符返回True,不存在返回false
     * */
    boolean IsCheckChineseCharactors(String input);
    /* 移除字符串中所有的特殊字符
     * 版本:v1.0
     * 作者:冯国阳
     * 日期:2016-06-20
     * 返回移除后的字符串
     * */
    String deleteAllSpecialCharactors(String input);
}
