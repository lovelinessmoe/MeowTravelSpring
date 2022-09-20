package vip.ashes.travel.map.controller;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
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

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/map")
public class MapController {

    @Value("${map.ak}")
    @Resource
    private String ak;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private BaiDuMapConverter baiDuMapConverter;

    @Resource
    private BaiduPoiService baiduPoiService;

    @SneakyThrows
    @GetMapping("/suggestion")
    public Result getSearchSuggestion(String query, String region, String tag) {
        String url = "https://api.map.baidu.com/place/v2/suggestion?" +
                "query=" + query + " " + tag +
                "&region=" + region +
                "&output=json" +
                "&ak=" + ak;
        String content = HttpUtil.get(url);
        BaiDuSuggVo baiDuSuggVo = objectMapper.readValue(content, BaiDuSuggVo.class);
        // 0是百度地图的正确，我是200
        return Result.ok().data(baiDuSuggVo.getResult())
//                .message(baiDuSuggVo.getMessage())
                .code(baiDuSuggVo.getStatus() == 0 ? ResultCode.SUCCESS.getCode() : ResultCode.COMMON_FAIL.getCode());
    }


    @SneakyThrows
    @GetMapping("/getDetailById/{uid}")
    public Result getDetailById(@PathVariable String uid) {
        String url = "https://api.map.baidu.com/place/v2/detail?" +
                "uid=" + uid +
                "&scope=2" +
                "&output=json" +
                "&ak=" + ak;
        String content = HttpUtil.get(url);
        BaiDuPoiDetailVo baiDuPoiDetailVo = objectMapper.readValue(content, BaiDuPoiDetailVo.class);
        return Result.ok().data(baiDuPoiDetailVo.getResult());
    }


    @SneakyThrows
    @GetMapping("/search")
    public Result searchLocation(String query, String location, String tag, String region) {
        ArrayList<BaiDuSearchVo.ResultsDTO> res = new ArrayList<>();
        for (int pageNum = 0; pageNum <= 5; pageNum++) {
            String url = "https://api.map.baidu.com/place/v2/search?" +
                    //虽然存在tag分类搜索，但我的建议还是在query里进行关键字检索
                    "query=" + query + " " + tag +
//                "&location=" + location +
                    "&region=" + region +
                    "&page_num=" + pageNum +
                    "&page_size=20" +
                    "&radius=3000" +
                    "&output=json" +
//                不行了，这个需要企业才能申请开通
//                "&photo_show=true" +
                    "&ak=" + ak;

            String content = HttpUtil.get(url);
            BaiDuSearchVo baiDuSearchVo = objectMapper.readValue(content, BaiDuSearchVo.class);
            List<BaiDuSearchVo.ResultsDTO> results = baiDuSearchVo.getResults();
            res.addAll(results);
        }

        // 先插入数据库中
        List<BaiduPoi> baiduPois = baiDuMapConverter.getPoiListBySearchVoList(res);
        boolean b = baiduPoiService.saveOrUpdateBatch(baiduPois);

        // 再从数据库中通过地图的id获取数据库内信息
        List<BaiduPoi> poisByIds = baiduPoiService.getPoisByIds(res);
        return Result.ok().data(poisByIds);
    }
}
