package com.hl.travel.security;

import com.hl.travel.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启方法级别的权限控制
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private  SpringSecurityUserService SpringSecurityUserService;


    //配置后端服务

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        http
                .headers().frameOptions().sameOrigin()// 解决 in a frame because it set 'X-Frame-Options' to 'deny' 问题         // 开启允许iframe 嵌套
                .and()
                .cors()//跨域
                .configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                // 其他请求配置
//                .antMatchers(MessageConstant.LOGIN_SUCCESS_URL+"/pages/**").authenticated() // 需要认证才能访问
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 允许 OPTIONS 请求通过 用于前端跨域请求 预检请求
                .anyRequest().authenticated() // 对其他请求进行身份验证
                .and()
                .formLogin()
                .loginPage(MessageConstant.LOGIN_SUCCESS_URL+"/login.html") // 登录页面
                .loginProcessingUrl("/login.do")// 登录请求拦截的url,也就是form表单提交时指定的action
                .permitAll() // 允许所有用户访问
                .successHandler(loginSuccessHandler()) // 使用自定义的登录成功处理器
                .failureUrl(MessageConstant.LOGIN_SUCCESS_URL+"/login.html")// 登录失败后跳转的url
                .and()
                .logout()
                .logoutUrl("/logout.do") // 退出登录拦截的url
                .logoutSuccessUrl(MessageConstant.LOGIN_SUCCESS_URL+"/login.html") // 退出登录成功后跳转的url
                .and()
                .rememberMe() // 开启记住我功能
                .and()
                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())//权限不足处理器
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .invalidSessionUrl(MessageConstant.LOGIN_SUCCESS_URL+"/login.html")//session失效后跳转的url
                .maximumSessions(1);//同一账号同时登录最大用户数
    }




    // 静态资源放行 配置前端服务
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/plugins/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //inMemoryAuthentication 从内存中获取
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("admin")).roles("ADMIN");

        //注入userDetailsService的实现类
        auth.userDetailsService(SpringSecurityUserService).passwordEncoder(new BCryptPasswordEncoder());
    }





    @Bean
    public AuthenticationSuccessHandler loginSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                // 自定义登录成功后的处理逻辑
                // 这里可以重定向到指定的URL或保持原始的URL不变
                //保存一份到前端域名
                response.setHeader("Access-Control-Allow-Credentials", "true"); // 允许携带cookie跨域
                // 例如，重定向到前端页面的URL
                response.sendRedirect(MessageConstant.LOGIN_SUCCESS_URL+"/pages/main.html");
            }
        };
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // 允许携带cookie跨域
        configuration.setAllowedOrigins(Arrays.asList(MessageConstant.LOGIN_SUCCESS_URL,MessageConstant.LOGIN_FRONT_URL,"https://app.apifox.com")); // 允许所有域名进行跨域调用
        configuration.setAllowedMethods(Collections.singletonList("*")); // 允许所有请求方法跨域调用
        configuration.setAllowedHeaders(Collections.singletonList("*")); // 允许所有请求头跨域调用
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 对所有请求路径进行跨域设置
        return source;
    }





}
