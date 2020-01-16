package com.atguigu.funding.impl;

import com.atguigu.funding.api.AdminService;
import com.atguigu.funding.dao.AdminDao;
import com.atguigu.funding.entity.Admin;
import com.atguigu.funding.entity.AdminExample;
import com.atguigu.funding.util.CrowdFundingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;


    @Override
    public List<Admin> getAll() {
        return adminDao.selectByExample(new AdminExample());
    }

    @Override
    public void updateAdmin() {
        adminDao.updateByPrimaryKey(new Admin(1,"wade98","fb89mayun","wade","wade@126.com","2019-09-21"));
        adminDao.updateByPrimaryKey(new Admin(2,"Yaomingsb","89yaomin","Yaoming","Yaomingsb@126.com","2019-02-01"));
    }

    @Override
    public Admin login(String loginAcct, String userPswd) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);
        //执行查询
        List<Admin> adminList = adminDao.selectByExample(adminExample);
        if (!CrowdFundingUtils.collectionEffective(adminList)){
            //如果查询结果的集合是无效的，那么就直接返回null
            return null;
        }
        Admin admin = adminList.get(0);
        if (admin == null){
            return null;
        }

        if (Objects.equals(CrowdFundingUtils.md5(userPswd), admin.getUserPswd())){
            return admin;
        }
        return null;
    }
}
