package vip.ashes.travel.auth.component;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.common.redis.service.RedisService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 图片验证码类型 tokenGranter
 *
 * @author loveliness
 */
public class CaptchaTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "captcha";
    private final AuthenticationManager authenticationManager;
    private RedisService redisService;

    public CaptchaTokenGranter(AuthenticationManager authenticationManager,
                               RedisService redisService,
                               AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService,
                               OAuth2RequestFactory requestFactory) {
        this(authenticationManager, tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
        this.redisService = redisService;
    }

    protected CaptchaTokenGranter(AuthenticationManager authenticationManager,
                                  AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService,
                                  OAuth2RequestFactory requestFactory,
                                  String grantType) {
        //调用父类 接管GRANT_TYPE类型
        super(tokenServices, clientDetailsService, requestFactory, grantType);
        this.authenticationManager = authenticationManager;

    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
        Map<String, String> parameters = new LinkedHashMap<>(tokenRequest.getRequestParameters());
        String username = parameters.get("email");
        String password = parameters.get("password");
        String code = parameters.get("code");
        String captchaVerification = parameters.get("captchaVerification");
        if (StringUtils.isEmpty(code)) {
            throw new InvalidGrantException(ResultCode.CAPTCHA_NULL.getMessage());
        }
        String o = redisService.getCacheObject(captchaVerification);
        if (o == null) {
            throw new InvalidGrantException(ResultCode.CAPTCHA_NOT_EXIST.getMessage());
        }
        if (!o.equals(code)) {
            throw new InvalidGrantException(ResultCode.USER_CAPTCHA_CODE_ERR.getMessage());
        }
        // Protect from downstream leaks of password
        parameters.remove("password");
        //从redis里删除验证码的缓存
        redisService.deleteObject(captchaVerification);
        Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        try {
            userAuth = authenticationManager.authenticate(userAuth);
        } catch (AccountStatusException ase) {
            //covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
            throw new InvalidGrantException(ase.getMessage());
        } catch (BadCredentialsException e) {
            // If the username/password are wrong the spec says we should send 400/invalid grant
            throw new InvalidGrantException(e.getMessage());
        }
        if (userAuth == null || !userAuth.isAuthenticated()) {
            throw new InvalidGrantException("Could not authenticate user: " + username);
        }

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }
}
