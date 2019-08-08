package com.atguigu.gulimall.ums;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@MapperScan("com.atguigu.gulimall.ums.dao")
@RefreshScope
@SpringBootApplication
public class GulimallUmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallUmsApplication.class, args);
    }

}
