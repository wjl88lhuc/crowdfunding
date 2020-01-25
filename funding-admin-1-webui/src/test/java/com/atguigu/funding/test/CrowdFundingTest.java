package com.atguigu.funding.test;

import com.atguigu.funding.api.AdminService;
import com.atguigu.funding.dao.AdminDao;
import com.atguigu.funding.dao.RoleDao;
import com.atguigu.funding.entity.Admin;
import com.atguigu.funding.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdFundingTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminService adminService;

    //测试数据源
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    //测试mybatis
    @Test
    public void testMybatis() {
        List<Admin> adminList = adminService.getAll();
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
    }

    //测试事物与aop
    @Test
    public void testTx() {
        adminService.updateAdmin();
    }

    @Autowired
    AdminDao adminDao;

    @Test
    public void testSelectAdminListByKeyword() {
        List<Admin> adminList = adminDao.selectAdminListByKeyword("ha");
        for (Admin admin : adminList) {
            System.out.println(admin);
        }
    }

    @Autowired
    private RoleDao roleDao;

    @Test
    public void testSaveRole(){
        for (int i = 0; i < 100; i++) {
            roleDao.insert(new Role(null,"role" + i));
        }
    }

    @Test
    public void testInstant(){
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(now.format(dateTimeFormatter));
    }
}
