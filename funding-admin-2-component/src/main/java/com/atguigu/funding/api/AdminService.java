package com.atguigu.funding.api;

import com.atguigu.funding.entity.Admin;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdminService {

    List<Admin> getAll();

    void updateAdmin();

    Admin login(String loginAcct, String userPswd);

    PageInfo<Admin> queryForKeywordSearch(Integer pageNum,Integer pageSize,String keyword);

    void batchRemove(List<Integer> adminArray);

    void saveAdmin(Admin admin);

    Admin getAdminById(Integer adminId);

    void updateAdmin(Admin admin);
}
