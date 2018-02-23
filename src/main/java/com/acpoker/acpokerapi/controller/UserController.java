package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.User;
import com.acpoker.acpokerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{uid}")
    public User findByUid(@PathVariable(value = "uid") String uid){
        return userService.findByUid(uid);
    }

    @PostMapping("/add")
    public void createUser() {
        userService.createUser();
    }
}
