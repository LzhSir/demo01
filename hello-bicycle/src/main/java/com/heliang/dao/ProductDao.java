package com.heliang.dao;

import com.heliang.entity.Product;
import com.heliang.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    private static final QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

    public long create(Product product) {
        String sql = "insert into product (brand, model, rent, licence) values (?, ?, ?, ?)";
        Object[] params = new Object[] {product.getBrand(), product.getModel(), product.getRent(), product.getLicence()};
        long result = -1;
        try {
            result = runner.insert(sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(Long id) {
        String sql = "delete from product where id = ?";
        Object[] params = new Object[] {id};
        try {
            runner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Product product) {
        String sql = "update product set brand=?, model=?, rent=?, licence=? where id = ?";
        Object[] params = new Object[] {product.getBrand(), product.getModel(), product.getRent(), product.getLicence(), product.getId()};
        try {
            runner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(Long id, Boolean status) {
        String sql = "update product set status=? where id = ?";
        Object[] params = new Object[] {status, id};
        try {
            runner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> query() {
        String sql = "select * from product";
        List<Product> products = new ArrayList<>();
        try {
            products = runner.query(sql, new BeanListHandler<>(Product.class));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product queryItem(Long id) {
        String sql = "select * from product where id = ?";
        Object[] params = new Object[] {id};
        Product product = null;
        try {
            product = runner.query(sql, new BeanHandler<>(Product.class), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
