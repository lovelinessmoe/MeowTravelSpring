package vip.ashes.travel.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.auth.entity.Oauth2TokenDto;
import vip.ashes.travel.auth.entity.Role;
import vip.ashes.travel.auth.entity.User;
import vip.ashes.travel.auth.service.AuthRoleService;
import vip.ashes.travel.auth.service.AuthUserService;
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
    private final AuthUserService authUserService;
    private final ObjectMapper objectMapper;
    private final AuthRoleService authRoleService;

    /**
     * Oauth2登录认证
     */
    @PostMapping("/token")
    public Result postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException, JsonProcessingException {
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(principal, parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(SecurityConstants.TOKEN_PREFIX + oAuth2AccessToken.getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn()).build();

        //UserInfo
        String email = parameters.get("email");
        QueryWrapper<User> eq = new QueryWrapper<User>().eq(User.COL_EMAIL, email);
        User user = authUserService.getOne(eq);
        user.setPassword(null);
        Map<String, Object> map = objectMapper.readValue(objectMapper.writeValueAsString(user), Map.class);
        map.put("auth", oauth2TokenDto);

        //ROLE
        Role roleById = authRoleService.getById(user.getRoleId());
        String role = roleById.getRoleNameEn();
        map.put("role", SecurityConstants.AUTHORITY_PREFIX + role);

        return Result.ok().data(map);
    }
}
