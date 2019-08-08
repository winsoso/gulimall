package com.atguigu.gulimall.pms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 配置文件夹中加
 * mybatis-plus:
 *   mapper-locations: classpath:/mapper/pms/*.xml
 *   使用MapperScan扫描所有的mapper接口
 */
@EnableTransactionManagement//开启事务注解驱动
@RefreshScope
@MapperScan(basePackages = "com.atguigu.gulimall.pms.dao")
@SpringBootApplication
public class GulimallPmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallPmsApplication.class, args);
    }

}
