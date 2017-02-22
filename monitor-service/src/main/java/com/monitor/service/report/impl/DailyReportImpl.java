package com.monitor.service.report.impl;

import com.monitor.dal.dailyReport.dao.DailyReportDAO;
import com.monitor.dal.dailyReport.dto.DailyRportQueryDTO;
import com.monitor.dal.dailyReport.entity.DailyReportDO;
import com.monitor.service.report.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
public class DailyReportImpl implements DailyReportService {
    @Autowired
    DailyReportDAO dailyReportDAO;

    @Override
    public List<DailyReportDO> getDailyReportByTime(DailyRportQueryDTO dailyRportQueryDTO) {
        return dailyReportDAO.findDailyReportContentByTime(dailyRportQueryDTO);
    }
}
