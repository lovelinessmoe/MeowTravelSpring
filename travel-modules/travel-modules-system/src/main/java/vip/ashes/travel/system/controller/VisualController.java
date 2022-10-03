package vip.ashes.travel.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.constant.UserConstants;
import vip.ashes.travel.common.redis.service.RedisService;
import vip.ashes.travel.system.entity.Vo.UserLocationVo;
import vip.ashes.travel.system.service.UserService;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("visual")
@AllArgsConstructor
public class VisualController {

    private final UserService userService;
    private final RedisService redisService;


    /**
     * 管理员后台的注册用户可视化
     * 做了redis缓存
     *
     * @return 全国注册人数
     */
    @GetMapping("/userLocation")
    public Result getUserLocation() {
        List<UserLocationVo> userLocation = redisService.getCacheObject(UserConstants.VISUAL_USER_LOCATION);
        if (userLocation == null) {
            userLocation = userService.getUserLocation();
            redisService.setCacheObject(UserConstants.VISUAL_USER_LOCATION, userLocation);
            redisService.expire(UserConstants.VISUAL_USER_LOCATION, 30 * 60);
        }
        return Result.ok().data(userLocation);
    }

}
