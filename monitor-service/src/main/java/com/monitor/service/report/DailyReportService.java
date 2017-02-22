package com.monitor.service.report;

import com.monitor.dal.dailyReport.dto.DailyRportQueryDTO;
import com.monitor.dal.dailyReport.entity.DailyReportDO;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
public interface DailyReportService {
    /**
     * 通过时间获取日报表
     * @param dailyRportQueryDTO
     * @return
     */
    List<DailyReportDO> getDailyReportByTime(DailyRportQueryDTO dailyRportQueryDTO);
}
