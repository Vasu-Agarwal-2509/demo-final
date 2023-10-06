package com.vasu.APIGateway.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.vasu.APIGateway.service.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	@Autowired
	private RouteValidator validator;
	
	@Autowired
	private JwtUtil jwtUtil; 
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	
	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if(validator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("missing authorization header");
				}
			
				String authHeaders = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeaders != null && authHeaders.startsWith("Bearer ")) {
					authHeaders = authHeaders.substring(7);
				}
				try {
					jwtUtil.validateByToken(authHeaders);
				}
				catch (Exception e) {
					System.out.println("error............");
				}
			}
			
			return chain.filter(exchange);
		});
	}
	
	public static class Config{
		 
	}
}
