package com.zf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Curing {

    private Integer id;
    private String imagepath;
    private String status;
    private String expected;
    private String actual;
    private String name;
    private String fertilizer_num;
    private String pesticides_num;
    private String seedling_num;
    private String schedule;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date;
    private String remarks;
    private Integer fertilizer_id;
    private Integer pesticides_id;
    private Integer seedling_id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFertilizer_id() {
        return fertilizer_id;
    }

    public void setFertilizer_id(Integer fertilizer_id) {
        this.fertilizer_id = fertilizer_id;
    }

    public Integer getPesticides_id() {
        return pesticides_id;
    }

    public void setPesticides_id(Integer pesticides_id) {
        this.pesticides_id = pesticides_id;
    }

    public Integer getSeedling_id() {
        return seedling_id;
    }

    public void setSeedling_id(Integer seedling_id) {
        this.seedling_id = seedling_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getFertilizer_num() {
        return fertilizer_num;
    }

    public void setFertilizer_num(String fertilizer_num) {
        this.fertilizer_num = fertilizer_num;
    }

    public String getPesticides_num() {
        return pesticides_num;
    }

    public void setPesticides_num(String pesticides_num) {
        this.pesticides_num = pesticides_num;
    }

    public String getSeedling_num() {
        return seedling_num;
    }

    public void setSeedling_num(String seedling_num) {
        this.seedling_num = seedling_num;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

}
