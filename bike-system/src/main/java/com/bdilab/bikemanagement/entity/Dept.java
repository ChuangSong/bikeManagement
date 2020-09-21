package com.bdilab.bikemanagement.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 部门实体类
 */
@Entity
@Table(name = "dept")
public class Dept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;//部门名称

    private int level;//级别

    private Long pid;//上级部门id

    @ManyToMany
    @JoinTable(name = "dept_role",joinColumns = {@JoinColumn(name = "dept_id",referencedColumnName = "id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;//持有角色

    @Column(name = "create_time")
    @CreationTimestamp
    private Timestamp createTime;
}
