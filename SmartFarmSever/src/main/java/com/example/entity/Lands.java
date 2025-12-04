package com.example.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Lands {
    private String uid;
    private String channel_uid;
    private String title;
    private String intro;
    private String img;
    private float price;
    private String location;
    private int stock;
    private String norms;
    private int recommendhome;
    private String details;
    private String remark;
    private int state;
    private Date createdtime;
    private Date updatedtime;
}
