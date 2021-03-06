package com.wemeet.wemeet.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author xieziwei99
 * 2019-07-13
 * 选择题，包含判断题
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
@DiscriminatorValue("ChoiceQuestion")   // 可省略
public class ChoiceQuestion extends BugContent{

    @Embedded
    private ContentDesc question;

    @NotNull
    private String choiceA;
    @NotNull
    private String choiceB;

    private String choiceC;
    private String choiceD;

    @NotNull
    private String correctAnswer;

    private Integer score;

}
