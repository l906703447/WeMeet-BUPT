package com.xzw.goldbug2.web;

import com.xzw.goldbug2.entity.BugProperty;
import com.xzw.goldbug2.entity.ChoiceQuestion;
import com.xzw.goldbug2.entity.Moment;
import com.xzw.goldbug2.entity.NarrativeQuestion;
import com.xzw.goldbug2.repository.BugContentRepo;
import com.xzw.goldbug2.repository.BugPropertyRepo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xieziwei99
 * 2019-07-17
 */
@RestController
public class GoldbugController {

    private final BugPropertyRepo bugPropertyRepo;
    private final BugContentRepo bugContentRepo;

    public GoldbugController(BugPropertyRepo bugPropertyRepo, BugContentRepo bugContentRepo) {
        this.bugPropertyRepo = bugPropertyRepo;
        this.bugContentRepo = bugContentRepo;
    }

    @ApiOperation(value = "根据用户当前位置获取种子列表", notes = "范围是以用户坐标为中心5000 × 5000的正方形")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userLon", value = "用户经度", required = true, dataType = "double"),
            @ApiImplicitParam(name = "userLat", value = "用户纬度", required = true, dataType = "double"),
    })
    @RequestMapping(value = "/getAroundBugs", method = RequestMethod.GET)
    public List<BugProperty> getAroundBugs(@RequestParam double userLon, @RequestParam double userLat) {
        return bugPropertyRepo.findByStartLongitudeBetweenAndStartLatitudeBetween
                (userLon - 2500, userLon + 2500,
                        userLat - 2500, userLat + 2500);
    }

    /**
     * 增加一条虫子记录
     * 不能增加BugContent
     * 只能依照已存在的BugContent加入，而且不能通过外键指定，要用BugContent的全部来指定
     * @param bugProperty 虫子的基本属性
     * @return 成功返回success
     */
    @ApiOperation(value = "增加一条虫子记录", notes = "不能增加BugContent，只能依照已存在的BugContent加入，而且不能通过外键指定，要用BugContent的全部来指定")
    @ApiImplicitParam(name = "bugProperty", value = "虫子详细实体BugProperty", required = true, dataType = "BugProperty")
    @RequestMapping(value = "/addGoldBug", method = RequestMethod.POST)
    public String addGoldBug(@RequestBody BugProperty bugProperty) {
        bugPropertyRepo.save(bugProperty);
        return "success";
    }

    @ApiOperation(value = "增加一条虫子内容 - 动态")
    @ApiImplicitParam(name = "bugContent", value = "虫子内容详细实体Moment", required = true, dataType = "Moment")
    @RequestMapping(value = "/addMoment", method = RequestMethod.POST)
    public String addMoment(@RequestBody Moment bugContent) {
        bugContentRepo.save(bugContent);
        return "success";
    }

    @ApiOperation(value = "增加一条虫子内容 - 选择题")
    @ApiImplicitParam(name = "bugContent", value = "虫子内容详细实体ChoiceQuestion", required = true, dataType = "ChoiceQuestion")
    @RequestMapping(value = "/addChoiceQuestion", method = RequestMethod.POST)
    public String addChoiceQuestion(@RequestBody ChoiceQuestion bugContent) {
        bugContentRepo.save(bugContent);
        return "success";
    }

    @ApiOperation(value = "增加一条虫子内容 - 叙述题")
    @ApiImplicitParam(name = "bugContent", value = "虫子内容详细实体NarrativeQuestion", required = true, dataType = "NarrativeQuestion")
    @RequestMapping(value = "/addNarrativeQuestion", method = RequestMethod.POST)
    public String addNarrativeQuestion(@RequestBody NarrativeQuestion bugContent) {
        bugContentRepo.save(bugContent);
        return "success";
    }
}
