package vip.ashes.travel.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.travel_group_user_report")
public class TravelGroupUserReport implements Serializable {
    public static final String COL_GROUP_USER_ID = "group_user_id";
    public static final String COL_REPORT_TIME = "report_time";
    public static final String COL_LOCATION_LAT = "location_lat";
    public static final String COL_LOCATION_LNG = "location_lng";
    private static final long serialVersionUID = 1L;
    /**
     * 旅游团个人id
     */
    @TableId(value = "group_user_id", type = IdType.INPUT)
    private String groupUserId;
    /**
     * 报告提交时间
     */
    @TableField(value = "report_time", fill = FieldFill.INSERT)
    private Date reportTime;
    /**
     * 纬度
     */
    @TableField(value = "location_lat")
    private BigDecimal locationLat;
    /**
     * 经度
     */
    @TableField(value = "location_lng")
    private BigDecimal locationLng;
}
