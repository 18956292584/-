package com.springboot.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = -5514139686858156155L;
    private String user_id;
    private String user_password;
    public String user_name;
    private String user_email;
    private String user_host;
    private String user_phone;
}
