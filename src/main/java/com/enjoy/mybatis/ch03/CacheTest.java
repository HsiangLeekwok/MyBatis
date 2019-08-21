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
 * <b>Date</b>: 2019/08/11 18:37<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 测试缓存<br/>
 * <b>Description</b>:
 */
public class CacheTest {


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

    /**
     * 测试一级缓存，相同的查询不会再从数据库中捞数据， 而是从缓存中直接拿取
     * 但所有的Update、Insert、Delete会直接清空已有的缓存
     */
    @Test
    public void testLevel1Cache() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        // 查询
        String email = "qq.com";
        Byte sex = 1;
        List<TUser> list = mapper.selectByEmailAndSexWithTrim(email, sex);
        System.out.println(list.size());

        // 查询另外一个条件
        sex = 2;
        list = mapper.selectByEmailAndSexWithTrim(null, sex);
        System.out.println(list.size());

        // 再查询第一个条件，此时不会再从数据库中捞取数据，而是从缓存中直接返回了，log打印出的sql语句只有2句
        sex = 1;
        list = mapper.selectByEmailAndSexWithTrim(email, sex);
        System.out.println(list.size());
    }

    @Test
    public void testLevel2CacheClear() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        // 查询
        String email = ".com";
        Byte sex = 1;
        List<TUser> list = mapper.selectByEmailAndSexWithTrim(email, sex);
        for (TUser user : list) {
            System.out.println(user.getRealName());
        }
        //System.out.println(list.size());

        // 更新一下
        TUser user = new TUser();
        user.setId(2);
        user.setSex((byte) 2);
        int updated = mapper.updateByPrimaryKeySelective(user);
        session.commit();
        System.out.println("updated: " + updated);

        // 再查询第一个条件，此时会再从数据库中捞取数据，因为更新导致缓存被清空了
        sex = 1;
        list = mapper.selectByEmailAndSexWithTrim(email, sex);
        System.out.println(list.size());
    }
}
