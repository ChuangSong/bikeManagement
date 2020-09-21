package com.bdilab.bikemanagement.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 车主信息实体类
 */
@Entity
@Table(name = "bike_owner")
@Data
public class BikeOwner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 64,nullable = false)
    private Long id;//主键id

    @Column(length = 64,nullable = false)
    private String name;//车主姓名

    @Column(length = 10,nullable = false)
    private String sex;//性别

    @Column(length = 64)
    private String phone;//电话

    private String address;//居住地

    @Column(name = "id_card",nullable = false)
    private String ID_card;//身份证

    @Column(length = 64)
    private String company;//工作单位
}
