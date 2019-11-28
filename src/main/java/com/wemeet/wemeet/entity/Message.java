package com.wemeet.wemeet.entity;

import com.wemeet.wemeet.entity.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

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

    @ManyToOne
    private User user;

    @Embedded
    private ContentDesc contentDesc;
}
