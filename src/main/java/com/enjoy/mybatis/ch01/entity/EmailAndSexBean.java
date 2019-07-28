package com.enjoy.mybatis.ch01.entity;

/**
 * <b>Author</b>: Hsiang Leekwok<br/>
 * <b>Date</b>: 2019/07/26 13:24<br/>
 * <b>Version</b>: v1.0<br/>
 * <b>Subject</b>: 多参数查询传入类型<br/>
 * <b>Description</b>:
 */
public class EmailAndSexBean {
    private String email;
    private Byte sex;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }
}
