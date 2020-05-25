package com.wemeet.wemeet.web;

import com.wemeet.wemeet.entity.*;
import com.wemeet.wemeet.entity.user.User;
import com.wemeet.wemeet.pojo.Bug;
import com.wemeet.wemeet.repository.BugContentRepo;
import com.wemeet.wemeet.repository.BugPropertyRepo;
import com.wemeet.wemeet.repository.CatcherBugRecordRepo;
import com.wemeet.wemeet.repository.UserRepo;
import com.wemeet.wemeet.util.ConvertUtil;
import com.wemeet.wemeet.util.MathUtil;
import com.wemeet.wemeet.util.ReturnVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author xieziwei99
 * 2019-07-17
 */
@RestController
@CrossOrigin(origins = "*")
@Validated
public class GoldbugController {

    private final BugPropertyRepo bugPropertyRepo;
    private final BugContentRepo bugContentRepo;
    private final UserRepo userRepo;
    private final CatcherBugRecordRepo recordRepo;

    public GoldbugController(BugPropertyRepo bugPropertyRepo, BugContentRepo bugContentRepo, UserRepo userRepo, CatcherBugRecordRepo recordRepo) {
        this.bugPropertyRepo = bugPropertyRepo;
        this.bugContentRepo = bugContentRepo;
        this.userRepo = userRepo;
        this.recordRepo = recordRepo;
    }

    @ApiOperation(value = "根据用户当前位置获取种子列表", notes = "范围是以用户坐标为中心5000 × 5000的正方形")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userLon", value = "用户经度", required = true, dataType = "double"),
            @ApiImplicitParam(name = "userLat", value = "用户纬度", required = true, dataType = "double"),
    })
    @RequestMapping(value = "/getAroundBugs", method = RequestMethod.GET)
    public List<Bug> getAroundBugs(@RequestParam double userLon, @RequestParam double userLat, @RequestParam double meter) {
        double longitude = MathUtil.meterToLongitude(meter);
        double latitude = MathUtil.meterToLatitude(meter, userLat);
        List<BugProperty> bugPropertyList = bugPropertyRepo.
                findByStartLongitudeBetweenAndStartLatitudeBetween(
                        userLon - longitude, userLon + longitude,
                        userLat - latitude, userLat + latitude);
        return ConvertUtil.convertBugPropertyToBug(bugPropertyList);
    }

    // just for test
    @GetMapping("/getBug/{id}")
    public Bug getBugById(@PathVariable Long id) throws Exception {
        BugProperty bugProperty = bugPropertyRepo.findById(id).orElseThrow(Exception::new);
        List<BugProperty> bugPropertyList = new ArrayList<BugProperty>(){{
            add(bugProperty);
        }};
        return ConvertUtil.convertBugPropertyToBug(bugPropertyList).get(0);
    }

    /**
     * 增加一条虫子记录
     * 不能增加BugContent
     * 只能依照已存在的BugContent加入，而且不能通过外键指定，要用BugContent的全部来指定
     *
     * @param bugProperty 虫子的基本属性
     * @return 成功返回success
     */
    @ApiOperation(value = "增加一条虫子记录", notes = "不能增加BugContent，只能依照已存在的BugContent加入，而且不能通过外键指定，要用BugContent的全部来指定")
    @ApiImplicitParam(name = "bugProperty", value = "虫子详细实体BugProperty", required = true, dataType = "BugProperty")
    @RequestMapping(value = "/addGoldBug", method = RequestMethod.POST)
    @Deprecated
    public ReturnVO addGoldBug(@RequestBody BugProperty bugProperty) {
        bugPropertyRepo.save(bugProperty);
        return new ReturnVO().setCode(200).setMessage("添加成功");
    }

    @ApiOperation(value = "增加一条虫子内容 - 动态")
    @ApiImplicitParam(name = "bugContent", value = "虫子内容详细实体Moment", required = true, dataType = "Moment")
    @RequestMapping(value = "/addMoment", method = RequestMethod.POST)
    @Deprecated
    public ReturnVO addMoment(@RequestBody Moment bugContent) {
        bugContentRepo.save(bugContent);
        return new ReturnVO().setCode(200).setMessage("添加成功");
    }

    @ApiOperation(value = "增加一条虫子内容 - 选择题")
    @ApiImplicitParam(name = "bugContent", value = "虫子内容详细实体ChoiceQuestion", required = true, dataType = "ChoiceQuestion")
    @RequestMapping(value = "/addChoiceQuestion", method = RequestMethod.POST)
    @Deprecated
    public ReturnVO addChoiceQuestion(@RequestBody ChoiceQuestion bugContent) {
        bugContentRepo.save(bugContent);
        return new ReturnVO().setCode(200).setMessage("添加成功");
    }

    @ApiOperation(value = "增加一条虫子内容 - 叙述题")
    @ApiImplicitParam(name = "bugContent", value = "虫子内容详细实体NarrativeQuestion", required = true, dataType = "NarrativeQuestion")
    @RequestMapping(value = "/addNarrativeQuestion", method = RequestMethod.POST)
    @Deprecated
    public ReturnVO addNarrativeQuestion(@RequestBody NarrativeQuestion bugContent) {
        bugContentRepo.save(bugContent);
        return new ReturnVO().setCode(200).setMessage("添加成功");
    }

    @ApiOperation(value = "增加一个包含内容的虫子")
    @ApiImplicitParam(name = "bug", value = "包含内容的虫子详细实体bug", required = true, dataType = "Bug")
    @PostMapping("/addBug")
    public ReturnVO addBug(@RequestBody Bug bug) {
        BugContent bugContent;
        if (bug.getMoment() != null) {
            bugContent = bug.getMoment();
        } else if (bug.getChoiceQuestion() != null) {
            bugContent = bug.getChoiceQuestion();
        } else if (bug.getNarrativeQuestion() != null) {
            bugContent = bug.getNarrativeQuestion();
        } else if (bug.getVirusPoint() != null) {
            bugContent = bug.getVirusPoint();
        } else {
            bugContent = null;
        }
        assert bugContent != null;
        bugContentRepo.save(bugContent);

        BugProperty bugProperty = bug.getBugProperty();
        bugProperty.setBugContent(bugContent);
        User planter = bug.getPlanter();
        bugProperty.setPlanter(planter);
        bugPropertyRepo.save(bugProperty);

        return new ReturnVO().setCode(200).setMessage("添加成功");
    }

    @RequestMapping(value = "/addUserCatchesBugConstraint", method = RequestMethod.GET)
    @Transactional
    public ReturnVO addUserCatchesBugConstraint(
            @Valid @NotNull @RequestParam Long bugId,
            @Valid @NotNull @RequestParam String email,
            @Valid @NotNull @RequestParam String userAnswer) {
        User user = userRepo.findByEmail(email);
        BugProperty bugProperty = bugPropertyRepo.getOne(bugId);
        CatcherBugRecord record = new CatcherBugRecord();
        record.setId(new CatcherBugKey().setUserId(user.getId()).setBugId(bugId))
                .setCatcher(user).setCaughtBug(bugProperty).setUserAnswer(userAnswer);
        recordRepo.save(record);

        // 将 rest_life_count 减 1
        bugProperty.setRestLifeCount(bugProperty.getRestLifeCount() - 1);
        bugPropertyRepo.save(bugProperty);

        return new ReturnVO().setCode(200).setMessage("增加约束成功");
    }

    /**
     * 通过用户邮箱，获取用户捕捉过的虫子
     */
    @GetMapping("/getCatchRecordsByEmail")
    public Set<CatcherBugRecord> getCatchRecordsByEmail(
            @Valid @NotNull @Email @RequestParam String email) {
        return userRepo.findByEmail(email).getCatchRecords();
    }

    @RequestMapping(value = "/deleteUserCatchesBugConstraint", method = RequestMethod.GET)
    public ReturnVO deleteUserCatchesBugConstraint(
            @Valid @NotNull @RequestParam String email) {
        User user = userRepo.findByEmail(email);
        recordRepo.deleteAll(user.getCatchRecords());
        return new ReturnVO().setCode(200).setMessage("删除约束成功");
    }

    @PutMapping("/bug/{id}")
    public ReturnVO updateBug(@Valid @NotNull @PathVariable Long id, @RequestBody Bug bug) throws Exception {
        Bug bugInDatabase = getBugById(id);
        VirusPoint toVirusPoint = bug.getVirusPoint();
        if (toVirusPoint != null) {
            Integer toStatus = toVirusPoint.getStatus();
            if (toStatus != null) {
                bugInDatabase.getVirusPoint().setStatus(toStatus);
            }
        }
        bugContentRepo.save(bugInDatabase.getVirusPoint());
        return new ReturnVO().setCode(200).setMessage("success");
    }


    // 以下3个接口均为多对多关系表为最简模式（不能额外添加属性）时的接口

//    @RequestMapping(value = "/addUserCatchesBugConstraint", method = RequestMethod.GET)
//    public ReturnVO addUserCatchesBugConstraint(
//            @Valid @NotNull @RequestParam Long bugId,
//            @Valid @NotNull @RequestParam String email) {
//        BugProperty bug = bugPropertyRepo.getOne(bugId);
//        User user = userRepo.findByEmail(email);
//        user.getCaughtBugs().add(bug);
//        userRepo.save(user);
//        return new ReturnVO().setCode(200).setMessage("增加约束成功");
//    }

//    @RequestMapping(value = "/deleteUserCatchesBugConstraint", method = RequestMethod.GET)
//    public ReturnVO deleteUserCatchesBugConstraint(
//            @Valid @NotNull @RequestParam String email) {
//        User user = userRepo.findByEmail(email);
//        user.getCaughtBugs().clear();
//        userRepo.save(user);
//        return new ReturnVO().setCode(200).setMessage("删除约束成功");
//    }

//    /**
//     * 通过用户邮箱，获取用户捕捉过的虫子
//     */
//    @GetMapping("/getCaughtBugsByEmail")
//    public List<Bug> getCaughtBugsByEmail(
//            @Valid @NotNull @Email @RequestParam String email) {
//        List<BugProperty> caughtBugs = userRepo.findByEmail(email).getCaughtBugs();
//        List<Bug> bugList = new ArrayList<>();
//        for (BugProperty bugProperty : caughtBugs) {
//            Bug bug = new Bug();
//            bug.setBugProperty(bugProperty);
//            switch (bugProperty.getBugContent().getType()) {
//                case 0:
//                    bug.setMoment((Moment) bugProperty.getBugContent());
//                    break;
//                case 1:
//                    bug.setChoiceQuestion((ChoiceQuestion) bugProperty.getBugContent());
//                    break;
//                case 2:
//                    bug.setNarrativeQuestion((NarrativeQuestion) bugProperty.getBugContent());
//                    break;
//                case 3:
//                    break;
//            }
//            bugList.add(bug);
//        }
//        return bugList;
//    }
}
