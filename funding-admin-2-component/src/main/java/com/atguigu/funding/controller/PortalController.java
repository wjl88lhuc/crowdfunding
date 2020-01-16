package com.atguigu.funding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PortalController {

    @RequestMapping("/index")
    public String showIndex(){
        //从数据库加载数据要显示的数据
        return "index-page";
    }
}
