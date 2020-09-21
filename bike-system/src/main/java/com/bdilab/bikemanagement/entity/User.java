package com.bdilab.bikemanagement.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 平台用户实体类
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;//主键id

    private String userName;//用户名

    private String passwd;// 密码

    @ManyToMany()
    @JoinTable(name = "users_roles",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;//角色列表

    @Column(name = "dept_id")
    private long deptId;//部门id

}
