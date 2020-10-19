package com.bdilab.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
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
    @NotNull(groups = Update.class)
    private long id;//主键id

    @NotNull(message = "用户名不能为空")
    @Size(min = 4,max = 20)
    private String name;//用户名

    @NotNull(message = "密码不能为空")
    private String passwd;// 密码

    @Email
    private String email;

    private String phone;

    private Date createTime;

    private Date updateTime;

    @ManyToMany()
    @JoinTable(name = "users_roles",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},inverseJoinColumns = {@JoinColumn(name = "role_id",referencedColumnName = "id")})
    private Set<Role> roles;//角色列表

    @Column(name = "dept_id")
    private long deptId;//部门id

    public @interface Update {}
}
