package com.enjoy.mybatis.ch03;

import com.enjoy.mybatis.ch03.entity.TUser;
import com.enjoy.mybatis.ch03.mappings.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/10 00:11<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class AssociationTest {

    // 必须要存在于整个应用的生命周期
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream input = Resources.getResourceAsStream(resource);
        // 1、读取 mybatis 配置文件并创建 SqlSessionFactory
        // SqlSessionFactoryBuilder数据方法级别，用完即丢
        factory = new SqlSessionFactoryBuilder().build(input);
        input.close();
    }

    @Test
    public void testOneToOne() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        //List<TUser> list = mapper.selectUserPosition1();
        List<TUser> list = mapper.selectUserPosition2();
        for (TUser user : list) {
            System.out.println(user.getPosition());
        }
    }

    @Test
    public void testOneToMany() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        //List<TUser> list = mapper.selectUserJobs1();
        List<TUser> list = mapper.selectUserJobs2();
        for (TUser user : list) {
            System.out.println(user.getJobs());
        }
    }

    @Test
    public void testDiscriminator() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        List<TUser> list = mapper.selectUserHealthReport();
        for (TUser user : list) {
            System.out.println(user.getHealthReports());
        }
    }

    @Test
    public void testManyToMany() {

    }
}
