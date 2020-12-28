package com.heliang.service;

import com.heliang.dao.LeaseDao;
import com.heliang.dao.ProductDao;
import com.heliang.dao.UserDao;
import com.heliang.dto.StatisticInfo;
import com.heliang.entity.Lease;
import com.heliang.entity.Product;
import com.heliang.entity.User;

import java.util.List;

public class DashboardService {

    private static final UserDao userDao = new UserDao();

    private static final ProductDao productDao = new ProductDao();

    private static final LeaseDao leaseDao = new LeaseDao();

    public StatisticInfo getStatisticInfo() {
        List<User> users = userDao.query();
        List<Product> products = productDao.query();
        List<Lease> leases = leaseDao.query();
        int availableProduct = (int) products.stream().filter(product -> !product.getStatus()).count();
        return new StatisticInfo(products.size(), availableProduct, users.size(), leases.size());
    }
}
