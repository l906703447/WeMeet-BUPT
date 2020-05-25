package com.wemeet.wemeet.entity;

import com.wemeet.wemeet.entity.user.User;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Objects;

/**
 * 捉虫用户和被捉虫子的关系表
 * @author xieziwei99
 * 2020-02-21
 */
@Entity
@Data
@Accessors(chain = true)
public class CatcherBugRecord {

    @EmbeddedId
    private CatcherBugKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "catcherId")     // 决定数据库中的字段名
    private User catcher;

    @ManyToOne
    @MapsId("bugId")
    @JoinColumn(name = "bugId")         // 决定数据库中的字段名
    private BugProperty caughtBug;

    /**
     * 用户捕捉此虫子时，所做的回答
     */
    private String userAnswer;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CatcherBugRecord record = (CatcherBugRecord) o;

        return Objects.equals(id, record.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
