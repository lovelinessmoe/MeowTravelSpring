package vip.ashes.travel.system.entity.Vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author loveliness
 */
@Data
public class SponsorVo {

    /**
     * 文章名称
     */
    private String title;
    /**
     * 昵称
     */
    private String userName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 0 未支付 1已支付
     */
    private Boolean type;
    /**
     * 赞助数量
     */
    private BigDecimal money;

}
