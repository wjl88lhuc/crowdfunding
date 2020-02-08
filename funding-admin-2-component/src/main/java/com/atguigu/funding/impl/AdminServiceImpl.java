package com.atguigu.funding.impl;

import com.atguigu.funding.api.AdminService;
import com.atguigu.funding.dao.AdminDao;
import com.atguigu.funding.entity.Admin;
import com.atguigu.funding.entity.AdminExample;
import com.atguigu.funding.util.CrowdFundingConstant;
import com.atguigu.funding.util.CrowdFundingUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Admin> getAll() {
        return adminDao.selectByExample(new AdminExample());
    }

    @Override
    public void updateAdmin() {
        adminDao.updateByPrimaryKey(new Admin(1, "wade98", "fb89mayun", "wade", "wade@126.com", "2019-09-21"));
        adminDao.updateByPrimaryKey(new Admin(2, "Yaomingsb", "89yaomin", "Yaoming", "Yaomingsb@126.com", "2019-02-01"));
    }

    @Override
    public Admin login(String loginAcct, String userPswd) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);
        //执行查询
        List<Admin> adminList = adminDao.selectByExample(adminExample);
        if (!CrowdFundingUtils.collectionEffective(adminList)) {
            //如果查询结果的集合是无效的，那么就直接返回null
            return null;
        }
        Admin admin = adminList.get(0);
        if (admin == null) {
            return null;
        }

        if (Objects.equals(CrowdFundingUtils.md5(userPswd), admin.getUserPswd())) {
            return admin;
        }
        return null;
    }

    @Override
    public PageInfo<Admin> queryForKeywordSearch(Integer pageNum,Integer pageSize,String keyword) {
     //调用PageHelper工具的方法，开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //执行分页查询
        List<Admin> adminList = adminDao.selectAdminListByKeyword(keyword);
        //将查询结果封装到PageInfo对象中即可
        return new PageInfo<Admin>(adminList);
    }

    @Override
    public void batchRemove(List<Integer> adminArray) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdIn(adminArray);
        adminDao.deleteByExample(adminExample);
    }

    @Override
    public void saveAdmin(Admin admin) {
        //对密码进行加密
//        String userPswd = CrowdFundingUtils.md5(admin.getUserPswd());
        String userPswd = passwordEncoder.encode(admin.getUserPswd());  //使用springSecurity的加密方法加密
        admin.setUserPswd(userPswd);
        if (admin != null && admin.getCreateTime() == null){
            LocalDateTime timeNow = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            admin.setCreateTime(timeNow.format(dateTimeFormatter));
        }
        //执行保存
        adminDao.insert(admin);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminDao.selectByPrimaryKey(adminId);
    }

    @Override
    public void updateAdmin(Admin admin) {
        String userPswd = CrowdFundingUtils.md5(admin.getUserPswd());
        admin.setUserPswd(userPswd);
        adminDao.updateByPrimaryKey(admin);//执行更新操作
    }
}
