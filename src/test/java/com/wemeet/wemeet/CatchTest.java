package com.wemeet.wemeet;

import com.wemeet.wemeet.repository.BugPropertyRepo;
import com.wemeet.wemeet.repository.CatcherBugRecordRepo;
import com.wemeet.wemeet.repository.UserRepo;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xieziwei99
 * 2020-02-19
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Log
public class CatchTest {
    @Autowired
    private BugPropertyRepo bugPropertyRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CatcherBugRecordRepo recordRepo;

    @Test
    public void contextLoads() {
    }

    /*
    @Test
    public void userCatchBug() {
        User user = userRepo.getOne(1L);
        BugProperty bugProperty = bugPropertyRepo.getOne(3L);
        CatcherBugKey key = new CatcherBugKey();
        key.setUserId(1L).setBugId(3L);
        CatcherBugRecord record = new CatcherBugRecord();
        record.setId(key).setCatcher(user).setCaughtBug(bugProperty);
        recordRepo.save(record);
    }

    @Test
    public void deleteUserBug() {
        recordRepo.deleteById(new CatcherBugKey().setBugId(3L).setUserId(1L));
    }

    @Test
    public void getUserAndBug() {
        User user = userRepo.getOne(1L);
        user.getCatchRecords().forEach(record -> System.out.println(record.getCaughtBug()));

        BugProperty bugProperty = bugPropertyRepo.getOne(3L);
        bugProperty.getCaughtRecords().forEach(record -> System.out.println(record.getCatcher()));
    }
    */


    // 以下3个Test均为多对多关系表为最简模式（不能额外添加属性）时的测试

    // 只可执行一次，会在数据库中插入数据
//    @Test
////    @Transactional    若使用此注解，则插入不成功，迷啊
//    public void addConstraint() {
//        User user = userRepo.getOne(2L);
//        User user1 = userRepo.getOne(8L);
//        BugProperty bugProperty = bugPropertyRepo.getOne(4L);
//        BugProperty bugProperty1 = bugPropertyRepo.getOne(7L);
//
//        // 维护端不可省略，即不能只写被维护端，而不写维护端
//        user.getCaughtBugs().add(bugProperty);
//        user.getCaughtBugs().add(bugProperty1);
//        user1.getCaughtBugs().add(bugProperty);
//        user1.getCaughtBugs().add(bugProperty1);
//        userRepo.save(user);
//        userRepo.save(user1);
//
//        // 被维护端可以省略，也可以不
////        bugProperty.getCatchers().add(user);
////        bugProperty.getCatchers().add(user1);
////        bugProperty1.getCatchers().add(user);
////        bugProperty1.getCatchers().add(user1);
////        bugPropertyRepo.save(bugProperty);
////        bugPropertyRepo.save(bugProperty1);
//        Assert.assertEquals(user1.getName(), "super");
//        Assert.assertEquals(bugProperty.getSurvivalTime().intValue(), 24);
//    }

    // 只可执行一次，且要求数据库中有相应数据，会在数据库中删除数据
//    @Test
//    public void deleteConstraint() {
//        User user = userRepo.getOne(2L);
//        User user1 = userRepo.getOne(8L);
//        BugProperty bugProperty = bugPropertyRepo.getOne(4L);
//        BugProperty bugProperty1 = bugPropertyRepo.getOne(7L);
//
//        // 维护端不可省略，即不能只写被维护端，而不写维护端
//        Assert.assertTrue(user.getCaughtBugs().remove(bugProperty));    // 需要重载equals方法
//        user.getCaughtBugs().remove(bugProperty1);
//        user1.getCaughtBugs().remove(bugProperty);
//        user1.getCaughtBugs().remove(bugProperty1);
//
//        // 被维护端可以省略，也可以不
////        Assert.assertTrue(bugProperty.getCatchers().remove(user));
////        bugProperty.getCatchers().remove(user1);
////        bugProperty1.getCatchers().remove(user);
////        bugProperty1.getCatchers().remove(user1);
//
////        bugPropertyRepo.save(bugProperty);
////        bugPropertyRepo.save(bugProperty1);
//        userRepo.save(user);
//        userRepo.save(user1);
//    }

//    @Test
//    public void getCatchersAndCaughtBugs() {
//        User user = userRepo.getOne(2L);
//        BugProperty bugProperty = bugPropertyRepo.getOne(4L);
//        user.getCaughtBugs().forEach(bugProperty1 -> log.info(bugProperty1.getBugID().toString()));
//        bugProperty.getCatchers().forEach(user1 -> log.info(user1.getName()));
//    }
}
