package vip.ashes.travel.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 旅游团
 *
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.travel_group")
public class TravelGroup implements Serializable {
    public static final String COL_GROUP_ID = "group_id";
    public static final String COL_GROUP_NUM = "group_num";
    public static final String COL_GROUP_NAME = "group_name";
    public static final String COL_GROUP_IMG = "group_img";
    public static final String COL_GROUP_SHORT_MSG = "group_short_msg";
    public static final String COL_CREATA_TIME = "creata_time";
    public static final String COL_IS_OPEN_REPORT = "is_open_report";
    public static final String COL_REPORT_OPEN_TIME = "report_open_time";
    public static final String COL_REPORT_END_TIME = "report_end_time";
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "group_id", type = IdType.ASSIGN_ID)
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
     * 旅游团图片
     */
    @TableField(value = "group_img")
    private String groupImg;
    /**
     * 旅游团介绍
     */
    @TableField(value = "group_short_msg")
    private String groupShortMsg;
    /**
     * 团创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 是否开启健康打卡功能
     */
    @TableField(value = "is_open_report")
    private Boolean isOpenReport;
}
