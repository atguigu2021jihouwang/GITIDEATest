package com.atguigu.springboot.config;

import com.atguigu.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JHW on 2021/8/21.
 */

/**
 * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件
 * 在配置文件中用<bean><bean/>标签添加组件
 */
@Configuration  // 配置类
public class MyAppConfig {

    // 添加bean
    @Bean(value = "helloService")
    public HelloService helloService02(){
        return  new HelloService();
    }

}
