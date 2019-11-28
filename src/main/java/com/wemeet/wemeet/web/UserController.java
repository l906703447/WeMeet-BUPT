package com.wemeet.wemeet.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xieziwei99
 * 2019-11-28
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {

//    @PostMapping("/register")
//    public String register(@RequestBody User user) {
//        user.setGrade(0);
//    }
}
