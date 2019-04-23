package com.zf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UpdateUser {

    private Integer id;
    private Integer updateid;
    private Integer allow;
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
    private String username;
    private String longitude;
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUpdateid() {
        return updateid;
    }

    public void setUpdateid(Integer updateid) {
        this.updateid = updateid;
    }

    public Integer getAllow() {
        return allow;
    }

    public void setAllow(Integer allow) {
        this.allow = allow;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getFertilizer_id() {
        return fertilizer_id;
    }

    @Override
    public String toString() {
        return "UpdateUser{" +
                "id=" + id +
                ", updateid=" + updateid +
                ", allow=" + allow +
                ", imagepath='" + imagepath + '\'' +
                ", status='" + status + '\'' +
                ", expected='" + expected + '\'' +
                ", actual='" + actual + '\'' +
                ", name='" + name + '\'' +
                ", fertilizer_num='" + fertilizer_num + '\'' +
                ", pesticides_num='" + pesticides_num + '\'' +
                ", seedling_num='" + seedling_num + '\'' +
                ", schedule='" + schedule + '\'' +
                ", date=" + date +
                ", remarks='" + remarks + '\'' +
                ", fertilizer_id=" + fertilizer_id +
                ", pesticides_id=" + pesticides_id +
                ", seedling_id=" + seedling_id +
                '}';
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
}
