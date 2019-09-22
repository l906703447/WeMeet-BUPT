package com.wemeet.wemeet;

import com.wemeet.wemeet.entity.*;
import com.wemeet.wemeet.repository.BugContentRepo;
import com.wemeet.wemeet.repository.BugPropertyRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WemeetApplicationTests {

    @Autowired
    private BugPropertyRepo bugPropertyRepo;
    @Autowired
    private BugContentRepo bugContentRepo;

    @Test
    public void contextLoads() {
    }

//    @Test
    public void addBugProperty() {
        List<BugProperty> bugProperties = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            double d = new Random().nextDouble() * 500000;
            BugProperty bugProperty = new BugProperty();
            bugProperty.setStartLongitude(d)
                    .setStartLatitude(d)
                    .setMovable(true)
                    .setDestLongitude(10.0)
                    .setDestLatitude(10.0)
                    .setSurvivalTime(2)
                    .setStartTime(new Timestamp(new Date().getTime()))
                    .setLifeCount(5)
                    .setTags(new ArrayList<>(Arrays.asList("tag1", "tag2", "tag3")))
                    .setBugContent(bugContentRepo.getOne(((long) new Random().nextInt(3) + 1)));
            bugProperties.add(bugProperty);
        }
        bugPropertyRepo.saveAll(bugProperties);
    }

//    @Test
    public void addBugContent() {
        // type指定增加哪种类型的内容
        int type = new Random().nextInt(3);     // 0 1 2
        // 下面代码创建并初始化一个ContentDesc对象
        ContentDesc contentDesc = new ContentDesc();
        try (
             FileInputStream inFile = new FileInputStream(new File("E:\\Pictures\\pictures\\罗.jpeg"));
             ByteArrayOutputStream outFile = new ByteArrayOutputStream(1024)
        ) {
            byte[] temp = new byte[1024];
            int size;
            while ((size = inFile.read(temp)) != -1) {
                outFile.write(temp, 0, size);
            }
            contentDesc.setTextContent("Who is he?")
                    .setPictureContent(outFile.toByteArray())
                    .setVideoContent("E:\\video\\test.avi");
        } catch (IOException e) {
            e.printStackTrace();
        }

        BugContent bugContent = null;
        switch (type) {
            case 0: {
                bugContent = new Moment();
                ((Moment) bugContent).setContentDesc(contentDesc)
                        .setType(type)
                        .setPublishTime(new Timestamp(new Date().getTime()));
            } break;
            case 1: {
                bugContent = new ChoiceQuestion();
                ((ChoiceQuestion)bugContent).setQuestion(contentDesc)
                        .setChoiceA("罗")
                        .setChoiceB("路飞")
                        .setChoiceC("娜美")
                        .setChoiceD("索隆")
                        .setCorrectAnswer("A")
                        .setType(type)
                        .setPublishTime(new Timestamp(new Date().getTime()));
            } break;
            case 2: {
                bugContent = new NarrativeQuestion();
                ((NarrativeQuestion)bugContent).setQuestion(contentDesc)
                        .setCorrectAnswer("罗")
                        .setType(type)
                        .setPublishTime(new Timestamp(new Date().getTime()));
            } break;
            default:
                System.err.println("No such type");
        }
        bugContentRepo.save(bugContent);
    }
}
