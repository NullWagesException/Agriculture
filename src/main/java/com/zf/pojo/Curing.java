package com.zf.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Curing {

    private Integer id;
    private String imagepath;
    private String status;
    private String expected;
    private String actual;
    private String fertilizer_num;
    private String pesticides_num;
    private String seedling_num;
    private String schedule;
    private Date date;
    private String remarks;

}
