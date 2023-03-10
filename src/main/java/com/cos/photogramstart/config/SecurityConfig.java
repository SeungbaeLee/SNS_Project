package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity//해당 파일로 시큐리티를 활성화
@Configuration//IOC
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encode(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //csrf 비활성화
        http.csrf().disable();
        //super 삭제 -> 기존 시큐리티가 가지고 있는 기능이 다 비활성화됨.
        http.authorizeRequests()
                .antMatchers("/", "user/**", "/image/**", "subscribe/**", "/comment/**","/api/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/signin")//GET요청 반응
                .loginProcessingUrl("/auth/signin")//POST요청 반응(로그인요청) -> 스프링 시큐리티가 로그인 프로세스 진행
                .defaultSuccessUrl("/");
    }
}
