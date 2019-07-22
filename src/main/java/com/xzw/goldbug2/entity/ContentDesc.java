package com.xzw.goldbug2.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author xieziwei99
 * 2019-07-13
 * 基本数据结构，包含文字、图片、视频
 */
@Embeddable
@Accessors(chain = true)
@Getter
@Setter
public class ContentDesc {

    private String textContent;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "BLOB")
    private byte[] pictureContent;

    private String videoContent;    // 存储视频文件所在路径
}
