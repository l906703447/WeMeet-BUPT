package com.wemeet.wemeet.util;

import com.wemeet.wemeet.entity.*;
import com.wemeet.wemeet.pojo.Bug;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xieziwei99
 * 2020-04-28
 */
public class ConvertUtil {

    public static List<Bug> convertBugPropertyToBug(List<BugProperty> bugPropertyList) {
        List<Bug> bugList = new ArrayList<>();
        for (BugProperty bugProperty : bugPropertyList) {
            Bug bug = new Bug();
            bug.setBugProperty(bugProperty);
            switch (bugProperty.getBugContent().getType()) {
                case 0:
                    bug.setMoment((Moment) bugProperty.getBugContent());
                    break;
                case 1:
                    bug.setChoiceQuestion((ChoiceQuestion) bugProperty.getBugContent());
                    break;
                case 2:
                    bug.setNarrativeQuestion((NarrativeQuestion) bugProperty.getBugContent());
                    break;
                case 3:
                    break;
                case 4:
                    bug.setVirusPoint((VirusPoint) bugProperty.getBugContent());
                    break;
            }
            bugList.add(bug);
        }
        return bugList;
    }
}
