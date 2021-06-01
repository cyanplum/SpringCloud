package org.uppower.sevenlion.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author AnQi
 * @date 2021/5/19 14:24:45
 * @description
 */
@Configuration
public class TokenFilter implements GlobalFilter {
   
   @Override
   public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
	   HttpHeaders headers = exchange.getRequest().getHeaders();
	   return chain.filter(exchange);
   }
   
}
