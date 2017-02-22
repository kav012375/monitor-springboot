package com.monitor.service.interfaces.request.log;

import com.monitor.dal.interfaceRequestLog.dto.InterfaceRequestLogQueryDTO;
import com.monitor.dal.interfaceRequestLog.entity.InterfaceRequestLogDO;
import com.monitor.dal.task.entity.TaskDO;
import org.springframework.stereotype.Service;

/**
 * Created by Zeus Feng on 2017/2/19.
 *
 * @author Zeus Feng
 * @date 2017/02/19
 */
public interface InterfaceRequestLogService {
    /**
     * 获取当天的任务请求量
     * @return
     */
    Long getRequestTimesByCurrentDay();

    /**
     * 记录请求日志到请求表
     * @param interfaceRequestLogDO
     */
    void saveRequestRecord(final InterfaceRequestLogDO interfaceRequestLogDO);

    /**
     * 检查IP是否重复
     * @param interfaceRequestLogQueryDTO
     * @return
     */
    boolean checkDuplicateIp(final InterfaceRequestLogQueryDTO interfaceRequestLogQueryDTO);

    /**
     * 组装接口查询条件
     * @param taskDO
     * @param ipAddress
     * @return
     */
    InterfaceRequestLogQueryDTO assembleInterfaceRequestLogQyueryUtil(TaskDO taskDO,String ipAddress);
}
