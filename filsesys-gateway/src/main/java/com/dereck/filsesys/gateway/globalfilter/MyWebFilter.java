package com.dereck.filsesys.gateway.globalfilter;

import com.dereck.filesys.common.entity.R;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class MyWebFilter implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        // 非登录请求，拦截
        if (!(antPathMatcher.match("/login", path) || antPathMatcher.match("/register", path))){
            // 获取token
            List<String> tokenList = request.getHeaders().get("token");
            // 没有token(token校验交给admin模块)
            if (tokenList == null) {
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            }
        }
        return chain.filter(exchange);
    }

    // 使用webflux输入请求信息
    private Mono<Void> out(ServerHttpResponse response) {
        R res = R.fail("您未登录", HttpStatus.FORBIDDEN);
        byte[] bytes = res.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        // 指定编码，否则在浏览器中会中文乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        // 输出http响应
        return response.writeWith(Mono.just(buffer));
    }


    @Override
    public int getOrder() {
        return 0;
    }
}
