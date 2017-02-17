package com.monitor.dal.userPwdConfig;

import com.monitor.dal.userPwdConfig.entity.UserPwdConfigDO;

/**
 * Created by zeusw on 2016/12/28.
 * @author zeusw
 */
public interface UserPwdConfigService {
    /**
     * 插入一条数据到用户名密码配置表
     * @param userPwdConfigDO
     * @return
     */
    boolean normalInsertConfigInfoInToUserPwdConfigTable(UserPwdConfigDO userPwdConfigDO);
}
