package vip.ashes.travel.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private String userId;
    /**
     * 角色id
     */
    private Integer roleId;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 电话号
     */
    private String telephone;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String district;
    /**
     * 街道
     */
    private String street;

}
