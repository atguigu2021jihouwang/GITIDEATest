package com.atguigu.springbootamqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 自动配置
 *  1、RabbitAutoConfiguration
 *  2、有自动配置了连接工厂ConnectionFactory；
 *  3、RabbitProperties 封装了 RabbitMQ的配置
 *  4、 RabbitTemplate ：给RabbitMQ发送和接受消息；
 *  5、 AmqpAdmin ： RabbitMQ系统管理功能组件;
 *  	AmqpAdmin：创建和删除 Queue，Exchange，Binding
 *  6、@EnableRabbit +  @RabbitListener 监听消息队列的内容
 */
//开启基于注解的RabbitMQ模式     类里面有关 RabbitMQ 的注解生效,需要开启 @EnableRabbit 注解
@EnableRabbit
@SpringBootApplication
public class Springboot09AmqpApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot09AmqpApplication.class, args);
	}

}
