package com.heliang.service;

import com.heliang.dao.LeaseDao;
import com.heliang.dao.ProductDao;
import com.heliang.dto.LeaseDto;
import com.heliang.entity.Lease;
import com.heliang.entity.Product;

import java.math.BigDecimal;
import java.util.*;

public class LeaseService {

    private static final LeaseDao leaseDao = new LeaseDao();

    private static final ProductDao productDao = new ProductDao();

    public List<LeaseDto> query(Long userId) {
        List<Lease> leases = leaseDao.query(userId);
        List<LeaseDto> leaseDtos = new ArrayList<>(leases.size());
        for (Lease lease : leases) {
            Product product = productDao.queryItem(lease.getProductId());
            LeaseDto leaseDto = new LeaseDto(lease.getId(), lease.getUserId(), product
                    , lease.getDuration(), lease.getCost(), lease.getExpireTime(), lease.getExpired(), lease.getBacked());
            leaseDtos.add(leaseDto);
        }
        return leaseDtos;
    }

    public void hire(Long userId, Long productId, Integer duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, duration);
        Product product = productDao.queryItem(productId);
        BigDecimal rent = product.getRent();
        BigDecimal total = rent.multiply(new BigDecimal(duration));
        Lease lease = new Lease(userId, productId, duration, total, calendar.getTime());
        leaseDao.hire(lease);
        productDao.updateStatus(productId, true);
    }

    public void back(Long id, Long productId) {
        leaseDao.back(id);
        productDao.updateStatus(productId, false);
    }
}
