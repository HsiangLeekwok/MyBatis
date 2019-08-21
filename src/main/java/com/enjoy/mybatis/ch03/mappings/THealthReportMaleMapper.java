package com.enjoy.mybatis.ch03.mappings;

import com.enjoy.mybatis.ch03.entity.THealthReportMale;

import java.util.List;

public interface THealthReportMaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THealthReportMale record);

    int insertSelective(THealthReportMale record);

    THealthReportMale selectByPrimaryKey(Integer id);

    List<THealthReportMale> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(THealthReportMale record);

    int updateByPrimaryKey(THealthReportMale record);
}