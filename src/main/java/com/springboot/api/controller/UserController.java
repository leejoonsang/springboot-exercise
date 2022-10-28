package com.springboot.api.controller;

import com.springboot.api.dao.UserDao;
import com.springboot.api.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }


    @PostMapping("/user")
    public String add(@RequestBody User user) {
        userDao.add(user);
        return String.format("%s번 등록", user.getId());
    }

    @DeleteMapping(value = "/user/all")
    public String deleteAll() {
        userDao.deleteAll();
        return "전체 삭제 완료.";
    }
}
