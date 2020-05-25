package com.wemeet.wemeet.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import java.sql.Timestamp;

/**
 * 疫情点
 * @author xieziwei99
 * 2020-03-03
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Accessors(chain = true)
public class VirusPoint extends BugContent {
    private String description;

    /**
     * 开始出现症状的时间，发病时间
     */
    private Timestamp diseaseStartTime;

    /**
     * 症状
     */
    private String symptoms;

    /**
     * 是否可传染
     */
    private Boolean infectious;

    /**
     * 传染方式，传播途径
     */
    private String wayOfInfect;

    /**
     * 假定 1-症状虫子 2-疑似虫子 3-确诊虫子
     */
    private Integer status;
}
