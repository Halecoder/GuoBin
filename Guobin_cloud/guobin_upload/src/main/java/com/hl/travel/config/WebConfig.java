package com.hl.travel.config;


import com.hl.travel.constant.MessageConstant;
import com.hl.travel.interceptor.TravelInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TravelInterceptor()).addPathPatterns("/**");

    }

    /**
     * @param registry 跨域请求处理
     * 引入SpringSecurity后，这种方法会失效
     */
    @Override
    public  void  addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins(MessageConstant.LOGIN_SUCCESS_URL)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(30*1000);
    }


}