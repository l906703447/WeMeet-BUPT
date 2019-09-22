package com.wemeet.wemeet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * @author xieziwei99
 * 2019-07-13
 * 叙述题
 */
@Entity
@DiscriminatorValue("NarrativeQuestion")
@Getter
@Setter
@Accessors(chain = true)
public class NarrativeQuestion extends BugContent{

    @Embedded
    private ContentDesc question;

    private String userAnswer;

    @NotNull
    private String correctAnswer;

    private Integer score;
}
