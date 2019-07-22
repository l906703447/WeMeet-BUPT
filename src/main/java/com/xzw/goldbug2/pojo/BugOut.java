package com.xzw.goldbug2.pojo;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author xieziwei99
 * 2019-07-17
 * 从数据库中select时的虫子基本信息（不含内容）
 */
@Getter
@Setter
public class BugOut {

    private Double startLongitude;

    private Double startLatitude;

    private boolean movable;

    private Double destLongitude;

    private Double destLatitude;

    private Integer survivalTime;

    private Timestamp startTime;

    private Integer lifeCount;

    private Integer restLifeCount;

    private List<String> tags;

    private Integer temperature;

    /**
     * 指虫子所载内容类型
     */
    private Integer type;

    private Timestamp publishTime;
}
