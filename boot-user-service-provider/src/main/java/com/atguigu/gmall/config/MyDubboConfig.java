package com.atguigu.gmall.config;

import com.alibaba.dubbo.config.*;
import com.atguigu.gmall.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JHW on 2021/11/24.
 */
// 配置类
@Configuration
public class MyDubboConfig {

    // <dubbo:application name="user-service-provider"></dubbo:application>
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("boot-user-service-provider");
        return applicationConfig;
    }

    // <dubbo:registry protocol="zookeeper" address="127.0.0.1:2181"></dubbo:registry>
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress("127.0.0.1:2181");
        return registryConfig;
    }

    //<dubbo:protocol name="dubbo" port="20882"></dubbo:protocol>
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20882);
        return protocolConfig;
    }

    /**
     *<dubbo:service interface="com.atguigu.gmall.service.UserService"
     ref="userServiceImpl01" timeout="1000" version="1.0.0">
     <dubbo:method name="getUserAddressList" timeout="1000"></dubbo:method>
     </dubbo:service>
     */
    @Bean
    public ServiceConfig<UserService> userServiceServiceConfig(UserService userServiceImpl){
        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(UserService.class);  // 接口名
        serviceConfig.setRef(userServiceImpl); // 具体实现类
        serviceConfig.setVersion("1.0.0");

        //配置每一个method的信息
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("getUserAddressList");
        methodConfig.setTimeout(1000);

        //将method的设置关联到service配置中
        List<MethodConfig> methods = new ArrayList<>();
        methods.add(methodConfig);
        serviceConfig.setMethods(methods);

        return serviceConfig;
    }

    // ProviderConfig  <!-- 统一配置 -->
//    @Bean
//    public ProviderConfig providerConfig(){
//        ProviderConfig providerConfig = new ProviderConfig();
//        return providerConfig;
//    }

    // MonitorConfig  <!-- 通过注册的方式动态的链接监控中心 -->
//    @Bean
//    public MonitorConfig monitorConfig(){
//        MonitorConfig monitorConfig = new MonitorConfig();
//        return  monitorConfig;
//    }
}
