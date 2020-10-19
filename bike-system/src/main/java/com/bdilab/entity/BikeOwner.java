package com.bdilab.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "姓名不能为空")
    private String name;//车主姓名

    @Column(length = 10,nullable = false)
    @NotBlank(message = "性别不能为空")
    private String sex;//性别

    @Column(length = 64)
    private String phone;//电话

    private String address;//居住地

    @Column(name = "id_card",nullable = false)
    @NotNull
    private String ID_card;//身份证

    @Column(length = 64)
    private String company;//工作单位
}
