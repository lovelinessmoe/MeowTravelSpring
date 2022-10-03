package vip.ashes.travel.user.entity.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TacticDetailVo {
    /**
     * 文章的id
     */
    private String tacticId;

    /**
     * 文章的内容
     */
    private String content;

    /**
     * 发表用户
     */
    private String userId;

    /**
     * 文章名称
     */
    private String title;

    /**
     * 博客简介
     */
    private String shortMsg;

    /**
     * 文章图片url
     */
    private String imgUrl;

    /**
     * poi的唯一标示，可用于详情检索
     */
    private String uid;
}
