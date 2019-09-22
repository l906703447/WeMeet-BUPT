package com.wemeet.wemeet.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author xieziwei99
 * 2019-07-13
 * 虫子所载内容
 */
@Entity
@Inheritance
@Data
@Accessors(chain = true)
public class BugContent {

    @Id
    @GeneratedValue
    private Long bugContentId;

    /**
     * 0代表微信朋友圈的动态，1代表选择题，2代表叙述题，3代表游戏
     */
    @NotNull
    private Integer type;

    @NotNull
    private Timestamp publishTime;

}
