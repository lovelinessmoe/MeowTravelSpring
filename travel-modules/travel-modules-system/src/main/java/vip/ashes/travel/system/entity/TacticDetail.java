package vip.ashes.travel.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.tactic_detail")
public class TacticDetail implements Serializable {
    public static final String COL_TACTIC_ID = "tactic_id";
    public static final String COL_CONTENT = "content";
    private static final long serialVersionUID = 1L;
    /**
     * 文章的id
     */
    @TableId(value = "tactic_id", type = IdType.INPUT)
    private String tacticId;
    /**
     * 文章的内容
     */
    @TableField(value = "content")
    private String content;
}
