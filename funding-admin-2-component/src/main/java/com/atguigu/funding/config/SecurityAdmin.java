package com.atguigu.funding.config;

import com.atguigu.funding.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 扩展User类
 */
public class SecurityAdmin extends User {

    private Admin rawAdmin;

    public SecurityAdmin(Admin rawAdmin, Collection<? extends GrantedAuthority> authorities) {
        //调用父类的构造器
        super(rawAdmin.getLoginAcct(), rawAdmin.getUserPswd(), authorities);
        this.rawAdmin = rawAdmin;
    }

    public Admin getRawAdmin() {
        return rawAdmin;
    }


}
