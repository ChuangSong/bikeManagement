package com.bdilab.service;

import com.bdilab.entity.User;

import java.util.Set;

public interface UserService {

    User addUser(User user);

    User updateUser(User user);

    void delUsers(Set<Long> ids);
}
