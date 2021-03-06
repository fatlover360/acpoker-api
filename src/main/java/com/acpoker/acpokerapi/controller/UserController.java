package com.acpoker.acpokerapi.controller;

import com.acpoker.acpokerapi.entity.User;
import com.acpoker.acpokerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @CrossOrigin(value = "*")
    public List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping("/{uid}")
    @CrossOrigin(value = "*")
    public User findByUid(@PathVariable(value = "uid") String uid){
        return userService.findByUid(uid);
    }

    @PostMapping("/add")
    @CrossOrigin(value = "*")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }
}
