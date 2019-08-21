package com.enjoy.mybatis.ch03.mappings;

import com.enjoy.mybatis.ch03.entity.TJobHistory;

import java.util.List;

public interface TJobHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TJobHistory record);

    int insertSelective(TJobHistory record);

    TJobHistory selectByPrimaryKey(Integer id);

    List<TJobHistory> selectByUserId(Integer userId);

    int updateByPrimaryKeySelective(TJobHistory record);

    int updateByPrimaryKey(TJobHistory record);
}