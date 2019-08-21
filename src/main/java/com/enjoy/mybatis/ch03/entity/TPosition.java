package com.enjoy.mybatis.ch03.entity;

public class TPosition {
    private Integer id;

    private String postName;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    @Override
    public String toString() {
        return "TPosition{" +
                "id=" + id +
                ", postName='" + postName + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}