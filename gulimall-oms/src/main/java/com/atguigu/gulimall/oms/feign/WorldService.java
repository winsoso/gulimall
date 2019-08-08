package com.atguigu.gulimall.oms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author winsoso
 * @title: WorldService
 * @projectName gulimall
 * @date 2019/8/221:24
 */


@FeignClient(name = "gulimall-pms")
public interface WorldService {
//把pms 的方法签名放入就可以了
    @GetMapping("/world")
    public String World();
}
