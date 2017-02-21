package com.monitor.service.user.impl;

import com.monitor.service.user.UserCheckService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public class UserCheckImpl implements UserCheckService {
    private static String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    private static Pattern SPECIAL_CHARACTORS_PATTERN = Pattern.compile(regEx);
    private static Pattern CHINESE_CHARACTORS_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");
    /**
     * 检查输入的字符串中是否存在特殊字符
     * @param input 输入字符串
     * @return 如果存在则返回true
     */
    @Override
    public boolean IsCheckSpecialCharactors(String input) {

//        Pattern p = Pattern.compile(regEx);
        Matcher m = SPECIAL_CHARACTORS_PATTERN.matcher(input);
        return (m.find());
    }
    /**
     * 检查输入的字符串中是否包含中文
     * @param input
     * @return 存在则返回true
     */
    @Override
    public boolean IsCheckChineseCharactors(String input) {
//        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = CHINESE_CHARACTORS_PATTERN.matcher(input);
        return m.find();
    }
    /**
     * 删除输入字符串中所有的特殊字符
     * @param input
     * @return 返回删除后的字符串
     */
    @Override
    public String deleteAllSpecialCharactors(String input) {
        input = SPECIAL_CHARACTORS_PATTERN.matcher(input).replaceAll("");
        return input;
    }
}
