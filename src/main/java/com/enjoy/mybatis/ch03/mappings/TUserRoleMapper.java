package com.enjoy.mybatis.ch03.mappings;

import com.enjoy.mybatis.ch03.entity.TUserRoleKey;

import java.util.List;

public interface TUserRoleMapper {
    int deleteByPrimaryKey(TUserRoleKey key);

    int insert(TUserRoleKey record);

    int insertSelective(TUserRoleKey record);

    List<TUserRoleKey> selectByUserId(Integer userId);
}