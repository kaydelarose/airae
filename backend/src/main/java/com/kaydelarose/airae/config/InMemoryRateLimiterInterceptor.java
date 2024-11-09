package com.kaydelarose.airae.config;

import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InMemoryRateLimiterInterceptor implements HandlerInterceptor {

    private final Map<String, RequestInfo> requestCounts = new HashMap<>();
    private static final int MAX_REQUESTS = 10; // Limit per minute
    private static final long TIME_WINDOW_MS = 60_000; // 1 minute

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientIp = request.getRemoteAddr();
        RequestInfo requestInfo = requestCounts.getOrDefault(clientIp, new RequestInfo());

        synchronized (requestInfo) {
            long currentTime = Instant.now().toEpochMilli();
            if (currentTime - requestInfo.startTime > TIME_WINDOW_MS) {
                requestInfo.count.set(1);
                requestInfo.startTime = currentTime;
            } else {
                if (requestInfo.count.incrementAndGet() > MAX_REQUESTS) {
                    response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                    response.getWriter().write("Rate limit exceeded. Try again later.");
                    return false;
                }
            }
            requestCounts.put(clientIp, requestInfo);
        }
        return true;
    }

    private static class RequestInfo {
        AtomicInteger count = new AtomicInteger(0);
        long startTime = Instant.now().toEpochMilli();
    }
}
