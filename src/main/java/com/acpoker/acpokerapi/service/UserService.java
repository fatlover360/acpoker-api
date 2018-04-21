package com.acpoker.acpokerapi.service;

import com.acpoker.acpokerapi.entity.User;
import com.acpoker.acpokerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUid(String uid) {
        return userRepository.findUserByUid(uid);
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
}
