package com.atguigu.gulimall.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author winsoso
 * @title: GulimallGateWayConfig
 * @projectName gulimall
 * @date 2019/8/49:29
 */
@Configuration
public class GulimallGateWayConfig {

    /**
     *
     * @return
     */
    @Bean
    public CorsWebFilter corsWebFilter(){

        //跨域的配置
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);//允许带cookie的跨域


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",config);

        CorsWebFilter filter = new CorsWebFilter(source);

        return filter;
    }
}
