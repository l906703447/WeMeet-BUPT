package com.wemeet.wemeet.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;

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
    @GeneratedValue
    private Long bugID;

    private Long userID;    // TODO: 2019/7/15 应该用外键描述

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
    private List<String> tags;      // TODO: 2019/7/12 String?

    private Integer temperature;

    @OneToOne
    @JoinColumn(name = "content_id")
    private BugContent bugContent;

    @OneToOne
    @JoinColumn(name = "message_id")
    private Message message;
}
