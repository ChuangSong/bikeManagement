package com.bdilab.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 车辆元数据实体类
 */
@Data
@Entity
@Table(name = "bike_meta")
public class BikeMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(length = 64,nullable = false)
    private Long id;//主键id

    private String name;//车辆名称

    @Column(length = 32,nullable = false)
    private String type;//车辆类型

    @Column(length = 64)
    private String brand;//品牌

    @Column(length = 64)
    private String number;//型号

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private BikeOwner owners;//车主id

    private String description;//描述信息
}
