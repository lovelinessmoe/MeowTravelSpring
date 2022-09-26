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

    /**
     * 注册
     * @param userRegister 注册用户信息
     * @param code 邮箱验证码
     * @return 提示
     */
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

    /**
     * 获取验证码
     * @return k-v验证码
     */
    @PostMapping("/captcha")
    public Result captcha() {
        return userService.generateCaptcha();
    }


    /**
     * 发送邮箱验证码
     * @param user 用户信息 主要使用名字和邮箱
     * @param captchaVerification 验证码key
     * @param code 验证码val
     * @return 成功
     */
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
