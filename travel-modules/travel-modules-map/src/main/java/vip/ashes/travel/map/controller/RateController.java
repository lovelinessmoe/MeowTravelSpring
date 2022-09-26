package vip.ashes.travel.map.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.map.entity.User;
import vip.ashes.travel.map.entity.UserRatePoi;
import vip.ashes.travel.map.service.impl.UserRatePoiService;
import vip.ashes.travel.map.util.LoginUserUtil;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/rate")
public class RateController {
    private final UserRatePoiService userRatePoiService;
    private final LoginUserUtil loginUserUtil;

    /**
     * 发起评分
     * @param rate 评分多少
     * @param uid poi
     * @return 成功与否
     */
    @PostMapping("submitRate")
    public Result submitRate(Double rate, String uid) {
        User currentUser = loginUserUtil.getCurrentUser();
        UserRatePoi userRatePoi = new UserRatePoi(uid, currentUser.getUserId(), rate);
        int i = userRatePoiService.submitRate(userRatePoi);
        if (i > 0) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }

    /**
     * 获取poi的评分
     * @param uid poiID
     * @return 评分
     */
    @PostMapping("getRate")
    public Result getRate(String uid) {
        User currentUser = loginUserUtil.getCurrentUser();
        UserRatePoi userRatePoi = new UserRatePoi(uid, currentUser.getUserId(), null);
        QueryWrapper<UserRatePoi> userRatePoiQueryWrapper = new QueryWrapper<>(userRatePoi);
        UserRatePoi one = userRatePoiService.getOne(userRatePoiQueryWrapper);
        // 空指针异常
        if (one == null) {
            one = new UserRatePoi();
        }
        return Result.ok().data(one.getRate());
    }
}
