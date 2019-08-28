package com.enjoy.mybatis.ch03.mappings;

import com.enjoy.mybatis.ch03.entity.TRole;

import java.util.List;

public interface TRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TRole record);

    int insertSelective(TRole record);

    TRole selectByPrimaryKey(Integer id);

    List<TRole> selectRoleUsers();

    int updateByPrimaryKeySelective(TRole record);

    int updateByPrimaryKey(TRole record);
}