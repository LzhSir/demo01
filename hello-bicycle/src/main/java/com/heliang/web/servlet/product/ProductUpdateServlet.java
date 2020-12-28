package com.heliang.web.servlet.product;

import com.heliang.dto.Result;
import com.heliang.entity.Product;
import com.heliang.service.ProductService;
import com.heliang.utils.MapperUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class ProductUpdateServlet extends HttpServlet {

    private static final ProductService productService = new ProductService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Long id = Long.parseLong(request.getParameter("id"));
        String brand = request.getParameter("brand");
        String model = request.getParameter("model");
        BigDecimal rent = new BigDecimal(request.getParameter("rent"));
        String licence = request.getParameter("licence");
        Product product = new Product(id, brand, model, rent, licence);
        productService.update(product);
        Result result = Result.success();
        out.println(MapperUtils.obj2json(result));
    }
}
