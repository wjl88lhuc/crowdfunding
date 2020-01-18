package com.atguigu.funding.controller;

import com.atguigu.funding.api.AdminService;
import com.atguigu.funding.entity.Admin;
import com.atguigu.funding.entity.ResultEntity;
import com.atguigu.funding.util.CrowdFundingConstant;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * @ResponseBody 作用就是将当前方法的返回值当作响应体，不再经过视图解析器了。将java类型转换成为json格式返回给请求者。
     * @RequestBody的作用： 将json格式的请求体转换为java类型
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/batch/remove")
    public ResultEntity<String> batchMove(@RequestBody List<Integer> adminArray) {
        try {
            adminService.batchRemove(adminArray);
            return ResultEntity.successWithoutData();
        } catch (Exception e) {
            return ResultEntity.fail(null,e.getMessage());
        }
    }

    @RequestMapping("/admin/query/for/search")
    public String queryForsearch(
            //如果页面上没有提供对应的参数（没有传过来），那么就使用defaultValue指定的默认值
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "6") Integer pageSize,
            @RequestParam(value = "keyword", defaultValue = "") String keyword, Model model) {
        PageInfo<Admin> pageInfo = adminService.queryForKeywordSearch(pageNum, pageSize, keyword);
        System.out.println("总条数:" + pageInfo.getList().size());
        model.addAttribute(CrowdFundingConstant.ATTR_NAME_PAGE_INFO, pageInfo);
        return "admin-userManage";
    }

    @RequestMapping("/admin/get/all")
    public String getAll(Model model) {
        List<Admin> adminList = adminService.getAll();
        model.addAttribute("list", adminList);
        return "admin-target";
    }

    /*@RequestMapping("/login")
    public String commonLogin(){
        return "admin-login";
    }*/

    @RequestMapping("/admin/to/main/page")
    public String toMainPage() {
        return "admin-main";
    }

    @RequestMapping("/admin/to/login/page")
    public String toLoginPage() {
        return "admin-login";
    }

    @RequestMapping("/admin/do/login")
    public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd,
                          Model model, HttpSession session) {
        Admin admin = adminService.login(loginAcct, userPswd);
        if (admin == null) {
            //登陆失败
            model.addAttribute(CrowdFundingConstant.ATTR_NAME_MESSAGE, CrowdFundingConstant.MESSAGE_LOGIN_FAILD);
            return "admin-login";
        }
        session.setAttribute(CrowdFundingConstant.ATTR_NAME_LOGIN_ADMIN, admin);
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/admin/logout")
    public String logout(HttpSession session) {
        session.invalidate();//销毁session
        return "redirect:/index-page.html";
    }

    @RequestMapping("/index-page")
    public String indexVisit() {
        return "index-page";
    }

    @RequestMapping("/admin/queryAdminListByKeyword")
    public String queryAdminList() {
        return "admin-userManage";
    }
}
