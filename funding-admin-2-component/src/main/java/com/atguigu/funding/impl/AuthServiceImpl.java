package com.atguigu.funding.impl;

import com.atguigu.funding.api.AuthService;
import com.atguigu.funding.dao.AuthDao;
import com.atguigu.funding.entity.Auth;
import com.atguigu.funding.entity.AuthExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDao authDao;

    @Override
    public List<Auth> getAllAuth() {
        return authDao.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssingedAuthIdList(Integer roleId) {
        return authDao.selectAssignedAuthIdList(roleId);
    }

    @Override
    public void updateRelationshipBetweenRoleAndAuth(Map<String, List<Integer>> assginDataMap) {
        //1. 获取两部分list的数据
        List<Integer> roleIdList = assginDataMap.get("roleIdList");
        List<Integer> authIdList = assginDataMap.get("authIdList");

        //2. 取出roleId
        Integer roleId = roleIdList.get(0);

        //3 . 删除旧数据
        authDao.deleteOldRelationshp(roleId);

        //4. 保存新数据
        if (authIdList != null && authIdList.size() > 0){
            authDao.insertNewRelationship(roleId,authIdList);
        }
    }
}
