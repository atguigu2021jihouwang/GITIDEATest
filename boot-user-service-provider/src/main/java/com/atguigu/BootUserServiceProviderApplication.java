package com.atguigu;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;


/**
 * 1、导入依赖；
 * 		1）、导入dubbo-starter
 * 		2）、导入dubbo的其他依赖
 * @author lfy
 *
 * SpringBoot与dubbo整合的三种方式：
 * 1）、导入dubbo-starter，在application.properties配置属性，使用@Service【暴露服务】使用@Reference【引用服务】
 * 2）、保留dubbo xml配置文件;
 * 			导入dubbo-starter，使用@ImportResource导入dubbo的配置文件即可
 * 3）、使用注解API的方式：
 * 		将每一个组件手动创建到容器中,让dubbo来扫描其他的组件
 */

/**
 * 1. -Ddubbo.protocol.port=20880
 */
@EnableHystrix // 开启服务容错
//@EnableDubbo                                          // 开启基于注解的dubbo功能   第一种方式
//@ImportResource(locations = "classpath:provider.xml") // 第二种方式  读取导入的配置文件,这样就不需要 @EnableDubbo 注解了
@EnableDubbo(scanBasePackages = "com.atguigu.gmall")    // 第三种方式 扫描方式
@SpringBootApplication
public class BootUserServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootUserServiceProviderApplication.class, args);
	}

}
