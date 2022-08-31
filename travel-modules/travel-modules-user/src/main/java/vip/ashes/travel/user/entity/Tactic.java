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

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.tactic")
public class Tactic implements Serializable {
    /**
     * 文章id/英文
     */
    @TableId(value = "tactic_id", type = IdType.INPUT)
    private String tacticId;

    /**
     * 文章名称
     */
    @TableField(value = "title")
    private String title;

    /**
     * 发表用户
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 发布时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 文章简介
     */
    @TableField(value = "short_msg")
    private String shortMsg;

    /**
     * 查看人数
     */
    @TableField(value = "views_count")
    private Integer viewsCount;

    /**
     * 评论人数
     */
    @TableField(value = "comments_count")
    private Integer commentsCount;

    /**
     * 是否是置顶
     */
    @TableField(value = "is_top")
    private Byte isTop;

    /**
     * 文章图片url
     */
    @TableField(value = "img_url")
    private String imgUrl;

    private static final long serialVersionUID = 1L;

    public static final String COL_TACTIC_ID = "tactic_id";

    public static final String COL_TITLE = "title";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_SHORT_MSG = "short_msg";

    public static final String COL_VIEWS_COUNT = "views_count";

    public static final String COL_COMMENTS_COUNT = "comments_count";

    public static final String COL_IS_TOP = "is_top";

    public static final String COL_IMG_URL = "img_url";
}
