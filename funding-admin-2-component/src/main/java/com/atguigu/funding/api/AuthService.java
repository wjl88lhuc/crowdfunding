package com.atguigu.funding.api;

import com.atguigu.funding.entity.Auth;

import java.util.List;
import java.util.Map;

public interface AuthService {
    List<Auth> getAllAuth();

    List<Integer> getAssingedAuthIdList(Integer roleId);

    void updateRelationshipBetweenRoleAndAuth(Map<String, List<Integer>> assginDataMap);
}
