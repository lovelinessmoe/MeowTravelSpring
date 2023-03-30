package vip.ashes.travel.map.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.map.entity.BaiduPoi;
import vip.ashes.travel.map.entity.Vo.BaiDuPoiDetailVo;
import vip.ashes.travel.map.entity.Vo.BaiDuSearchVo;
import vip.ashes.travel.map.entity.Vo.BaiDuSuggVo;
import vip.ashes.travel.map.entity.converter.BaiDuMapConverter;
import vip.ashes.travel.map.service.impl.BaiduPoiService;
import vip.ashes.travel.map.util.BaiduUtil;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RefreshScope
@RequestMapping("/map")
public class MapController {
    private final BaiDuMapConverter baiDuMapConverter;
    private final BaiduPoiService baiduPoiService;
    private final BaiduUtil baiduUtil;

    /**
     * 获取百度地点建议
     *
     * @param query  search-key
     * @param region 地区
     * @param tag    tag
     * @return 建议
     */
    @SneakyThrows
    @GetMapping("/suggestion")
    public Result getSearchSuggestion(String query, String region, String tag) {
        BaiDuSuggVo searchSuggestion = baiduUtil.getSearchSuggestion(query, region, tag);
        // 0是百度地图的正确，我是200
        return Result.ok().data(searchSuggestion.getResult())
//                .message(searchSuggestion.getMessage())
                .code(searchSuggestion.getStatus() == 0 ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode());
    }


    /**
     * 百度地图获取poi的详细信息
     *
     * @param uid poiID
     * @return 详细信息
     */
    @SneakyThrows
    @GetMapping("/getDetailById/{uid}")
    public Result getDetailById(@PathVariable String uid) {
        BaiDuPoiDetailVo detailById = baiduUtil.getDetailById(uid);

        BaiduPoi poiByDetailVo = baiDuMapConverter.getPoiByDetailVo(detailById.getResult());
        poiByDetailVo.setType((byte) 0);
        // 更新数据库poi信息为百度地图最新信息
        baiduPoiService.saveOrUpdate(poiByDetailVo);
        // 获取包含数据库其他字段的信息
        BaiduPoi byId = baiduPoiService.getById(uid);

        return Result.ok().data(byId);
    }


    /**
     * 搜索地点
     *
     * @param query    search-key
     * @param location 位置
     * @param tag      tag
     * @param region   地域
     * @return 数据的信息
     */
    @SneakyThrows
    @GetMapping("/search")
    public Result searchLocation(String query, String location, String tag, String region) {
//        ArrayList<BaiDuSearchVo.ResultsDTO> res = new ArrayList<>();
        /*for (int pageNum = 0; pageNum <= 5; pageNum++) {
            BaiDuSearchVo baiDuSearchVo = baiduUtil.searchLocation(query, location, tag, region, pageNum);
            List<BaiDuSearchVo.ResultsDTO> results = baiDuSearchVo.getResults();
            res.addAll(results);
        }*/
        BaiDuSearchVo baiDuSearchVo = baiduUtil.searchLocation(query, location, tag, region, 0);
        // 先插入数据库中
        List<BaiduPoi> baiduPois = baiDuMapConverter.getPoiListBySearchVoList(baiDuSearchVo.getResults());

        baiduPois.forEach(item -> item.setType("酒店".equals(tag) ? (byte) 1 : (byte) 0));
        baiduPoiService.saveOrUpdateBatch(baiduPois);

        // 再从数据库中通过地图的id获取数据库内信息
        List<BaiduPoi> poisByIds = baiduPoiService.getPoisByIds(baiDuSearchVo.getResults());
        return Result.ok().data(poisByIds);
    }
}
