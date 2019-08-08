package com.atguigu.gulimall.wms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * 1.注册配置中心
 *      a.导包 b.写配置 c.加注释 @EnableDiscoveryClient
 * 2. 配置中心
 * a.@RefreshScope 刷新
 */
@MapperScan("com.atguigu.gulimall.wms.dao")
@RefreshScope
@SpringBootApplication
public class GulimallWmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GulimallWmsApplication.class, args);
    }

}
