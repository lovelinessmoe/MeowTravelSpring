package vip.ashes.travel.map.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.redis.constant.RedisConstant;
import vip.ashes.travel.common.redis.service.RedisService;
import vip.ashes.travel.map.entity.BaiduPoi;
import vip.ashes.travel.map.entity.User;
import vip.ashes.travel.map.entity.UserRatePoi;
import vip.ashes.travel.map.service.impl.BaiduPoiService;
import vip.ashes.travel.map.service.impl.UserRatePoiService;
import vip.ashes.travel.map.util.LoginUserUtil;
import vip.ashes.travel.map.util.RecommendCore;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/recommend")
@AllArgsConstructor
public class RecommendController {
    private final RecommendCore recommendCore = new RecommendCore();
    private final UserRatePoiService userRatePoiService;
    private final BaiduPoiService baiduPoiService;
    private final LoginUserUtil loginUserUtil;
    private final RedisService redisService;

    /**
     * 给用户推荐景点
     * @return 6条以上推荐的景点
     */
    @GetMapping("/getUserLikeSights")
    public Result getUserLikeSights() {
        User currentUser = loginUserUtil.getCurrentUser();
        List<BaiduPoi> baiduPois;
        baiduPois = redisService.getCacheList(RedisConstant.USER_RECOMMEND + currentUser.getUserId());
        if (baiduPois.isEmpty()) {
            List<UserRatePoi> allSightsPoiRate = userRatePoiService.getAllSightsPoiRate();
            List<String> recommend = recommendCore.recommend(currentUser.getUserId(), allSightsPoiRate);
            if (!recommend.isEmpty()) {
                baiduPois = baiduPoiService.listByIds(recommend);
            }
            // 不足6个凑数
            int recommendSize = 6;
            if (baiduPois.size() < recommendSize) {
                List<BaiduPoi> sizePoi = baiduPoiService.getSizePoi(recommendSize - baiduPois.size(), baiduPois);
                baiduPois.addAll(sizePoi);
            }
            redisService.setCacheList(RedisConstant.USER_RECOMMEND + currentUser.getUserId(), baiduPois);
            redisService.expire(RedisConstant.USER_RECOMMEND + currentUser.getUserId(), 30 * 60);
        }

        return Result.ok().data(baiduPois);
    }

}
