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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class RegisterServlet extends HttpServlet {

    private static final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Result result = Result.success();
        String nickname = request.getParameter("nickname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Optional<User> userOpt = userService.queryByUsername(username);
        if (!userOpt.isPresent()) {
            User user = new User(nickname, username, password);
            long id = userService.create(user);
            if (id == -1) {
                result = Result.failure(ResultInfo.INTERNAL_SERVER_ERROR);
            }
        } else {
            result = Result.failure(ResultInfo.USER_EXIST_ERROR);
        }
        out.println(MapperUtils.obj2json(result));
    }
}
