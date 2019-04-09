package com.zf.pojo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
//用户
public class User {

    private int id;
    private String username;
    private String password;
    private String phone;
    private int type;
    private String position;
    private String openid;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", type=" + type +
                ", position='" + position + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
