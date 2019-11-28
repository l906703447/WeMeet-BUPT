package com.wemeet.wemeet.entity.user;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 代表用户的类
 * @author xieziwei99
 * 2019-11-27
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"phoneNumber", "email"}))
@Data
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    private String phoneNumber;

    /**
     * 用于注册和登录
     */
    @NotNull
    private String email;

    /**
     * 代表积分，或者货币
     */
    @NotNull
    private Double score;

    /**
     * 代表用户等级
     */
    private Integer grade;
}
