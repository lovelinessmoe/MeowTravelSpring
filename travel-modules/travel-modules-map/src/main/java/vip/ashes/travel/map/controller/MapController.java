package vip.ashes.travel.map.controller;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.map.entity.Vo.BaiDuSearchVo;
import vip.ashes.travel.map.entity.Vo.BaiDuSuggVo;

import javax.annotation.Resource;

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
    @GetMapping("/search")
    public Result searchLocation(String query, String location, String tag) {
        String url = "https://api.map.baidu.com/place/v2/search?" +
                //虽然存在tag分类搜索，但我的建议还是在query里进行关键字检索
                "query=" + query + " " + tag +
                "&location=" + location +
                "&radius=2000" +
                "&output=json" +
                "&ak=" + ak;
        String content = HttpUtil.get(url);
        System.out.println(content);
        BaiDuSearchVo baiDuSearchVo = objectMapper.readValue(content, BaiDuSearchVo.class);

        return Result.ok().data(baiDuSearchVo.getResults());
    }
}
