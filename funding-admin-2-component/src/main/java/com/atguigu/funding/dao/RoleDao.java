package com.atguigu.funding.dao;

import com.atguigu.funding.entity.Role;
import com.atguigu.funding.entity.RoleExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleDao {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<Role> selectForKeywordSearch(String keyword);

    List<Role> selectAssignedRoleList(Integer adminId);

    List<Role> selectUnAssignedRoleList(Integer adminId);

    void deleteOldAdminRelationShip(Integer adminId);

    void insertNewAdminRelationShip(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);
}