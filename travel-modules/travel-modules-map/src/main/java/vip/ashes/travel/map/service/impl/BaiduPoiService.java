package vip.ashes.travel.map.service.impl;

import vip.ashes.travel.map.entity.BaiduPoi;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.map.entity.Vo.BaiDuSearchVo;

import java.util.List;

public interface BaiduPoiService extends IService<BaiduPoi>{


    List<BaiduPoi> getPoisByIds(List<BaiDuSearchVo.ResultsDTO> results);
}
