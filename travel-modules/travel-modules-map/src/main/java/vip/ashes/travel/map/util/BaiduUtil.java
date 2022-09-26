package vip.ashes.travel.map.util;

import cn.hutool.http.HttpUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import vip.ashes.travel.map.entity.Vo.BaiDuPoiDetailVo;
import vip.ashes.travel.map.entity.Vo.BaiDuSearchVo;
import vip.ashes.travel.map.entity.Vo.BaiDuSuggVo;

/**
 * @author loveliness
 */
@AllArgsConstructor
public class BaiduUtil {
    private final String ak;
    private final ObjectMapper objectMapper;

    public BaiDuSuggVo getSearchSuggestion(String query, String region, String tag) throws JsonProcessingException {
        String url = "https://api.map.baidu.com/place/v2/suggestion?" +
                "query=" + query + " " + tag +
                "&region=" + region +
                "&output=json" +
                "&ak=" + ak;
        String content = HttpUtil.get(url);

        return objectMapper.readValue(content, BaiDuSuggVo.class);
    }


    public BaiDuPoiDetailVo getDetailById(String uid) throws JsonProcessingException {
        String url = "https://api.map.baidu.com/place/v2/detail?" +
                "uid=" + uid +
                "&scope=2" +
                "&output=json" +
                "&ak=" + ak;
        String content = HttpUtil.get(url);
        return objectMapper.readValue(content, BaiDuPoiDetailVo.class);
    }


    public BaiDuSearchVo searchLocation(String query, String location, String tag, String region,int pageNum) throws JsonProcessingException {
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
        return objectMapper.readValue(content, BaiDuSearchVo.class);
    }


}
