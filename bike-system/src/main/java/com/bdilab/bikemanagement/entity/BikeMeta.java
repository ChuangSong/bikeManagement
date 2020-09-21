package com.bdilab.bikemanagement.entity;

import lombok.Data;

import javax.persistence.*;

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

    @Column(length = 32,nullable = false)
    private String type;//车辆类型

    @Column(length = 64)
    private String brand;//品牌

    @Column(length = 64)
    private String model;//型号

    @OneToOne
    @JoinColumn(name = "owner_id")
    private BikeOwner owner;//车主id

    private String description;//描述信息
}
