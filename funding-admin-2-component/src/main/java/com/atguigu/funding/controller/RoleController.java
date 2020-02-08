package com.atguigu.funding.controller;

import com.atguigu.funding.api.RoleService;
import com.atguigu.funding.entity.ResultEntity;
import com.atguigu.funding.entity.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/refresh/role/fresh")
    public ResultEntity<String> updateRoleByRoleId(@RequestParam("roleId") Integer roleId,@RequestParam("roleName") String roleName){
        roleService.updateByRoleId(roleId,roleName);
        return ResultEntity.successWithoutData();
    }

    //SpEL表达式
    @PreAuthorize("hasAuthority('role:get')")
    @RequestMapping("/role/search/by/keyword")
    public ResultEntity<PageInfo<Role>> search(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword" ,defaultValue = "") String kerword
    ){
        PageInfo<Role> rolePageInfo = roleService.queryForKeywordWithPage(pageNum, pageSize, kerword);
        return ResultEntity.successWithData(rolePageInfo);
    }

    @RequestMapping("/role/get/list/by/id/list")
    public ResultEntity<List<Role>> getRoleListByidList(@RequestBody List<Integer> roleIdList ){
        List<Role> roleList = roleService.getRoleListByidList(roleIdList);
        return ResultEntity.successWithData(roleList);
    }

    @RequestMapping("/role/batch/remove")
    public ResultEntity<String> batchRemove(@RequestBody List<Integer> roleIdList){
        roleService.batchRemove(roleIdList);
        return ResultEntity.successWithoutData();
    }

    @RequestMapping("/role/save/role")
    public ResultEntity<String> saveRole(@RequestParam("roleName") String roleName ){
        roleService.saveRole(roleName);
        return ResultEntity.successWithoutData();
    }





}
