package com.enjoy.mybatis.ch03.entity;

public class THealthReportMale extends HealthReport {

    private String checkProject;

    private String detail;

    public String getCheckProject() {
        return checkProject;
    }

    public void setCheckProject(String checkProject) {
        this.checkProject = checkProject == null ? null : checkProject.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    @Override
    public String toString() {
        return "THealthReportMale{" +
                "checkProject='" + checkProject + '\'' +
                ", detail='" + detail + '\'' +
                '}' + super.toString();
    }
}