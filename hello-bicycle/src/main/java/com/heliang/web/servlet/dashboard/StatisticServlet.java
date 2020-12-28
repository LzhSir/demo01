package com.heliang.web.servlet.dashboard;

import com.heliang.dto.Result;
import com.heliang.dto.StatisticInfo;
import com.heliang.service.DashboardService;
import com.heliang.utils.MapperUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class StatisticServlet extends HttpServlet {

    private static final DashboardService dashboardService = new DashboardService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        StatisticInfo statisticInfo = dashboardService.getStatisticInfo();
        Result result = Result.success(statisticInfo);
        out.println(MapperUtils.obj2json(result));
    }
}
