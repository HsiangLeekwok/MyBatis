package com.enjoy.mybatis.ch01;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/07/19 09:26<br/>
 * <b>Version</b>: 1.0<br/>
 * <b>Subject</b>: <br/>
 * <b>Description</b>:
 */
public class JdbcDemo {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.22.130:3306/demo?useUnicode=true&" +
            "characterEncoding=utf8&allowMultiQueries=true";

    static final String USER = "root";
    static final String PASS = "root1234%";

    @Test
    public void queryStatementDemo() {
        Connection connection = null;
        Statement stmt = null;
        List<TAccount> accounts = new ArrayList<>();
        try {
            // step1、注册 mysql 的驱动
            Class.forName(JDBC_DRIVER);

            // step2、获得一个链接
            System.out.println("connecting to database......");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // step3、创建一个查询
            System.out.println("creating statement.....");
            stmt = connection.createStatement();
            String name = "lucy";
            String sql = "select * from account where name = '" + name + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // step4、从resultset结果集中获取数据并转化成bean
            while (rs.next()) {
                System.out.println("-------------------------");
                // 读取数据并且将每一列转换到对象中
                TAccount account = new TAccount();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                ;
                account.setBalance(rs.getInt("balance"));

                System.out.println(account.toString());
                accounts.add(account);
            }

            // step5、关闭连接
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != stmt) {
                    stmt.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void queryPreparedStatementDemo() {
        Connection connection = null;
        PreparedStatement stmt = null;
        List<TAccount> accounts = new ArrayList<>();
        try {
            // step1、注册 mysql 的驱动
            Class.forName(JDBC_DRIVER);

            // step2、获得一个链接
            System.out.println("connecting to database......");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            // step3、创建一个查询
            System.out.println("creating statement.....");
            String name = "lucy";
            String sql = "select * from account where name = ? ";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, name);
            System.out.println(stmt.toString());
            ResultSet rs = stmt.executeQuery(sql);

            // step4、从resultset结果集中获取数据并转化成bean
            while (rs.next()) {
                System.out.println("-------------------------");
                // 读取数据并且将每一列转换到对象中
                TAccount account = new TAccount();
                account.setId(rs.getInt("id"));
                account.setName(rs.getString("name"));
                ;
                account.setBalance(rs.getInt("balance"));

                System.out.println(account.toString());
                accounts.add(account);
            }

            // step5、关闭连接
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != stmt) {
                    stmt.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void updateDemo() {
        Connection connection = null;
        PreparedStatement stmt = null;
        try {
            // 注册mysql驱动
            Class.forName(JDBC_DRIVER);
            // 获取一个连接
            System.out.println("connection to database......");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            // 启动手动提交
            connection.setAutoCommit(false);

            System.out.println("creating statement.......");
            String sql = "update account set balance= ? where name = ? ";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, 1000);
            stmt.setString(2, "lilei");
            System.out.println("updating: " + stmt.toString());
            int ret = stmt.executeUpdate();
            System.out.println("updated " + ret + " records.");

            // commit
            connection.commit();

            // 关闭
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (null != stmt) {
                    stmt.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
