package com.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Cyclers {
    private String uid;
    private String title;
    private int days;
    private String remark;
    private int state;
    private Date createdtime;
    private Date updatedtime;

}
