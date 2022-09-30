package vip.ashes.travel.user.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 组队内部人员
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName(value = "Travel.travel_group_user")
public class TravelGroupUser implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "group_user_id", type = IdType.ASSIGN_ID)
    private String groupUserId;

    /**
     * 组团ID
     */
    @TableField(value = "group_id")
    private String groupId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 是不是创建者
     */
    @TableField(value = "is_leader")
    private Boolean isLeader;

    /**
     * 进团时间
     */
    @TableField(value = "add_time",fill = FieldFill.INSERT)
    private Date addTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_GROUP_USER_ID = "group_user_id";

    public static final String COL_GROUP_ID = "group_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_IS_LEADER = "is_leader";

    public static final String COL_ADD_TIME = "add_time";
}
