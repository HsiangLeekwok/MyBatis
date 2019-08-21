package com.enjoy.mybatis.ch03.mappings;

import com.enjoy.mybatis.ch03.entity.THealthReportFemale;

import java.util.List;

public interface THealthReportFemaleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(THealthReportFemale record);

    int insertSelective(THealthReportFemale record);

    THealthReportFemale selectByPrimaryKey(Integer id);

    List<THealthReportFemale> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(THealthReportFemale record);

    int updateByPrimaryKey(THealthReportFemale record);
}