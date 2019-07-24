package com.xzw.goldbug2.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author xieziwei99
 * 2019-07-15
 * 留言版 - 只支持一个留言？
 */
@Entity
@Data
@Accessors(chain = true)
public class Message {

    @Id
    @GeneratedValue
    private Long messageID;

    private Long userID;    // TODO: 2019/7/15 应该用外键描述

    @Embedded
    private ContentDesc contentDesc;
}
