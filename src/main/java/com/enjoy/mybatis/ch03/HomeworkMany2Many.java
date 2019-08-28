package com.enjoy.mybatis.ch03;

import com.enjoy.mybatis.ch03.entity.TRole;
import com.enjoy.mybatis.ch03.entity.TUser;
import com.enjoy.mybatis.ch03.mappings.TRoleMapper;
import com.enjoy.mybatis.ch03.mappings.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import javax.management.relation.Role;
import java.io.InputStream;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/08/11 23:19<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 作业：使用数据库中t_user表和t_role表实现多对多的关联（注意使用中间表t_user_role），两个一对多关系一个使用嵌套查询（要体现延迟加载），另外一个使用嵌套结果<br/>
 * <b>Description</b>:
 */
public class HomeworkMany2Many {

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
    public void testMany2Many() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        List<TUser> list = mapper.selectUserRoles();
        for (TUser user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testMany2ManyAllInOne() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        List<TUser> list = mapper.selectUserRolesAllInOne();
        for (TUser user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testMany2Many1() {
        SqlSession session = factory.openSession();
        TRoleMapper mapper = session.getMapper(TRoleMapper.class);

        List<TRole> list = mapper.selectRoleUsers();
        for (TRole role : list) {
            System.out.println(role);
        }
    }
}
