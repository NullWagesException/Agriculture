package com.zf.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//图片
public class Images {

    private Integer id;
    private String imagepath;

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                ", imagepath='" + imagepath + '\'' +
                '}';
    }
}
