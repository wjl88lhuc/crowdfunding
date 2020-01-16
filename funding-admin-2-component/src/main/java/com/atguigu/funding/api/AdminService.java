package com.atguigu.funding.api;

import com.atguigu.funding.entity.Admin;

import java.util.List;

public interface AdminService {

    List<Admin> getAll();

    void updateAdmin();

    Admin login(String loginAcct, String userPswd);
}
