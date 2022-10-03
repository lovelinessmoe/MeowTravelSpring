package vip.ashes.travel.map.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.map.entity.BaiduPoi;
import vip.ashes.travel.map.service.impl.BaiduPoiService;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/baidu")
public class BaiduPoiController {
    private final BaiduPoiService baiduPoiService;

    /**
     * 攻略获取物理地点时补全建议
     *
     * @param key 关键字
     * @return 建议的List
     */
    @GetMapping("getBaiDuPoiSuggestApi")
    public Result getBaiDuPoiSuggestApi(String key) {
        List<BaiduPoi> baiDuPoiSuggestApi = baiduPoiService.getBaiDuPoiSuggestApi(key);
        return Result.ok().data(baiDuPoiSuggestApi);
    }


    /**
     * 分页 百度地图poi
     */
    @GetMapping("/list")
    public Result list(BaiduPoi baiduPoi, PageDTO<BaiduPoi> query) {
        PageDTO<BaiduPoi> page = baiduPoiService.getPageList(baiduPoi, query);
//        PageDTO<BaiduPoi> page = baiduPoiService.page(query, baiduPoiQueryWrapper);
        return Result.ok().data(page);
    }

    /**
     * 百度地图poi 保存图片
     */
    @GetMapping("/savePoiImgUrl")
    public Result savePoiImgUrl(String imgUrl, String uid) {
        UpdateWrapper<BaiduPoi> updateWrapper = new UpdateWrapper<BaiduPoi>();
        updateWrapper.set(BaiduPoi.COL_POI_PHOTO_URL, imgUrl)
                .eq(BaiduPoi.COL_UID, uid);
        boolean update = baiduPoiService.update(updateWrapper);
        if (update) {
            return Result.ok();
        } else {
            return Result.error();
        }
    }
}
