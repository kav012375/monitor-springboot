package com.monitor.service.interfaces.request.log.impl;

import com.monitor.dal.interfaceRequestLog.dao.InterfaceRequestLogDAO;
import com.monitor.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.monitor.dal.interfaceRequestLog.entity.InterfaceRequestLogDO;
import com.monitor.service.interfaces.request.log.InterfaceRequestLogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public class InterfaceRequestLogImpl implements InterfaceRequestLogService {

    @Autowired
    InterfaceRequestLogDAO interfaceRequestLogDAO;

    @Override
    public Long getRequestTimesByCurrentDay() {
        InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO = new InterfaceRequestLogQueryDTO();
        String timeBefore = new Timestamp(System.currentTimeMillis()).toString().split(" ")[0];
        interfaceRequestLogQueryDTO.setStartTime(timeBefore+" 00:00:00");
        interfaceRequestLogQueryDTO.setEndTime(timeBefore+" 23:59:59");
        Long times = interfaceRequestLogDAO.countRequestTimesByDay(interfaceRequestLogQueryDTO);
        return times;
    }

    @Override
    public void saveRequestRecord(final InterfaceRequestLogDO interfaceRequestLogDO) {
        new Thread(new Runnable() {
            public void run() {
                interfaceRequestLogDAO.insertInterfaceRequestLog(interfaceRequestLogDO);
            }
        }).start();

    }
}
