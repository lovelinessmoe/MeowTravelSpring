package vip.ashes.travel.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.`user`")
public class User implements Serializable {
    public static final String COL_USER_ID = "user_id";
    public static final String COL_ROLE_ID = "role_id";
    public static final String COL_USER_NAME = "user_name";
    public static final String COL_TELEPHONE = "telephone";
    public static final String COL_AGE = "age";
    public static final String COL_PASSWORD = "password";
    public static final String COL_EMAIL = "email";
    public static final String COL_AVATAR_URL = "avatar_url";
    public static final String COL_LOCATION_LAT = "location_lat";
    public static final String COL_LOCATION_LNG = "location_lng";
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private String userId;
    /**
     * 角色id
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 昵称
     */
    @TableField(value = "user_name", condition = SqlCondition.LIKE)
    private String userName;
    /**
     * 电话号
     */
    @TableField(value = "telephone", condition = SqlCondition.LIKE)
    private String telephone;
    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;
    /**
     * 登录密码
     */
    @JsonIgnore
    @TableField(value = "password")
    private String password;
    /**
     * 邮箱
     */
    @TableField(value = "email", condition = SqlCondition.LIKE)
    private String email;
    /**
     * 头像地址
     */
    @TableField(value = "avatar_url")
    private String avatarUrl;
    /**
     * 纬度
     */
    @TableField(value = "location_lat")
    private Double locationLat;
    /**
     * 经度
     */
    @TableField(value = "location_lng")
    private Double locationLng;
}
