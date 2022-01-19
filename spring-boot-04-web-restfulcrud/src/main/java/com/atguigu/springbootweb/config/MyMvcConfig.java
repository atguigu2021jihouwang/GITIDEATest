package com.atguigu.springbootweb.config;

import com.atguigu.springbootweb.component.LoginHandlerInterceptor;
import com.atguigu.springbootweb.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by JHW on 2021/8/22.
 */


//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //浏览器发送 /atguigu 请求来到 success
        registry.addViewController("/atguigu").setViewName("success");
    }

    // 自定义的视图解析器(访问路径)
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean  //将组件注册在容器
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // 访问首页
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                // 防止重复提交,使用重定向
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            // 注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //super.addInterceptors(registry);
                //静态资源；  *.css , *.js
                //SpringBoot已经做好了静态资源映射
                /**
                 * addPathPatterns("/**") 表示: 哪些请求路径需要拦截  @PostMapping(value = "/user/login")   /**  下面所有的路径
                 *  相当于: 配置文件中
                 *  <mvc:interceptor>
                 *      <mvc:mapping path="/emps"/>
                 *      <bean class="com.atguigu.springmvc.interceptors.SecondInterceptor"></bean>
                 *   </mvc:interceptor>
                 *
                 *  excludePathPatterns("/index.html","/","/user/login");  哪些路径不需要拦截
                 *
                 *  http://localhost:8083/crud/main.html
                 */
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/","/user/login");
            }
        };
        return adapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
