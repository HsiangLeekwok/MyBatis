package com.enjoy.mybatis.ch03.mappings;

import com.enjoy.mybatis.ch03.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    List<TUser> selectByEmailAndSexWithTrim(@Param("email") String email, @Param("sex") Byte sex);

    List<TUser> selectUserPosition1();

    List<TUser> selectUserPosition2();

    List<TUser> selectUserJobs1();

    List<TUser> selectUserJobs2();

    List<TUser> selectUserHealthReport();

    List<TUser> selectUserRoles();

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);
}