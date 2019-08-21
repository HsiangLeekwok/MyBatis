package com.enjoy.mybatis.ch03.entity;

import java.math.BigDecimal;

public class THealthReportFemale extends HealthReport {

    private String item;

    private BigDecimal score;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "THealthReportFemale{" +
                "item='" + item + '\'' +
                ", score=" + score +
                '}' + super.toString();
    }
}