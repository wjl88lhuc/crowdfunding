package com.atguigu.funding.controller;

import com.atguigu.funding.api.MenuService;
import com.atguigu.funding.entity.Menu;
import com.atguigu.funding.entity.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 这个组装树形节点的逻辑前提是这颗树只有一个根节点
     * @return
     */
    @RequestMapping("/menu/get/whole/tree")
    public ResultEntity<Menu> getWholeTree(){
        //1. 查询所有的树形节点，用于组装
        List<Menu> menuList =menuService.getAll();
        //2. 将 List<Menu> 转换为Map<Menu的id,Menu本身>
        Map<Integer,Menu> menuMap = new HashMap<Integer, Menu>();
        for (Menu menu : menuList) {
            menuMap.put(menu.getId(),menu);
        }

        //3. 声明变量用于存储根节点对象
        Menu rootNode = null;

        //3. 遍历 menuList ，找 根节点
        for (Menu menu : menuList) {
            //获取当前对象的pid
            Integer pid = menu.getPid();
            //判断当前节点的pid（父节点）是否是 null,如果是 null 的话，那么当前节点就是根节点
            if (pid == null) {
                rootNode = menu; //找到了根节点，结束本次循环,继续下一次循环。因为根节点没有根节点
                continue;
            }
            //既然pid不为 null,那么我们根据这个pid查找当前节点的父节点,从menuMap中查找当前节点的父节点
            Menu myfather = menuMap.get(pid);
            //找到父节点，组装到父节点的子节点集合中
            myfather.getChildren().add(menu);
        }
        return ResultEntity.successWithData(rootNode);
    }

    @RequestMapping("/menu/save")
    public ResultEntity<String> saveMenu(Menu menu){
        System.out.println("********请求到这里了**********");
        System.out.println("menu:" + menu);
        menuService.saveMenu(menu);
        return  ResultEntity.successWithoutData();
    }

    @RequestMapping("/menu/get/{menuId}")
    public ResultEntity<Menu> getMenuById(@PathVariable("menuId") Integer menuId){
        Menu menu = menuService.getMenuById(menuId);
        return  ResultEntity.successWithData(menu);
    }

    @RequestMapping("/menu/update")
    public ResultEntity<String> updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return  ResultEntity.successWithoutData();
    }


}
