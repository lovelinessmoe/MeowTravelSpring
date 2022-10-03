package vip.ashes.travel.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.common.core.constant.UserConstants;
import vip.ashes.travel.user.entity.User;
import vip.ashes.travel.user.service.UserService;
import vip.ashes.travel.user.utils.LoginUserUtil;

import java.util.Collection;
import java.util.Map;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final LoginUserUtil loginUserUtil;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 获取个人信息
     *
     * @return 个人信息
     */
    @SneakyThrows
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        User currentUser = loginUserUtil.getCurrentUser();
        Collection currentUserAuth = loginUserUtil.getCurrentUserAuth();
        currentUser.setPassword(null);
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(objectMapper.writeValueAsString(currentUser), Map.class);
        map.put("auth", currentUserAuth.toString());
        return Result.ok().data(map);
    }

    /**
     * 获取邮箱验证码,用于修改信息时
     *
     * @param captchaVerification 验证码随机
     * @param code                验证码
     * @return
     */
    @PostMapping("/modifyInfMail")
    public Result modifyInfMail(@RequestParam String captchaVerification, @RequestParam String code) {
        User currentUser = loginUserUtil.getCurrentUser();
        if (userService.checkCaptcha(captchaVerification, code)) {
            return userService.modifyInfMail(currentUser.getUserId());
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }

    /**
     * 修改用户信息
     *
     * @param user     要更改的用户信息
     * @param mailCode 随机生成的鉴权码，邮箱里的
     * @return
     */
    @PostMapping("/updateUserInfo")
    public Result updateUserInfo(@RequestBody User user, @RequestParam String mailCode) {
        //通过请求头的id获取用户，增加安全性
        String userId = loginUserUtil.getCurrentUser().getUserId();
        User user1 = userService.getById(userId);
        if (userService.checkCaptcha(UserConstants.MODIFY_INFORMATION_MAIL + user1.getEmail(), mailCode)) {
            //防止通过接口修改邮箱
            user.setEmail(null);
            //防止通过接口修改角色
            user.setRoleId(null);
            user.setUserId(userId);
            if (!ObjectUtils.isEmpty(user.getPassword())) {
                //密码进行加密
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }
            if (userService.updateById(user)) {
                return Result.ok().message("修改成功");
            } else {
                return Result.error().message("修改失败");
            }
        } else {
            return Result.RCode(false, ResultCode.USER_CAPTCHA_CODE_ERR);
        }
    }
}
