package com.atguigu.funding.controller;

import com.atguigu.funding.api.RoleService;
import com.atguigu.funding.entity.ResultEntity;
import com.atguigu.funding.entity.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/role/search/by/keyword")
    public ResultEntity<PageInfo<Role>> search(
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
            @RequestParam(value = "keyword" ,defaultValue = "") String kerword
    ){
        PageInfo<Role> rolePageInfo = roleService.queryForKeywordWithPage(pageNum, pageSize, kerword);
        return ResultEntity.successWithData(rolePageInfo);
    }

    @ResponseBody
    @RequestMapping("/role/get/list/by/id/list")
    public ResultEntity<List<Role>> getRoleListByidList(@RequestBody List<Integer> roleIdList ){
        List<Role> roleList = roleService.getRoleListByidList(roleIdList);
        return ResultEntity.successWithData(roleList);
    }

    @ResponseBody
    @RequestMapping("/role/batch/remove")
    public ResultEntity<String> batchRemove(@RequestBody List<Integer> roleIdList){
        roleService.batchRemove(roleIdList);
        return ResultEntity.successWithoutData();
    }





}
