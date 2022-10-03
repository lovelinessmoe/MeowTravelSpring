package vip.ashes.travel.user.entity.Vo;


import lombok.Data;

import java.util.Date;

/**
 * @author loveliness
 */
@Data
public class GroupInfoVo {
    /**
     * 主键
     */
    private String groupId;

    /**
     * 旅游团最大人数
     */
    private Integer groupNum;

    /**
     * 旅游团名称
     */
    private String groupName;

    /**
     * 旅游团图片
     */
    private String groupImg;

    /**
     * 旅游团介绍
     */
    private String groupShortMsg;

    /**
     * 团创建时间
     */
    private Date createTime;

    /**
     * 是否开启健康打卡功能
     */
    private Boolean isOpenReport;

    /**
     * 团当前人数
     */
    private Integer nowNum;
}
