package com.monitor.dal.interfaceRequestLog.dao;

import com.monitor.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.monitor.dal.interfaceRequestLog.entity.InterfaceRequestLogDO;

/**
 * Created by zeusw on 2017/1/11.
 */
public interface InterfaceRequestLogDAO {
    int insertInterfaceRequestLog(InterfaceRequestLogDO interfaceRequestLogDO);
    Long countRequestTimesByDay(InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO);
    int checkDuplicateIp(InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO);
}
