package com.atguigu.gulimall.pms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author winsoso
 * @title: PmsSecurityConfig
 * @projectName gulimall
 * @date 2019/8/213:27
 */
@EnableWebSecurity
@Configuration
public class PmsSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests().antMatchers("/**").permitAll();
        //csrf()默认开启，需要先关闭
        http.csrf().disable();
    }
}
