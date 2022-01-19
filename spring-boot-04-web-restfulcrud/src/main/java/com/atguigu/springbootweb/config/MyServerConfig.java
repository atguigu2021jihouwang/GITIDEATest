package com.atguigu.springbootweb.config;

import com.atguigu.springbootweb.filter.myFilter;
import com.atguigu.springbootweb.listener.MyListener;
import com.atguigu.springbootweb.servlet.MyServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Created by JHW on 2021/8/26.
 */
@Configuration
public class MyServerConfig {

    //注册三大组件
    /**
     * http://localhost:8084/crud/myServlet 访问路径
     * Servlet
     */
    @Bean
    public ServletRegistrationBean myServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new MyServlet(),"/myServlet");
        registrationBean.setLoadOnStartup(1);
        return registrationBean;
    }

    /**
     * http://localhost:8084/crud/myServlet
     * http://localhost:8084/crud/hello
     * Filter
     */
    @Bean public FilterRegistrationBean myFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new myFilter());
        // 拦截路径
        registrationBean.setUrlPatterns(Arrays.asList("/hello","/myServlet"));
        return registrationBean;
    }

    // Listener
    @Bean public ServletListenerRegistrationBean myListener(){
        ServletListenerRegistrationBean<MyListener> registrationBean = new ServletListenerRegistrationBean<>(new MyListener());
        return registrationBean;
    }

//    //配置嵌入式的Servlet容器   和在配置文件中写，效果一样
//    @Bean
//    public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
//        return new EmbeddedServletContainerCustomizer() {
//
//            //定制嵌入式的Servlet容器相关的规则
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                container.setPort(8083);
//            }
//        };
//    }
}
