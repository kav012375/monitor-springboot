package com.monitor.dal.Role.dao;

import com.monitor.dal.Role.entity.RoleDO;

/**
 * Created by zeusw on 2016/11/11.
 */
public interface RoleDAO {
    int checkIfHaveRoleViaUserAcct(String userAcct);
    int insertRoleDataInfo(RoleDO roleDO);
}
