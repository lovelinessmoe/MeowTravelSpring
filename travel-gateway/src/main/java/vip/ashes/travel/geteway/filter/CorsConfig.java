package vip.ashes.travel.geteway.filter;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author loveliness
 * 日你妈，Security在解决OPTIONS时抢先一步拦截了请求，导致请求变成了CORS错误
 * 但是有的接口相同的还是能请求到的，不知道为什么，一直以为是CORS配置错误
 * 之前使用的了gateway的yml配置，由于优先级不如Security的，一直不能用
 * 这里使用了Ordered进行优先级排序
 * @see Ordered#HIGHEST_PRECEDENCE
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsConfig implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange swe, WebFilterChain wfc) {
        ServerHttpRequest request = swe.getRequest();
        if (CorsUtils.isCorsRequest(request)) {
            ServerHttpResponse response = swe.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("Access-Control-Allow-Methods", "*");
            headers.add("Access-Control-Max-Age", "3600");
            headers.add("Access-Control-Allow-Headers", "*");
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
        }
        return wfc.filter(swe);
    }
}
