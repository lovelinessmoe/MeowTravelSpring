package vip.ashes.travel.system.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.constant.UserConstants;
import vip.ashes.travel.common.redis.service.RedisService;
import vip.ashes.travel.system.entity.Vo.DictVO;
import vip.ashes.travel.system.service.DictService;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/dict")
@AllArgsConstructor
public class DictController {

    private final DictService dictService;
    private final RedisService redisService;

    /**
     * 通过code获取字典
     *
     * @return 结构
     * dicData: [
     * {
     * label: "阴性",
     * value: 0
     * }, {
     * label: "阳性",
     * value: 1
     * },
     * ]
     */
    @GetMapping("/getDictByCode")
    public Result getDictByCode(String code) {
        List<DictVO> dictByCode = redisService.getCacheObject(UserConstants.DICT + code);
        if (dictByCode == null) {
            dictByCode = dictService.getDictByCode(code);
            redisService.setCacheObject(UserConstants.DICT + code, dictByCode);
        }
        return Result.ok().data(dictByCode);
    }
}
