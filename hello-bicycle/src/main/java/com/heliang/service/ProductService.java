package com.heliang.service;

import com.heliang.dao.ProductDao;
import com.heliang.entity.Product;

import java.util.List;

public class ProductService {

    private static final ProductDao productDao = new ProductDao();

    public long create(Product product) {
        return productDao.create(product);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }

    public void update(Product product) {
        productDao.update(product);
    }

    public List<Product> query() {
        return productDao.query();
    }
}
