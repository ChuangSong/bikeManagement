package com.bdilab.service.impl;

import com.bdilab.entity.User;
import com.bdilab.repository.UserRepository;
import com.bdilab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delUsers(Set<Long> ids) {
        for (Long id : ids) {
            userRepository.deleteById(id);
        }
    }
}
