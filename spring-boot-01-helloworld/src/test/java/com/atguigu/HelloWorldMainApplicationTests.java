package com.atguigu;

import com.atguigu.bean.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by JHW on 2021/8/21.
 */

@SpringBootTest
public class HelloWorldMainApplicationTests {

    @Autowired
    public Person person;


    @Test
    public void contextLoads() {
        System.out.println(person);
    }

}
