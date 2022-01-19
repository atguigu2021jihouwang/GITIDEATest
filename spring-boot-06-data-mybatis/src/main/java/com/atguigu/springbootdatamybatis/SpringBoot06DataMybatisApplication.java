package com.atguigu.springbootdatamybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 如果 mapper文件太多,在每个接口上配置 @Mapper 注解太麻烦,
 * 可以直接使用 @MapperScan注解(和之前使用的扫包注解一样,),
 * 注意: 如果这里配置了扫包,那么在文件上就不用配置 @Mapper 注解了
 */
@MapperScan(value = {"com.atguigu.springbootdatamybatis.mapper"})
@SpringBootApplication
public class SpringBoot06DataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot06DataMybatisApplication.class, args);
	}

}
