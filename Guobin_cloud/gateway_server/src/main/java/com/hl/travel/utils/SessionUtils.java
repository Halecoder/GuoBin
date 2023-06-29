package com.hl.travel.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.web.server.ServerWebExchange;

public class SessionUtils {

    private final RedisOperationsSessionRepository sessionRepository;
    private final RedisTemplate<Object, Object> redisTemplate;

    public SessionUtils(RedisOperationsSessionRepository sessionRepository,
                        RedisTemplate<Object, Object> redisTemplate) {
        this.sessionRepository = sessionRepository;
        this.redisTemplate = redisTemplate;
    }

    public Session getSessionFromRedis(String sessionId) {
        return sessionRepository.findById(sessionId);
    }

    public Session getSessionFromRequest(ServerWebExchange exchange) {
        String sessionId = extractSessionIdFromRequest(exchange);
        if (sessionId != null) {
            return getSessionFromRedis(sessionId);
        }
        return null;
    }

    private String extractSessionIdFromRequest(ServerWebExchange exchange) {
        // 从请求中获取Session ID，根据实际情况进行提取，以下代码仅为示例
        // 例如，从请求头或Cookie中获取Session ID
        String sessionId = exchange.getRequest().getHeaders().getFirst("sessionId");
        if (sessionId == null) {
            sessionId = String.valueOf(exchange.getRequest().getCookies().getFirst("sessionId"));
        }
        return sessionId;
    }
}
