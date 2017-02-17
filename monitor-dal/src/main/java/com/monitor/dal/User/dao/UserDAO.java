package com.monitor.dal.User.dao;

import com.monitor.dal.User.entity.UserDO;


/**
 * Created by FengG on 16/6/16.
 */
public interface UserDAO {
    UserDO findAll();
    int count();
    void InsertNewUser(UserDO userDO);
    UserDO FindUserInfoByUserAcct(String uacct);
}
