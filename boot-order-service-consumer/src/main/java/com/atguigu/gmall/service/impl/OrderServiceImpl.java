package com.atguigu.gmall.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.OrderService;
import com.atguigu.gmall.service.UserService;


/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 		1）、导入dubbo依赖（2.6.2）\操作zookeeper的客户端(curator)
 * 		2）、配置服务提供者
 * 
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 * @author lfy
 *
 */
@Service
public class OrderServiceImpl implements OrderService{

//	@Autowired
//	public UserService userService;

	// 远程引用UserService服务,会自动的从注册中心中获取
	@Reference(loadbalance = "roundrobin")// 负载均衡规则 // (url = "127.0.0.1:20882")  // 加上url: 表示绕过zookeeper注册中心,直接和dubbo连接
	public UserService userService;


	@HystrixCommand(fallbackMethod = "hello")// 回调方法
	@Override
	public List<UserAddress> initOrder(String userId) {
		//1、查询用户的收货地址
		List<UserAddress> addressList = userService.getUserAddressList(userId);
//		for (UserAddress userAddress : addressList) {
//			System.out.println(userAddress.getUserAddress());
//		}
		return addressList;
	}

	public List<UserAddress> hello(String userId) {
		return Arrays.asList(new UserAddress(10, "测试地址", "1", "测试", "测试", "Y"));
	}

}
