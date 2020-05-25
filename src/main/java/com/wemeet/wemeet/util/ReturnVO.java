package com.wemeet.wemeet.util;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author xieziwei99
 * 2020-02-08
 */
@Data
@Accessors(chain = true)
public class ReturnVO {
    /**
     *用200表示成功，用500表示失败
     */
    private Integer code;

    private String message;
}
