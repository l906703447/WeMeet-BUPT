package com.wemeet.wemeet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wemeet.wemeet.entity.user.User;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @author xieziwei99
 * 2019-07-12
 * 虫子的基本属性
 */
@Entity
@Table(name = "bug_property")
@Data
@Accessors(chain = true)
public class BugProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bugID;

    // 多对一关系，维护端在多端
    // 当删除bug时，不应删除其种植者，所以未设置cascade属性
    // 未设置optional，默认为true，即表示planter可以为空
    @ManyToOne
    private User planter;

    @NotNull
    private Double startLongitude;

    @NotNull
    private Double startLatitude;

    @NotNull
    private boolean movable;

    private Double destLongitude;

    private Double destLatitude;

    /**
     * int代表小时
     */
    @NotNull
    private Integer survivalTime;

    @NotNull
    private Timestamp startTime;

    @NotNull
    private Integer lifeCount;

    private Integer restLifeCount;

    @ElementCollection
    private List<String> tags;

    private Integer temperature;

    @OneToOne
    @JoinColumn(name = "content_id")
    private BugContent bugContent;

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;

//    /**
//     * 关系被维护端
//     * 保存捕捉过此虫子的用户列表
//     * 可空
//     */
//    @JsonIgnore     // 避免循环引用导致爆栈
//    @ToString.Exclude   // 避免循环引用toString()导致爆栈
//    @ManyToMany(mappedBy = "caughtBugs")
//    private List<User> catchers;

    @OneToMany(mappedBy = "caughtBug")
    @ToString.Exclude
    @JsonIgnore
    private Set<CatcherBugRecord> caughtRecords;

    // 当bugID相等时，两个bug相等
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BugProperty that = (BugProperty) o;

        return bugID.equals(that.bugID);
    }

    @Override
    public int hashCode() {
        return bugID.hashCode();
    }

    private String address;
}
