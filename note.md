[TOC]

##### 实现多值字段的映射 - @ElementCollection

``` java
@Entity
public class BugProperty {
    @Id
    @GeneratedValue
    private Long id;
	
    // 省略其他字段
    
    @ElementCollection
    private List<String> tags;
}
```

具体映射到数据库中的表示为 两张表：

bug_property

| id   | 其他字段 |
| ---- | -------- |
|      |          |

bug_property_tags

| bug_property_id | tags |
| --------------- | ---- |
|                 |      |

##### 实现链式调用

- 通过 lombok 的@Accessors注解可轻易实现

  ```java
  @Accessors(chain = true)
  public class BugProperty {
  }
  ```

- 调用形式如下：

  ```java
  BugProperty bugProperty = new BugProperty();
  bugProperty.setStartLongitude(0.0)
      .setStartLatitude(0.0)
      .setMovable(false)
      .setSurvivalTime(2)
      .setStartTime(new Timestamp(new Date().getTime()))
      .setLifeCount(5)
      .setTags(new ArrayList<>(Arrays.asList("tag1", "tag2", "tag3")));
  ```

##### 简单初始化一个ArrayList

```java
new ArrayList<>(Arrays.asList("tag1", "tag2", "tag3"))
```

