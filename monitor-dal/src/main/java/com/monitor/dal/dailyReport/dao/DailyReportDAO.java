package com.monitor.dal.dailyReport.dao;

import com.monitor.dal.dailyReport.dto.DailyRportQueryDTO;
import com.monitor.dal.dailyReport.entity.DailyReportDO;

import java.util.List;

/**
 * Created by zeusw on 2017/1/4.
 */
public interface DailyReportDAO {
    /**
     * 插入日常报告
     * @param dailyReportDO
     * @return
     */
    int insertDailyReport(DailyReportDO dailyReportDO);

    /**
     * 查询日常报告
     * @param DailyRportQueryDTO
     * @return
     */
    List<DailyReportDO> findDailyReportContentByTime(DailyRportQueryDTO DailyRportQueryDTO);

    /**
     * 删除次数为0的报告
     */
    void deleteZeroRunTimesReport();
}
