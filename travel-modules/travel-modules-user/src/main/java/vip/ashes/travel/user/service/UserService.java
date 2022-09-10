package vip.ashes.travel.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.User;

public interface UserService extends IService<User> {
    /**
     * 比对数据库的密码和传进来的密码加密后是否相同
     *
     * @param currentPassword 原来的密码
     * @param password        加密后的密码
     * @return 相同t 不相同f
     */
    boolean check(String currentPassword, String password);

    /**
     * 用户注册
     *
     * @param userRegister 注册的用户实体
     * @return 失败-1 成功影响的条数1
     */
    int register(User userRegister);

    /**
     * 查看redis存的验证码是否正确
     *
     * @param captchaVerification redis 的 key
     * @param code                输入的验证码
     * @return 正确true 错误false
     */
    boolean checkCaptcha(String captchaVerification, String code);

    /**
     * 生成验证码 存入redis
     *
     * @return 包含验证码票证的结果
     */
    Result generateCaptcha();

    /**
     * 给用户发送验证码
     * 注册时的方法
     *
     * @param user 用户实体 主要使用邮箱和用户名字段
     * @return Result
     */
    Result registerMail(User user);

    /**
     * 给用户发送验证码
     * 修改用户信息时的方法
     *
     * @param userId 用户id
     * @return Result
     */
    Result modifyInfMail(String userId);

}
