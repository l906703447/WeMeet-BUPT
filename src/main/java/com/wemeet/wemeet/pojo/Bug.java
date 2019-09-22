package com.wemeet.wemeet.pojo;

import com.wemeet.wemeet.entity.NarrativeQuestion;
import com.wemeet.wemeet.entity.BugProperty;
import com.wemeet.wemeet.entity.ChoiceQuestion;
import com.wemeet.wemeet.entity.Moment;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xieziwei99
 * 2019-09-14
 * 接收前端传来的数据（存入一个bug的完整信息，包括BugProperty和BugContent）
 */
@Getter
@Setter
public class Bug {

    private BugProperty bugProperty;
    private Moment moment;
    private ChoiceQuestion choiceQuestion;
    private NarrativeQuestion narrativeQuestion;
}
