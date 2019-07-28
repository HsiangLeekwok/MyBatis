package com.enjoy.mybatis.ch01.mapper;

import com.enjoy.mybatis.ch01.entity.EmailAndSexBean;
import com.enjoy.mybatis.ch01.entity.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/07/26 09:58<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public interface TUserMapper {

    TUser selectByPrimaryKey(Integer id);

    TUser selectByPrimaryKey1(Integer id);

    List<TUser> selectByEmailAndSex1(Map<String, Object> map);

    List<TUser> selectByEmailAndSex2(@Param("email") String email, @Param("sex") Byte sex);

    List<TUser> selectByEmailAndSex3(EmailAndSexBean easb);

    int deleteByPrimaryKey(Integer id);

    int insert(TUser user);

    int insert1(TUser user);

    int insert2(TUser user);

    int insertForeach4Batch(List<TUser> list);

    List<TUser> selectBySymbol(@Param("tableName") String tableName,@Param("inCol") String inCol,@Param("orderStr") String orderStr,@Param("sex") Byte sex);

    int updateIfAndSetOper(TUser user);
}
