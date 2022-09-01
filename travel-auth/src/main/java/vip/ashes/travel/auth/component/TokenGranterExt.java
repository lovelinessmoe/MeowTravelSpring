package vip.ashes.travel.auth.component;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import vip.ashes.travel.common.redis.service.RedisService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 重写TokenEnhancerChain进行添加CaptchaTokenGranter
 *
 * @author loveliness
 */
public class TokenGranterExt extends TokenEnhancerChain {
    public static TokenGranter getTokenGranter(
            final AuthenticationManager authenticationManager,
            final AuthorizationServerEndpointsConfigurer endpointsConfigurer,
            RedisService redisService
    ) {
        //  默认tokenGranter集合 security 自带的
        List<TokenGranter> granters = new ArrayList<>(Collections.singletonList(endpointsConfigurer.getTokenGranter()));
        //添加验证码
        granters.add(new CaptchaTokenGranter(authenticationManager, redisService, endpointsConfigurer.getTokenServices(), endpointsConfigurer.getClientDetailsService(), endpointsConfigurer.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }
}
