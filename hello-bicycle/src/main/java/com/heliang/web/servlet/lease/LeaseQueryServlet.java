package com.heliang.web.servlet.lease;

import com.heliang.dto.LeaseDto;
import com.heliang.dto.Result;
import com.heliang.entity.User;
import com.heliang.service.LeaseService;
import com.heliang.utils.MapperUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LeaseQueryServlet extends HttpServlet {

    private static final LeaseService leaseService = new LeaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("online");
        List<LeaseDto> leaseDtos = leaseService.query(user.getId());
        Result result = Result.success(leaseDtos);
        out.println(MapperUtils.obj2json(result));
    }
}
