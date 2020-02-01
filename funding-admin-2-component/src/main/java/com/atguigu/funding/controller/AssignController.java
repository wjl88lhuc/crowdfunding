package com.atguigu.funding.controller;

import com.atguigu.funding.api.AuthService;
import com.atguigu.funding.api.RoleService;
import com.atguigu.funding.entity.Auth;
import com.atguigu.funding.entity.ResultEntity;
import com.atguigu.funding.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class AssignController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping("/assgin/do/assgin")
    public ResultEntity<String> doRoleAssignAuth(@RequestBody Map<String, List<Integer>> assginDataMap){
        authService.updateRelationshipBetweenRoleAndAuth(assginDataMap);
        return ResultEntity.successWithoutData();
    }
    @ResponseBody
    @RequestMapping("/assinge/get/assinged/auth/id/list")
    public ResultEntity<List<Integer>> getAssingedAuthIdList(Integer roleId){
        List<Integer> assingedAuthIdList = authService.getAssingedAuthIdList(roleId);
        return ResultEntity.successWithData(assingedAuthIdList);
    }


    @ResponseBody
    @RequestMapping("/assign/get/all/auth")
    public ResultEntity<List<Auth>> getAllAuth(){
        List<Auth> authList = authService.getAllAuth();
        return ResultEntity.successWithData(authList);
    }

    @RequestMapping("/assign/to/assign/role/page")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, Model model){
        //1.查询已经分配的角色
        List<Role> assignedRoleList = roleService.getAssignedRoleList(adminId);
        //2.查询未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRoleList(adminId);

        //存入模型中
        model.addAttribute("assignedRoleList",assignedRoleList);
        model.addAttribute("unAssignedRoleList",unAssignedRoleList);
        return "assign-role";
    }

    /**
     * roleIdList 这个参数由可能没有（也就是不一定每一次都有），所以 required = false
     * @param roleIdList
     * @param adminId
     * @return
     */
    @RequestMapping("/assign/role")
    public String doAssignRole(@RequestParam(value = "roleIdList",required = false) List<Integer> roleIdList,
                               @RequestParam("adminId") Integer adminId,
                                @RequestParam("pageNum") Integer pageNum){
        roleService.updateRelationShip(roleIdList,adminId);
        return "redirect:/admin/query/for/search.html?pageNum=" + pageNum;
    }

}
