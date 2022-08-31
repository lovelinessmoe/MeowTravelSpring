package vip.ashes.travel.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 旅游团
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.travel_group")
public class TravelGroup implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "group_id", type = IdType.INPUT)
    private String groupId;

    /**
     * 旅游团最大人数
     */
    @TableField(value = "group_num")
    private Integer groupNum;

    /**
     * 旅游团名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 团创建时间
     */
    @TableField(value = "creata_time")
    private Date creataTime;

    /**
     * 是否开启健康打卡功能
     */
    @TableField(value = "is_open_report")
    private Boolean isOpenReport;

    /**
     * 健康打卡开启时间
     */
    @TableField(value = "report_open_time")
    private Date reportOpenTime;

    /**
     * 健康打卡结束时间
     */
    @TableField(value = "report_end_time")
    private Date reportEndTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_GROUP_ID = "group_id";

    public static final String COL_GROUP_NUM = "group_num";

    public static final String COL_GROUP_NAME = "group_name";

    public static final String COL_CREATA_TIME = "creata_time";

    public static final String COL_IS_OPEN_REPORT = "is_open_report";

    public static final String COL_REPORT_OPEN_TIME = "report_open_time";

    public static final String COL_REPORT_END_TIME = "report_end_time";
}
