package vip.ashes.travel.geteway.authorization;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import reactor.core.publisher.Mono;
import vip.ashes.travel.common.redis.constant.RedisConstant;
import vip.ashes.travel.common.redis.service.RedisService;
import vip.ashes.travel.common.security.constants.SecurityConstants;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 鉴权管理器，用于判断是否有资源的访问权限
 *
 * @author loveliness
 */
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Resource
    private RedisService redisService;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        //从Redis中获取当前路径可访问角色列表
        URI uri = authorizationContext.getExchange().getRequest().getURI();
        Map<String, List> map = redisService.getCacheMap(RedisConstant.RESOURCE_ROLES_MAP);
        List<String> authorities = null;
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        for (Map.Entry<String, List> next : map.entrySet()) {
            if (antPathMatcher.match(next.getKey(), uri.getPath())) {
                authorities = next.getValue();
            }
        }
        authorities = authorities.stream().map(i -> i = SecurityConstants.AUTHORITY_PREFIX + i).collect(Collectors.toList());
        //认证通过且角色匹配的用户可访问当前路径
        return mono
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorities::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

}
