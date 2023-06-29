package com.hl.travel.filter;


import com.hl.travel.model.dao.RedisDao;
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
public class AuthorizeFilter implements GlobalFilter {

    @Autowired
    private RedisDao<String,String> sessionRedis;


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        MultiValueMap<String, String> params = exchange.getRequest().getQueryParams();
        // 2.校验请求的URL的后缀是否为.do
        String path = exchange.getRequest().getPath().toString();


        String sessionId = "";
        String sessionKey = "";

        // 通过ServerWebExchange对象获取请求的ServerHttpRequest
        ServerHttpRequest request = exchange.getRequest();

        // 获取请求中的所有Cookie
        MultiValueMap<String, HttpCookie> cookies = request.getCookies();

        // 获取名为"SESSION"的Cookie
        List<HttpCookie> sessionCookies = cookies.get("TOKEN");

        if (sessionCookies != null) {
            // 获取第一个SESSION Cookie的值
            sessionId = sessionCookies.get(0).getValue();
             sessionKey ="spring:session:sessions:" + sessionId;

        }

        // 3.校验 login.do
        if (path.endsWith("login.do") || path.endsWith("logout.do")) {  // 配置文件重写成功 先执行配置文件的断言，才执行这里的代码 所以这里占个位，没用

            // 放行
            return chain.filter(exchange);
        } else {

            if (sessionRedis.hasKey(sessionKey)) {
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



}