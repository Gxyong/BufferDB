package com.buffer.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author:
 * 时间:
 * 版本:
 * 描述: dec
 * 修改说明:
 */
public class DemoBean implements Serializable {
    private Long id;
    private String name;
    private Date time;
    private Boolean valid;
    private Integer grade;
    private Float credit;
    private BigDecimal bigDecimal;



    @Override
    public String toString() {
        return "DemoBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", valid=" + valid +
                ", grade=" + grade +
                ", credit=" + credit +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Float getCredit() {
        return credit;
    }

    public void setCredit(Float credit) {
        this.credit = credit;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }
}
