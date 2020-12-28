package com.heliang.web.servlet.product;

import com.heliang.dto.Result;
import com.heliang.service.ProductService;
import com.heliang.utils.MapperUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductDeleteServlet extends HttpServlet {

    private static final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Long id = Long.parseLong(request.getParameter("id"));
        productService.delete(id);
        Result result = Result.success();
        out.println(MapperUtils.obj2json(result));
    }
}
