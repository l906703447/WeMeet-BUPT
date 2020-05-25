package com.wemeet.wemeet.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wemeet.wemeet.entity.BugProperty;
import com.wemeet.wemeet.entity.CatcherBugRecord;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * 代表用户的类
 * @author xieziwei99
 * 2019-11-27
 */
@Entity
@Table
@Data
@Accessors(chain = true)
public class User {

    // 默认是auto，会导致生成hibernate_sequence表，改为IDENTITY，表示使用数据库的id自增策略 但oracle不支持此种方式
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String password;

    @Column(unique = true)
    private String phoneNumber;

    /**
     * 用于注册和登录
     */
    @NotNull
    @Email
    @Column(unique = true)
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

//    /**
//     * 关系维护端
//     * 保存此用户捕捉过的虫子
//     * 可空
//     */
//    @ToString.Exclude   // 避免循环引用toString()导致爆栈
//    @ManyToMany
//    private List<BugProperty> caughtBugs;

    @OneToMany(mappedBy = "catcher")
    @ToString.Exclude
    @JsonIgnore
    private Set<CatcherBugRecord> catchRecords;

    // 当id相等时，两个用户相等
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    // 一对多关系，如果指明，则一端必须用mappedBy指明多端（owner端）的对应属性名
    // 当删除用户时，其种植的虫子都被删除
    @OneToMany(mappedBy = "planter", cascade = CascadeType.REMOVE)
    @JsonIgnore
    @ToString.Exclude
    private List<BugProperty> plantBugs;

    /**
     * 0-普通大众，1-医生
     */
    private int role;
}
