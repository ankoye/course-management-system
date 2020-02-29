package com.anko.coursems.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private String userId;

    private String userName;

    private String module;

    private String operation;

    private String method;

    private String params;

    private Long time;

    private String ip;

    private Date createTime;
}
