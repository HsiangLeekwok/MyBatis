package com.enjoy.mybatis.ch03.entity;

public class TUserRoleKey {
    private Integer roleId;

    private Integer userId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TUserRoleKey{" +
                "roleId=" + roleId +
                ", userId=" + userId +
                '}';
    }
}