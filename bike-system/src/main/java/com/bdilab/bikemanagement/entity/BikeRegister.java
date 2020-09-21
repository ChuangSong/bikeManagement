package com.bdilab.bikemanagement.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 车辆登记信息
 */
@Entity
@Table(name = "bike_register")
public class BikeRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_id",length = 64)
    private String cardId;//电子牌照

    @OneToOne
    @JoinColumn(name = "owner_id")
    private BikeOwner owner;//车主id

    @OneToOne
    @JoinColumn(name = "meta_id")
    private BikeMeta meta;//元数据id

    @Column(name = "register_time")
    private Date registerTime;//登记时间

    private String handler;//经办人

    private String handleCompany;//经办单位

}
