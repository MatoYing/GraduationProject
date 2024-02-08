package com.zust.ysc.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author 闫思潮
 * @Date 01/05/2023 1:55 pm
 */

@Data
public class Inspect {
    private String title;
    private String description;
    private String people;
    private String start;
    private String author;
    private String start_address;
    private Date end_date;
    private String end_address;
    private String problem;
    private String location;
}
