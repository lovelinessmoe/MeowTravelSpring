package vip.ashes.travel.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 赞助
 *
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.sponsor")
public class Sponsor implements Serializable {
    public static final String COL_SPONSOR_ID = "sponsor_id";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_TACTIC_ID = "tactic_id";
    public static final String COL_MONEY = "money";
    public static final String COL_TYPE = "type";
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(value = "sponsor_id", type = IdType.INPUT)
    private String sponsorId;
    /**
     * 赞助的用户id
     */
    @TableField(value = "user_id")
    private String userId;
    /**
     * 攻略id
     */
    @TableField(value = "tactic_id")
    private String tacticId;
    /**
     * 赞助数量
     */
    @TableField(value = "money")
    private BigDecimal money;
    /**
     * 0 未支付 1已支付
     */
    @TableField(value = "type")
    private Boolean type;
}
