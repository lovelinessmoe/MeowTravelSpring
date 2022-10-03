package vip.ashes.travel.map.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.map.entity.BaiduPoi;
import vip.ashes.travel.map.entity.Vo.BaiDuSearchVo;

import java.util.List;

public interface BaiduPoiService extends IService<BaiduPoi> {


    List<BaiduPoi> getPoisByIds(List<BaiDuSearchVo.ResultsDTO> results);

    List<BaiduPoi> getBaiDuPoiSuggestApi(String key);

    List<BaiduPoi> getSizePoi(int i, List<BaiduPoi> baiduPois);

    PageDTO<BaiduPoi> getPageList(BaiduPoi baiduPoi, PageDTO<BaiduPoi> query);
}

