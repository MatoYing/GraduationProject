package com.zust.ysc.entity;

import lombok.Data;

/**
 * @Description
 * @Author Github: MatoYing
 * @Date 27/02/2023 2:05 pm
 */

@Data
public class User {
    private String ID;
    private String phone;
    private String password;
    private String QQ;
    private String DingDing;
    private String name;
    private String role;
    private String email;
    private String birthday;
    private String sex;
    private String permission;
}
