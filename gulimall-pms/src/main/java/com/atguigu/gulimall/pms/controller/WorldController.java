package com.atguigu.gulimall.pms.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author winsoso
 * @title: WorldController
 * @projectName gulimall
 * @date 2019/8/221:18
 */
@RestController
public class WorldController {
    @Value("${spring.datasource.url}")
    private String val;
    @GetMapping("/world")
    public String World(){
        //省略了service /world 就是service 的结果
        String msg ="world"+val;
        return  msg;
    }
}
