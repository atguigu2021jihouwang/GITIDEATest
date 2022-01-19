package com.atguigu.springbootamqp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot09AmqpApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;  // RabbitMQ 系统管理功能组件

	/**
	 * 1、单播（点对点） 发送
	 */
	@Test
	public void contextLoads() {
		/**
		 * Message需要自己构造一个;定义消息体内容和消息头
		 * rabbitTemplate.send(exchage,routeKey,message);
		 * object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq;
		 * rabbitTemplate.convertAndSend(exchage,routeKey,object);
		 */
//		Map<String,Object> map = new HashMap<>();
//		map.put("msg","这是第一个消息");
//		map.put("data", Arrays.asList("helloworld",123,true));
		//对象被默认序列化以后发送出去
		//rabbitTemplate.convertAndSend("exchange.hello","atguigu.hello",map);
		rabbitTemplate.convertAndSend("exchange.hello","atguigu.hello",
				new com.atguigu.springbootamqp.bean.Book("西游记","吴承恩"));
	}

	//接受数据,如何将数据自动的转为json发送出去
	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("atguigu.hello");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	/**
	 * 广播
	 */
	@Test
	public void sendMsg(){
		rabbitTemplate.convertAndSend("exchange.fanout","",
				new com.atguigu.springbootamqp.bean.Book("舒克贝塔","郑渊杰"));
	}

	@Test
	public void createExchange(){
		// 创建一个交换器 Exchange
//		amqpAdmin.declareExchange(new DirectExchange("amqpadmin.exchange"));
//		System.out.println("amqpadmin.exchange：创建完成");

		// 创建一个消息队列 Queue
//		amqpAdmin.declareQueue(new Queue("amqpadmin.queue",true));
//		System.out.println("amqpadmin.queue：创建完成");

		// 绑定交换器和消息队列
		amqpAdmin.declareBinding(new Binding("amqpadmin.queue", Binding.DestinationType.QUEUE,
				"amqpadmin.exchange","amqp.haha",null));
	}

}
