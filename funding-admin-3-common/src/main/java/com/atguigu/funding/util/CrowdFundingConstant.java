package com.atguigu.funding.util;

import java.util.HashMap;
import java.util.Map;

public class CrowdFundingConstant {
    public static final String ATTR_NAME_MESSAGE ="MESSAGE";
    public static final String ATTR_NAME_LOGIN_ADMIN = "LOGIN-ADMIN";
    public static final String ATTR_NAME_PAGE_INFO = "PAGE-INFO";

    public static final String MESSAGE_LOGIN_FAILD ="登陆账号或密码有误，请核对后再登陆";
    public static final String MESSAGE_CODE_INVALID ="明文不是有效字符串，请核对后再操作";
    public static final String MESSAGE_ACCESS_DENIED="请登录后再操作";
    public static final String MESSAGE_LOGINACCT_ALREADY_IN_USE = "登陆账号已经被使用了，请重新设定";

    public static final Map<String ,String> EXCEPTION_TO_MESSAGEMAP = new HashMap<>();
    static {
        EXCEPTION_TO_MESSAGEMAP.put(ArithmeticException.class.getName(),"系统在数学运算是发生的错误");
        EXCEPTION_TO_MESSAGEMAP.put(RuntimeException.class.getName(),"系统在运行时候发生异常");
        EXCEPTION_TO_MESSAGEMAP.put("com.atguigu.funding.except.LoginException","系统在登陆时发生异常");//自定义的异常
        EXCEPTION_TO_MESSAGEMAP.put("org.springframework.security.access.AccessDeniedException","尊敬的用户，你不具备你正在访问的资源的权限，请先联系超级管理员");
    }

}
