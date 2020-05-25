package com.wemeet.wemeet.web;

import com.wemeet.wemeet.entity.BugProperty;
import com.wemeet.wemeet.entity.user.User;
import com.wemeet.wemeet.pojo.Bug;
import com.wemeet.wemeet.repository.UserRepo;
import com.wemeet.wemeet.util.ConvertUtil;
import com.wemeet.wemeet.util.ReturnVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author xieziwei99
 * 2019-11-28
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
@Validated      // 使得@Valid @RequestParam @Email String email 起作用
public class UserController {

    private final UserRepo userRepo;

    @Autowired
    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 至少需要提供name，email和password
     *
     * @param user 要注册的用户信息
     * @return 注册成功返回OK，失败返回ERROR：用户已注册
     */
    @ApiOperation(value = "用户注册")
    @ApiImplicitParam(name = "user", value = "至少需要提供name，email和password", required = true,
            dataType = "User")
    @PostMapping("/register")
    public ReturnVO register(@RequestBody User user) {
        if (userRepo.findByEmail(user.getEmail()) == null) {
            user.setGrade(0).setScore(100.0);
            userRepo.save(user);
            return new ReturnVO().setCode(200).setMessage("注册成功");
        } else {
            return new ReturnVO().setCode(500).setMessage("此用户已注册");
        }
    }

    /**
     * 至少需要提供 email 和 password 字段  + role字段
     *
     * @param user 请求登陆的用户信息
     * @return OK 或 ERROR
     */
    @ApiOperation(value = "用户登录")
    @ApiImplicitParam(name = "user", value = "需要提供email和password", required = true,
            dataType = "User")
    @PostMapping("/login")
    public ReturnVO login(@RequestBody User user) {
        User userTemp = userRepo.findByEmail(user.getEmail());
        if (userTemp == null) {
            return new ReturnVO().setCode(500).setMessage("用户未注册");
        } else if (!userTemp.getPassword().equals(user.getPassword())) {
            return new ReturnVO().setCode(500).setMessage("密码错误");
        } else if (userTemp.getRole() != user.getRole()) {
            return new ReturnVO().setCode(500).setMessage("用户角色不匹配");
        } else {
            return new ReturnVO().setCode(200).setMessage("登录成功");
        }
    }

    @PutMapping
    public ReturnVO updateUser(@RequestBody User user) {
        userRepo.save(user);
        return new ReturnVO().setCode(200).setMessage("更新成功");
    }

    /**
     * 通过传入的 email 和 score 更新用户
     */
    @PutMapping("/email/{email}/score/{score}")
    public ReturnVO changeScoreOfUser(@Valid @Email @NotNull @PathVariable String email,
                                      @Valid @NotNull @PathVariable double score) {
        User user = userRepo.findByEmail(email);
        user.setScore(user.getScore() + score);
        userRepo.save(user);
        return new ReturnVO().setCode(200).setMessage("更新成功");
    }

    @GetMapping
    public User getUserByEmail(@Valid @RequestParam @Email String email) {
        return userRepo.findByEmail(email);
    }

    @GetMapping("/plantBugs")
    public List<Bug> getPlantBugsByUserEmail(@Valid @RequestParam @Email String email) {
        User user = userRepo.findByEmail(email);
        List<BugProperty> plantBugs = user.getPlantBugs();
        return ConvertUtil.convertBugPropertyToBug(plantBugs);
    }
}
