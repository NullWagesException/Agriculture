package com.zf.pojo;

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
}
