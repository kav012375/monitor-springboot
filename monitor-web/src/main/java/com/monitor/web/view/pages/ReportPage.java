package com.monitor.web.view.pages;

import com.monitor.dal.dailyReport.dto.DailyRportQueryDTO;
import com.monitor.dal.dailyReport.entity.DailyReportDO;
import com.monitor.service.constants.ErrorCodeEnum;
import com.monitor.service.report.DailyReportService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by Zeus Feng on 2017/2/21.
 *
 * @author Zeus Feng
 * @date 2017/02/21
 */
@RestController
@RequestMapping(value = "/report")
public class ReportPage {

    @Autowired
    DailyReportService dailyReportService;

    @RequestMapping(value = "/frame")
    @ResponseBody
    public ModelAndView reportPageReportFrame(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/report/reportFrame");
        return modelAndView;
    }

    @RequestMapping(value = "daily")
    @ResponseBody
    public ModelAndView reportPageReportDaily(
            @Param("reportType") String reportType,
            @Param("reportTime") String reportTime
    ){
        ModelAndView modelAndView = new ModelAndView();
        DailyRportQueryDTO dailyRportQueryDTO = new DailyRportQueryDTO();
        dailyRportQueryDTO.setStartTime( reportTime + " 00:00:00");
        dailyRportQueryDTO.setEndTime( reportTime + " 23:59:59");
        modelAndView.setViewName("/report/dailyreport");
        List<DailyReportDO> dailyReportDOs = dailyReportService.getDailyReportByTime(dailyRportQueryDTO);
        if (dailyReportDOs == null){
            modelAndView.addObject("errormsg", ErrorCodeEnum.NO_DAILY_REPROT.getDescription());
        }else {
            modelAndView.addObject("dataresource",dailyReportDOs);
        }
        return modelAndView;
    }
}
