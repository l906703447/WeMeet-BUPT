package com.wemeet.wemeet.web;

import com.wemeet.wemeet.entity.user.User;
import com.wemeet.wemeet.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xieziwei99
 * 2019-11-28
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 至少需要提供name，email和password
     * @param user 要注册的用户信息
     * @return 注册成功返回OK，失败返回ERROR：用户已注册
     */
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        if (userRepo.findByEmail(user.getEmail()) != null) {
            user.setGrade(0).setScore(100.0);
            userRepo.save(user);
            return "OK";
        } else {
            return "ERROR: 此用户已注册";
        }
    }
}
