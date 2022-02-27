package com.zlf.iot.gatewayserver.config;

import com.zlf.iot.gatewayserver.common.TokenInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.logging.Logger;

import static org.apache.commons.lang.StringUtils.substringAfter;

@Component
public class AuthClient {


    private RestTemplate restTemplate = new RestTemplate();

    @Value("http://localhost:6001/oauth/check_token")
    private String checkTokenUrl;

    public boolean hasPermissionControl(String url) {
        return url.startsWith("/product")||
                url.startsWith("/device")
                ;
    }

    public boolean accessable(ServerWebExchange exchange) {

        ServerHttpRequest request = exchange.getRequest();

        String authHeader = exchange.getRequest().getHeaders().getFirst("token");
        String token = substringAfter(authHeader, "Bearer ");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(checkTokenUrl).queryParam("token", token);
        URI url = builder.build().encode().toUri();

        HttpEntity<?> entity = new HttpEntity<>(request.getHeaders());

        try {
            ResponseEntity<TokenInfo> response = restTemplate.exchange(url, HttpMethod.GET, entity, TokenInfo.class);
          //  System.out.println("Res"+response.getBody().toString());
            return response.getBody() != null && response.getBody().isActive();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
