package com.enjoy.mybatis.ch01;

import com.enjoy.mybatis.ch01.mapper.TAccountMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/07/19 11:07<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: MyBatis测试demo<br/>
 * <b>Description</b>:
 */
public class MyBatisDemo {

    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream input = Resources.getResourceAsStream(resource);
        // 1、读取 mybatis 配置文件并创建 SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(input);
        input.close();
    }

    @Test
    public void quickStart() throws Exception {
        // 2、获取sqlSession
        SqlSession session = factory.openSession();
        // 3、获取对应的mapper
        TAccountMapper mapper = session.getMapper(TAccountMapper.class);
        // 4、执行查询并返回结果
        TAccount account = mapper.selectByPrimaryKey(1);
        System.out.println(account);
    }
}
