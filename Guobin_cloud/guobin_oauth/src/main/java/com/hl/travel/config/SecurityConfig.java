package com.hl.travel.config;

import com.hl.travel.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseCookie;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private com.hl.travel.security.SpringSecurityUserService SpringSecurityUserService;

    @Autowired
    private com.hl.travel.security.SecurityLogoutSuccessHandler SecurityLogoutSuccessHandler;




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()// 关闭csrf
                .headers().frameOptions().disable()// 解决 in a frame because it set 'X-Frame-Options' to 'deny' 问题         // 开启允许iframe 嵌套
                .and()
                .cors()//跨域
                .configurationSource(corsConfigurationSource())  // 网关已经配置了跨域
                .disable()
//                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 不创建session
                .and()
                .authorizeRequests()
                // 其他请求配置
//                .antMatchers(MessageConstant.LOGIN_SUCCESS_URL+"/pages/**").authenticated() // 需要认证才能访问
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // 允许 OPTIONS 请求通过 用于前端跨域请求 预检请求
                .antMatchers("/swagger-ui/**").permitAll() // 允许swagger-ui.html请求通过
                .antMatchers("/v3/api-docs/**").permitAll() // 允许/v3/api-docs/**请求通过
                .antMatchers("/user/**").permitAll() // 允许/swagger-resources/**请求通过
                .anyRequest().authenticated() // 对其他请求进行身份验证
                .and()
                .formLogin()
                .loginPage(MessageConstant.LOGIN_SUCCESS_URL+"/login.html") // 登录页面
                .loginProcessingUrl("/login.do")// 登录请求拦截的url,也就是form表单提交时指定的action
                .permitAll() // 允许所有用户访问
                .successHandler(loginSuccessHandler()) // 使用自定义的登录成功处理器
                .failureUrl(MessageConstant.LOGIN_SUCCESS_URL+"/login.html")// 登录失败后跳转的url
                .and()
//                .exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler())//权限不足处理器
                .logout()
                .logoutUrl("/logout.do") // 退出登录拦截的url
//                // 退出登录删除指定的cookie
                .deleteCookies("TOKEN","SESSION")
                .logoutSuccessHandler(SecurityLogoutSuccessHandler);// 退出登录成功后的处理器

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
                // 创建会话
                HttpSession session = request.getSession();
                // 获取会话ID
                String sessionId = session.getId();

                // 创建包含会话ID的Cookie
                Cookie sessionCookie = new Cookie("TOKEN", sessionId);
                sessionCookie.setPath("/");

                // 将Cookie添加到响应中
                response.addCookie(sessionCookie);
                response.sendRedirect(MessageConstant.LOGIN_SUCCESS_URL+"/pages/main.html");
            }
        };
    }

    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // 允许携带cookie跨域
        configuration.setAllowedOrigins(Arrays.asList(MessageConstant.LOGIN_SUCCESS_URL,MessageConstant.LOGIN_FRONT_URL)); // 允许所有域名进行跨域调用
        configuration.setAllowedMethods(Collections.singletonList("*")); // 允许所有请求方法跨域调用
        configuration.setAllowedHeaders(Collections.singletonList("*")); // 允许所有请求头跨域调用
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 对所有请求路径进行跨域设置
        return source;
    }


}
