package com.atguigu.funding.impl;

import com.atguigu.funding.api.RoleService;
import com.atguigu.funding.dao.RoleDao;
import com.atguigu.funding.entity.Role;
import com.atguigu.funding.entity.RoleExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImple implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public PageInfo<Role> queryForKeywordWithPage(Integer pageNum, Integer pageSize, String keyword) {
        //开启分页功能
        PageHelper.startPage(pageNum,pageSize);
        //执行查询
        List<Role> roles = roleDao.selectForKeywordSearch(keyword);
        System.out.println("keyword:" + keyword);
        //封装为PageInfo
        return new PageInfo<>(roles);
    }

    @Override
    public List<Role> getRoleListByidList(List<Integer> roleIdList) {
        //预期的sql语句: select id,name from t_role where id in (1,2,3,4,5)
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(roleIdList);
        return roleDao.selectByExample(roleExample);
    }

    @Override
    public void batchRemove(List<Integer> roleIdList) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andIdIn(roleIdList);
        roleDao.deleteByExample(roleExample);
    }

    @Override
    public void saveRole(String roleName) {
        roleDao.insert(new Role(null,roleName));
    }

    @Override
    public void updateByRoleId(Integer roleId,String roleName) {
        roleDao.updateByPrimaryKey(new Role(roleId,roleName));
    }
}
