package com.xzw.goldbug2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;

/**
 * @author xieziwei99
 * 2019-07-13
 * 朋友圈动态
 */
@Entity
@DiscriminatorValue("Moment")
@Getter
@Setter
@Accessors(chain = true)
public class Moment extends BugContent{

    @Embedded
    private ContentDesc contentDesc;
}
