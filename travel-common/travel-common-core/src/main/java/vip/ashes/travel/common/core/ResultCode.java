package vip.ashes.travel.common.core;

/**
 * @author loveliness
 * @Description: 返回码定义
 * 规定:
 * #200表示成功
 * #后面对什么的操作自己在这里注明就行了
 */
public enum ResultCode {
    /* 成功 */
    SUCCESS(200, null),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "用户名或密码不正确"),
    USER_CREDENTIALS_EXPIRED(2004, "该账户的登录凭证已过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "该账号已被锁定"),
    USER_CAPTCHA_CODE_ERR(2007, "验证码错误,请重新输入验证码"),
    EMAIL_FORMAT_ERROR(2008, "邮箱格式错误"),
    USER_ACCOUNT_EMAIL_ALREADY_EXIST(2009, "账号已存在,请更换邮箱"),
    CAPTCHA_NULL(2010, "验证码不能为空"),
    CAPTCHA_NOT_EXIST(2011, "验证码不存在,请刷新验证码"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),


    /*运行时异常*/
    ARITHMETIC_EXCEPTION(9001, "算数异常");


    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
