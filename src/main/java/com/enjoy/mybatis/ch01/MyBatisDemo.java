package com.enjoy.mybatis.ch01;

import ch.qos.logback.classic.turbo.TurboFilter;
import com.enjoy.mybatis.ch01.entity.EmailAndSexBean;
import com.enjoy.mybatis.ch01.entity.TAccount;
import com.enjoy.mybatis.ch01.entity.TUser;
import com.enjoy.mybatis.ch01.mapper.TAccountMapper;
import com.enjoy.mybatis.ch01.mapper.TUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.*;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/07/19 11:07<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: MyBatis测试demo<br/>
 * <b>Description</b>:
 */
public class MyBatisDemo {

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
    public void textAutoMapping() throws Exception {
        // 2、获取sqlSession，SqlSession属于方法级别的对象，线程不安全，不可以共享
        SqlSession session = factory.openSession();
        // 3、获取对应的mapper
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        // 4、执行查询并返回结果
//        TUser user = mapper.selectByPrimaryKey(1);
        TUser user = mapper.selectByPrimaryKey1(1);
        System.out.println(user);
    }

    /**
     * 多参数传入查询
     */
    @Test
    public void testManyQueryParameters() {
        SqlSession sqlSession = factory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

        String email = "qq.com";
        Byte sex = 1;

        // 第一种方式：使用 map 传值
        Map<String, Object> params = new HashMap<>();
        params.put("email", email);
        params.put("sex", sex);
        List<TUser> list1 = mapper.selectByEmailAndSex1(params);
        System.out.println(list1.size());

        // 第二种方式：多参数传入，使用 @Param 注解，但参数个数大于 5 个时，不建议使用
        List<TUser> list2 = mapper.selectByEmailAndSex2(email, sex);
        System.out.println(list2.size());

        // 第三种方式：使用自定义对象进行多值传入，一般参数 5 个以上时，建议使用此方法
        EmailAndSexBean easb = new EmailAndSexBean();
        easb.setEmail(email);
        easb.setSex(sex);
        List<TUser> list3 = mapper.selectByEmailAndSex3(easb);
        System.out.println(list3.size());
    }

    @Test
    public void testSelectIfOper() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);
        String email = "qq.com";
        Byte sex = 1;

        //List<TUser> list = mapper.selectByEmailAndSex2(email, sex);
        List<TUser> list = mapper.selectChooseOper(null, sex);
        System.out.println(list.size());
    }

    @Test
    public void testInsertGenerateId1() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        TUser user = new TUser();
        user.setUserName("张三");
        user.setRealName("张四五");
        user.setEmail("zhangsan");
        mapper.insert1(user);
        session.commit();

        System.out.println(user.getId());
    }

    @Test
    public void testInsertBatch() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        List<TUser> users = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            TUser user = new TUser();
            user.setUserName("haha_" + i);
            user.setRealName("张哈哈" + i);
            user.setSex((byte) (i % 2));
            user.setEmail("haha_" + i + "@qq.com");
            //user.setPositionId(i + 100);
            users.add(user);
        }
        int ret = mapper.insertForeach4Batch(users);
        session.commit();
        System.out.println("insert batch: " + ret + " rows.");
    }

    @Test
    public void testInsertBatchExecutor() {
        SqlSession session = factory.openSession(ExecutorType.BATCH, true);
        TUserMapper mapper=session.getMapper(TUserMapper.class);

        TUser user=new TUser();
        user.setUserName("markk");
        user.setRealName("猫猫");
        user.setEmail("xx@qq.com");
        user.setMobile("13999999999");
    }

    @Test
    public void testDelete() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        int deleted = mapper.deleteByPrimaryKey(113);
        session.commit();
        System.out.println("deleted row: " + deleted);
    }

    @Test
    public void testSymbol() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        String inCol = "id, user_name, real_name, sex, mobile, email, note";
        String tableName = "t_user";
        Byte sex = 1;
        String orderStr = "sex, user_name";
        List<TUser> list = mapper.selectBySymbol(tableName, inCol, orderStr, sex);
        System.out.println(list.size());
    }

    @Test
    public void testUpdateIfAndSetOper() {
        SqlSession session = factory.openSession();
        TUserMapper mapper = session.getMapper(TUserMapper.class);

        TUser user = new TUser();
        user.setId(114);
        user.setUserName("zhangsan");
        //user.setRealName("朱六七");
        user.setSex((byte) 0);
        //user.setEmail("auliuqi");
        int ret = mapper.updateIfAndSetOper(user);

        session.commit();

        System.out.println("updated rows: " + ret);
    }
}
