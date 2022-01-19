package com.atguigu.springbootdatamybatis.config;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;

/**
 * Created by JHW on 2021/8/28.
 */

@org.springframework.context.annotation.Configuration
public class MyBatisConfig {

    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer(){

            @Override
            public void customize(Configuration configuration) {
                // 使用驼峰命名法  以前用的是配置文件
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }
}
