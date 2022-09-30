package vip.ashes.travel.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Travel.sponsor")
public class Sponsor implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "sponsor_id", type = IdType.ASSIGN_ID)
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
     * 0 未支付 1已支付 2已打款给用户
     */
    @TableField(value = "type")
    private Boolean type;

    private static final long serialVersionUID = 1L;

    public static final String COL_SPONSOR_ID = "sponsor_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_TACTIC_ID = "tactic_id";

    public static final String COL_MONEY = "money";

    public static final String COL_TYPE = "type";
}
