package vip.ashes.travel.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.auth.entity.Oauth2TokenDto;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.security.constants.SecurityConstants;

import java.security.Principal;
import java.util.Map;

/**
 * 自定义Oauth2获取令牌接口
 *
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/oauth")
public class AuthController {

    private final TokenEndpoint tokenEndpoint;


    /**
     * Oauth2登录认证
     */
    @PostMapping("/token")
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead(SecurityConstants.TOKEN_PREFIX).build();
        return Result.ok().data(oauth2TokenDto);
    }

}
