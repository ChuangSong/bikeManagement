package com.bdilab.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * 平台角色实体类
 */
@Entity
@Table(name = "role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String level;

    private String permissions;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany(mappedBy = "roles")
    private Set<Dept> depts;
}
