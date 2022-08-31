package vip.ashes.travel.auth.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import vip.ashes.travel.common.core.Result;

/**
 * 全局处理Oauth2抛出的异常
 *
 * @author loveliness
 */
@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public Result handleOauth2(OAuth2Exception e) {
        return Result.error().message(e.getMessage());
    }
}
