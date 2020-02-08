package com.atguigu.funding.impl;

import com.atguigu.funding.config.SecurityAdmin;
import com.atguigu.funding.dao.AdminDao;
import com.atguigu.funding.dao.AuthDao;
import com.atguigu.funding.dao.RoleDao;
import com.atguigu.funding.entity.Admin;
import com.atguigu.funding.entity.AdminExample;
import com.atguigu.funding.entity.Auth;
import com.atguigu.funding.entity.Role;
import com.atguigu.funding.util.CrowdFundingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FundingUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AuthDao authDao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AdminExample adminExample = new AdminExample();

        //封装查询条件
        adminExample.createCriteria().andLoginAcctEqualTo(userName);
        // 执行查询
        List<Admin> adminList = adminDao.selectByExample(adminExample);
        //检查adminList是否有效
        if (!CrowdFundingUtils.collectionEffective(adminList)){
            return null;
        }
        //从 adminList 取出Admin对象
        Admin admin = adminList.get(0);

        //封装角色权限信息。 authorityList 用于存储角色权限信息
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Role> roleList = roleDao.selectAssignedRoleList(admin.getId());
        //遍历角色集合
        for (Role role : roleList) {
            authorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }

        //查询当前admin对应的权限
        List<Auth> authList = authDao.selectAuthListByAdminId(admin.getId());
        for (Auth auth : authList) {
            String authName = auth.getName();
            if (!CrowdFundingUtils.stringEffective(authName)){
                continue;  //如果authName 不是有效字符串就抛弃
            }
            authorityList.add(new SimpleGrantedAuthority(authName));
        }
        //返回User对象
        return new SecurityAdmin(admin,authorityList);
    }
}
