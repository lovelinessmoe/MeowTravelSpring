package vip.ashes.travel.auth.component;

import lombok.SneakyThrows;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import vip.ashes.travel.auth.entity.SecurityUser;

import java.util.HashMap;
import java.util.Map;

/**
 * JWT内容增强器
 *
 * @author loveliness
 */
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    @SneakyThrows
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        //把用户设置到JWT中
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(securityUser.getUser());
        info.put("user", userJson);
        info.put("auth", securityUser.getAuthorities());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
