package vip.ashes.travel.user.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.`comment`")
public class Comment implements Serializable {
    public static final String COL_COMMENT_ID = "comment_id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_CONTENT = "content";
    public static final String COL_CREATE_TIME = "create_time";
    public static final String COL_P_ID = "p_id";
    public static final String COL_LEVEL = "level";
    public static final String COL_TACTIC_ID = "tactic_id";
    private static final long serialVersionUID = 1L;
    @TableId(value = "comment_id", type = IdType.ASSIGN_UUID)
    private String commentId;
    /**
     * 评论人
     */
    @TableField(value = "user_id")
    private String userId;
    /**
     * 评论内容
     */
    @TableField(value = "content")
    private String content;
    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;
    /**
     * 父id
     */
    @TableField(value = "p_id")
    private String pId;
    /**
     * 层级
     */
    @TableField(value = "level")
    private String level;
    /**
     * 攻略id
     */
    @TableField(value = "tactic_id")
    private String tacticId;
}
