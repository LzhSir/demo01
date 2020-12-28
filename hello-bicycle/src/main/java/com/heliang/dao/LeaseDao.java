package com.heliang.dao;

import com.heliang.entity.Lease;
import com.heliang.utils.DataSourceUtils;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaseDao {

    private static final QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

    public List<Lease> query() {
        String sql = "select * from lease";
        List<Lease> leases = new ArrayList<>();
        try {
            //开启驼峰映射
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            leases = runner.query(sql, new BeanListHandler<>(Lease.class, processor));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }
    public List<Lease> query(Long userId) {
        String sql = "select * from lease where user_id = ?";
        Object[] params = new Object[] {userId};
        List<Lease> leases = new ArrayList<>();
        try {
            //开启驼峰映射
            BeanProcessor bean = new GenerousBeanProcessor();
            RowProcessor processor = new BasicRowProcessor(bean);
            leases = runner.query(sql, new BeanListHandler<>(Lease.class, processor), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leases;
    }


    public void hire(Lease lease) {
        String sql = "insert into lease (user_id, product_id, duration, cost, expire_time) values (?, ?, ?, ?, ?)";
        Object[] params = new Object[] {lease.getUserId(), lease.getProductId(), lease.getDuration(), lease.getCost(), lease.getExpireTime()};
        try {
            runner.insert(sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void back(Long id) {
        String sql = "update lease set expired=?, backed=? where id=?";
        Object[] params = new Object[] {true, true, id};
        try {
            runner.update(sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
