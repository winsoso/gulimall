package com.atguigu.gulimall.wms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author winsoso
 * @title: WmsSecurityConfig
 * @projectName gulimall
 * @date 2019/8/53:12
 */
@Configuration
public class WmsSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().permitAll();

        http.csrf().disable();
    }
}
