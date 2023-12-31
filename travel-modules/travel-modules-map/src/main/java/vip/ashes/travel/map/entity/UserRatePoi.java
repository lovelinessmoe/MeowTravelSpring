package vip.ashes.travel.map.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.user_rate_poi")
public class UserRatePoi implements Serializable {
    public static final String COL_BAIDU_POI_UID = "baidu_poi_uid";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_RATE = "rate";
    private static final long serialVersionUID = 1L;
    /**
     * poi的唯一标示，可用于详情检索
     */
    @TableField(value = "baidu_poi_uid")
    private String baiduPoiUid;
    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;
    /**
     * 评分
     */
    @TableField(value = "rate")
    private Double rate;
}
