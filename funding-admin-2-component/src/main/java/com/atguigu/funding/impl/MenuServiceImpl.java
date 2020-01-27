package com.atguigu.funding.impl;

import com.atguigu.funding.api.MenuService;
import com.atguigu.funding.dao.MenuDao;
import com.atguigu.funding.entity.Menu;
import com.atguigu.funding.entity.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public List<Menu> getAll() {
        return menuDao.selectByExample(new MenuExample());
    }

    @Override
    public void saveMenu(Menu menu) {
        menuDao.insert(menu);
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuDao.selectByPrimaryKey(menuId);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuDao.updateByPrimaryKey(menu);
    }
}
