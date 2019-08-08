package com.atguigu.gulimall.oms.config;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author winsoso
 * @title: OmsCloudConfig
 * @projectName gulimall
 * @date 2019/8/221:01
 */
@Configuration
@EnableDiscoveryClient  //开启服务注册发现功能
@EnableFeignClients(basePackages = "com.atguigu.gulimall.oms.feign") //开启feign的远程调用功能。
public class OmsCloudConfig {


}

