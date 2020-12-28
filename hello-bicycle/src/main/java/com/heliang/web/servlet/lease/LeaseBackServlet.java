package com.heliang.web.servlet.lease;

import com.heliang.dto.Result;
import com.heliang.service.LeaseService;
import com.heliang.utils.MapperUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LeaseBackServlet extends HttpServlet {

    private static final LeaseService leaseService = new LeaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Long id = Long.parseLong(request.getParameter("id"));
        Long productId = Long.parseLong(request.getParameter("productId"));
        leaseService.back(id, productId);
        Result result = Result.success();
        out.println(MapperUtils.obj2json(result));
    }
}
