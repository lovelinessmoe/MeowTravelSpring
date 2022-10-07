package vip.ashes.travel.user.entity.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 查看旅游团打卡信息，以及成员信息
 *
 * @author loveliness
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckGroupInfo {
    private String userId;
    private String userName;
    private String telephone;
    private String email;
    private String avatarUrl;
    private Date addTime;
    private boolean isLeader;
    private BigDecimal locationLat;
    private BigDecimal locationLng;
    private String reportTime;

}
