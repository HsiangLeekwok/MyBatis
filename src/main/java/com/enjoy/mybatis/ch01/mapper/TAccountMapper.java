package com.enjoy.mybatis.ch01.mapper;

import com.enjoy.mybatis.ch01.entity.TAccount;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/07/19 11:00<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: Mapper<br/>
 * <b>Description</b>:
 */
public interface TAccountMapper {

    /**
     * 通过主键查询
     * @param id 主键 id
     * @return
     */
    TAccount selectByPrimaryKey(Integer id);
}
