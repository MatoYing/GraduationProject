package com.zust.ysc.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author 闫思潮
 * @Date 14/04/2023 5:55 am
 */

@Data
public class Alert {
    private int id;
    private String name;
    private String responsible_name;
    private Date start_data;
    private Date end_date;
    private String status;
    private String executor;
    private String severity;
    private String monitor_name;
    private String note;
    private String alert;
    private String address;
    private String submit_date;
    private String submit_note;
}
