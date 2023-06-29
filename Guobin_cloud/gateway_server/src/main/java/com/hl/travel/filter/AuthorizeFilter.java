package com.hl.travel.filter;


import com.hl.travel.config.RedisSessionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Import;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;

import org.springframework.session.MapSession;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Import(RedisSessionConfig.class)
public class AuthorizeFilter implements GlobalFilter {

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;
//
//    @Autowired
//    private RedisOperationsSessionRepository sessionRepository;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        // 2.校验请求的URL的后缀是否为.do
        String path = exchange.getRequest().getPath().toString();



        try {
            WebSession session = exchange.getSession().toFuture().get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        String sessionId = "";
        String sessionKey = "";

        // 通过ServerWebExchange对象获取请求的ServerHttpRequest
        ServerHttpRequest request = exchange.getRequest();

        // 获取请求中的所有Cookie
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();

        // 获取名为"SESSION"的Cookie
        List<HttpCookie> sessionCookies = cookies.get("SESSION");

        if (sessionCookies != null && !sessionCookies.isEmpty()) {
            // 获取第一个SESSION Cookie的值
            sessionId = sessionCookies.get(0).getValue();
             sessionKey ="spring:session:sessions:" + sessionId;

        }

        // 3.校验 login.do
        if (path.endsWith("login.do") || path.endsWith("logout.do")) {  // 配置文件重写成功 先执行配置文件的断言，才执行这里的代码 所以这里占个位，没用

            // 放行
            return chain.filter(exchange);
        } else {

            if (sessionId != null) {
                // 会话有效，放行请求
                return chain.filter(exchange);
            } else {
                // 会话无效，拦截请求
                // 4.1. 禁止访问，设置状态码
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                // 4.2. 结束处理
                return exchange.getResponse().setComplete();
            }
        }




    }

//    private boolean isSessionValid(String sessionId) {
//        // 在这里编写逻辑，根据sessionId从Redis中验证会话是否有效
//        // 如果会话有效，返回true；否则返回false
//        // 您可以使用Spring Session的API来检查会话的有效性
//        // 通过sessionId从Redis中获取会话
//        // 例如，通过注入RedisOperationsSessionRepository并调用findById方法
//        Session session = sessionRepository.findById(sessionId);
//        // 来获取会话并进行相应的验证逻辑
//        // 检查会话是否存在且有效
//        if (session != null && !session.isExpired()) {
//            // 会话有效
//            return true;
//        } else {
//            // 会话无效
//            return false;
//        }
//
//
//    }



}