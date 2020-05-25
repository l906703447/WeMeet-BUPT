package com.wemeet.wemeet.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * 捉虫用户和被捉虫子的关系表 的组合主键
 *      我们必须用@Embeddable标记它
 *      它必须实现java.io.Serializable
 *      我们需要提供hashcode（）和equals（）方法的实现
 *      这些字段本身都不可以是实体
 * @author xieziwei99
 * 2020-02-21
 */
@Embeddable
@Data
@Accessors(chain = true)
public class CatcherBugKey implements Serializable {

    private Long userId;

    private Long bugId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatcherBugKey key = (CatcherBugKey) o;

        if (!Objects.equals(userId, key.userId)) {
            return false;
        }
        return Objects.equals(bugId, key.bugId);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (bugId != null ? bugId.hashCode() : 0);
        return result;
    }
}
