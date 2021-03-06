package com.atguigu.funding.api;

import com.atguigu.funding.dao.RoleDao;
import com.atguigu.funding.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface RoleService {
    PageInfo<Role>queryForKeywordWithPage(Integer pageNum,Integer pageSize,String keyword);

    List<Role> getRoleListByidList(List<Integer> roleIdList);

    void batchRemove(List<Integer> roleIdList);

    void saveRole(String roleName);

    void updateByRoleId(Integer roleId,String roleName);

    List<Role> getAssignedRoleList(Integer adminId);

    List<Role> getUnAssignedRoleList(Integer adminId);

    void updateRelationShip(List<Integer> roleIdList, Integer adminId);
}
