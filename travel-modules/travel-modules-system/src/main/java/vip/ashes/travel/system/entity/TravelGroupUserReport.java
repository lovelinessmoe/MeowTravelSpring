package vip.ashes.travel.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.travel_group_user_report")
public class TravelGroupUserReport implements Serializable {
    /**
     * 旅游团个人id
     */
    @TableId(value = "group_user_id", type = IdType.INPUT)
    private String groupUserId;

    /**
     * 报告提交时间
     */
    @TableField(value = "report_time")
    private Date reportTime;

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

    private static final long serialVersionUID = 1L;

    public static final String COL_GROUP_USER_ID = "group_user_id";

    public static final String COL_REPORT_TIME = "report_time";

    public static final String COL_LOCATION_LAT = "location_lat";

    public static final String COL_LOCATION_LNG = "location_lng";
}