package com.atguigu.funding.controller;

import com.atguigu.funding.api.AdminService;
import com.atguigu.funding.entity.Admin;
import com.atguigu.funding.util.CrowdFundingConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/get/all")
    public String getAll(Model model){
        List<Admin> adminList = adminService.getAll();
        model.addAttribute("list",adminList);
        return "admin-target";
    }

    /*@RequestMapping("/login")
    public String commonLogin(){
        return "admin-login";
    }*/

    @RequestMapping("/admin/to/login/page")
    public String toLoginPage(){
        return "admin-login";
    }

    @RequestMapping("/admin/do/login")
    public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd,
                          Model model, HttpSession session){
        Admin admin = adminService.login(loginAcct,userPswd);
        if (admin == null){
            //登陆失败
            model.addAttribute(CrowdFundingConstant.ATTR_NAME_MESSAGE,CrowdFundingConstant.MESSAGE_LOGIN_FAILD);
            return "admin-login";
        }
        session.setAttribute(CrowdFundingConstant.ATTR_NAME_LOGIN_ADMIN,admin);
        return "admin-main";
    }

    @RequestMapping("/admin/logout")
    public String logout(HttpSession session){
        session.invalidate();//销毁session
        return "redirect:/index-page.html";
    }

    @RequestMapping("/index-page")
    public String indexVisit(){
        return "index-page";
    }
}
