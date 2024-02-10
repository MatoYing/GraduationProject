package com.zust.ysc.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 14/04/2023 5:56 am
 */

@Data
public class Rule {
    private String name;
    private String add_people;
    private String responsible_person;
    private Date date;
    private String status;
    private String severity;
    private int number;
    private String note;
    private String rule;
    private String monitor_name;
}
