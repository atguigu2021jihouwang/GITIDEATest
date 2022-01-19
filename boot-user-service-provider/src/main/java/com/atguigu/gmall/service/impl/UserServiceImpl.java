package com.atguigu.gmall.service.impl;

import java.util.Arrays;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Component;

// 这个是 dubbo的service 暴露服务
@Service//(weight = 50)//权重  // 如果使用的是配置文件，那么这里就不用dubbo的@Service注解,包括入口的@EnableDubbo注解
// @Service spring的Service  可以换成 @Component 只是为了错开
@Component   // 使用注解API的方式  配置类
public class UserServiceImpl implements UserService {

	@HystrixCommand   // 服务容错的代理对象
	@Override
	public List<UserAddress> getUserAddressList(String userId) {
		System.out.println("UserServiceImpl.....3...");
		UserAddress address1 = new UserAddress(1, "北京市昌平区宏福科技园综合楼3层", "1", "李老师", "010-56253825", "Y");
		UserAddress address2 = new UserAddress(2, "深圳市宝安区西部硅谷大厦B座3层（深圳分校）", "1", "王老师", "010-56253825", "N");
		return Arrays.asList(address1,address2);
	}

}
