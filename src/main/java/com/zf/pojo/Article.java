package com.zf.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
//文章
public class Article {

    private Integer id;
    private String title;
    private String text;
    private Date time;
    private String imagepath;

    @Override
    public String toString() {
        return "IArticleService{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", imagepath='" + imagepath + '\'' +
                '}';
    }
}
