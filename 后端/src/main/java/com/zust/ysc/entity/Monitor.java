package com.zust.ysc.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 14/04/2023 5:55 am
 */

@Data
public class Monitor {
   private String name;
   private String server_address;
   private String file_address;
   private String people;
   private Date date;
   private String status;
   private int rules;
   private int warnings;
   private int critical;
   private int emergency;
   private String note;
   private String grafana;
   private String type;
}
