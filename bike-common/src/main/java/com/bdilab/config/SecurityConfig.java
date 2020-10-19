package com.bdilab.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //静态资源
                .antMatchers(HttpMethod.GET, "/*.html","/**/*.html","/**/*.css","/**/*.js").permitAll()
                //swagger文档
                .antMatchers("/swagger-ui.html","/swagger-resources/**","/webjars/**","/*/api-docs").permitAll()
                //接口测试暂时放行
                .antMatchers("/bike/meta/**").permitAll()
                //所有请求都需要认证
                .anyRequest().authenticated();
    }
}
