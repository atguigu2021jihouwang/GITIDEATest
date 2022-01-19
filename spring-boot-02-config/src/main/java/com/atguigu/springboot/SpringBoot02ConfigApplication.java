package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 *  导入Spring的配置文件，让配置文件里面的内容生效；
 *  但springBoot不建议这么用
 */
//@ImportResource(locations = {"classpath:beans.xml"})
@SpringBootApplication  // 相当于配置类
public class SpringBoot02ConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot02ConfigApplication.class, args);
	}

}
