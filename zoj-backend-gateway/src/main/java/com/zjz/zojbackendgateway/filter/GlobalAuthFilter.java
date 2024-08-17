package com.zjz.zojbackendgateway.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import com.zjz.common.exception.BusinessException;
import com.zjz.common.utils.JwtTool;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 网关全局权限校验
 */
@Component
public class GlobalAuthFilter implements GlobalFilter, Ordered {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final String[] noAuthPath = {"/**/login", "/**/register", "/**/question/get/vo","/**/list/page/vo"};

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String path = serverHttpRequest.getURI().getPath();
        // 1. 判断路径中是否包含 inner，只允许内部调用
        if (antPathMatcher.match("/**/inner/** ", path)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer dataBuffer = dataBufferFactory.wrap("无权限".getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(dataBuffer));
        }
        // 2. 判断是否需要拦截
        if (isExclude(serverHttpRequest.getPath().toString())){
            return chain.filter(exchange);
        }

        List<String> headers = serverHttpRequest.getHeaders().get("Authorization");
        String token = null;
        // 3. 获取 token
        if(!CollUtil.isEmpty(headers)){
            token = headers.get(0);
        }
        // 4. 校验 token
        Long userId;
        try {
            userId = JwtTool.parseToken(token);
        } catch (BusinessException e){
            ServerHttpResponse response = exchange.getResponse();
            response.setRawStatusCode(401);
            return response.setComplete();
        }

        Long finalUserId = userId;
        ServerWebExchange userExchange = exchange.mutate()
                .request(b -> b.header("user", finalUserId.toString()))
                .build();

        return chain.filter(userExchange);
    }

    private boolean isExclude(String antPath) {
        for (String excludePath : noAuthPath) {
            if(antPathMatcher.match(excludePath, antPath)){
                return true;
            }
        }
        return false;
    }

    /**
     * 设置最高优先级
     */
    @Override
    public int getOrder() {
        return 0;
    }
}

