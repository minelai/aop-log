package com.github.example;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基础测试类
 * @author: Mr.lai
 * @date: 2020/11/8 10:02
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class) // 程序启动类
public class BaseTest {

    private long time;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Before
    public void init() {
        this.setTime(System.currentTimeMillis());
        log.info("测试开始：");
    }

    @After
    public void after() {
        log.info("测试结束：程序运行时间，{}", System.currentTimeMillis() - this.getTime());
    }

    @Ignore
    @Test
    public void Test1(){
        log.info("Test JUnit");
    }

}
