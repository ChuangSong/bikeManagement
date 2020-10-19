package com.bdilab.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //操作人
    private String operator;

    //操作方法
    private String method;

    //方法参数值
    @Column(name = "args",columnDefinition = "text")
    private String args;

    //日志类型
    @Column(name = "log_type")
    private String logType;

    //请求ip
    private String requestIp;

    //浏览器
    private String browser;

    //请求耗时
    private Long time;

    //异常详情
    @Column(name = "exception_detail",columnDefinition = "text")
    private String exceptionDetail;

    //描述
    private String description;

    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp creatTime;

}
