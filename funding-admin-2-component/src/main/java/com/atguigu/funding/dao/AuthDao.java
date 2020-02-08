package com.atguigu.funding.dao;

import java.util.List;

import com.atguigu.funding.entity.Auth;
import com.atguigu.funding.entity.AuthExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuthDao {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> selectAssignedAuthIdList(int roleId);

    void deleteOldRelationshp(Integer roleId);

    void insertNewRelationship(@Param("roleId") Integer roleId,@Param("authIdList") List<Integer> authIdList);

    List<Auth> selectAuthListByAdminId(Integer adminId);
}