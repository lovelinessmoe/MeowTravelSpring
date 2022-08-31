package vip.ashes.travel.user.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.common.core.constant.UserConstants;
import vip.ashes.travel.user.entity.User;
import vip.ashes.travel.user.service.UserService;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User userRegister,
                           @RequestParam String code) {

        if (userService.checkCaptcha(UserConstants.REGISTER_MAIL + userRegister.getEmail(), code)) {
            userService.register(userRegister);
            return Result.ok();
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    @PostMapping("/captcha")
    public Result captcha() {
        return userService.generateCaptcha();
    }

    @PostMapping("/registerMail")
    public Result registerMail(@RequestBody User user,
                               @RequestParam String captchaVerification,
                               @RequestParam String code) {
        if (userService.checkCaptcha(captchaVerification, code)) {
            return userService.registerMail(user);
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }
}
