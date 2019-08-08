package com.atguigu.gulimall.pms.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author winsoso
 * @title: PmsCloudConfig
 * @projectName gulimall
 * @date 2019/8/220:42
 */
@Configuration
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.atguigu.gulimall.pms.feign")
public class PmsCloudConfig {


}
