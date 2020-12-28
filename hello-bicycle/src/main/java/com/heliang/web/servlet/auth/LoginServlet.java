package com.heliang.web.servlet.auth;

import com.heliang.dto.Result;
import com.heliang.entity.User;
import com.heliang.enums.ResultInfo;
import com.heliang.service.UserService;
import com.heliang.utils.MapperUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private static final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result result = Result.success();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> userOpt = userService.queryByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                HttpSession session = request.getSession();
                session.setAttribute("online", user);
            } else {
                result = Result.failure(ResultInfo.BAD_CREDENTIALS);
            }
        } else {
            result = Result.failure(ResultInfo.BAD_CREDENTIALS);
        }
        out.println(MapperUtils.obj2json(result));
    }
}
