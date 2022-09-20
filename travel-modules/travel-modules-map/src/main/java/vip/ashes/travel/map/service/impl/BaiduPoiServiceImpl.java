package vip.ashes.travel.map.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.map.entity.BaiduPoi;
import vip.ashes.travel.map.entity.Vo.BaiDuSearchVo;
import vip.ashes.travel.map.mapper.BaiduPoiMapper;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BaiduPoiServiceImpl extends ServiceImpl<BaiduPoiMapper, BaiduPoi> implements BaiduPoiService {

    private final BaiduPoiMapper baiduPoiMapper;

    @Override
    public List<BaiduPoi> getPoisByIds(List<BaiDuSearchVo.ResultsDTO> results) {
        ArrayList<String> idList = new ArrayList<>();
        for (BaiDuSearchVo.ResultsDTO result : results) {
            String uid = result.getUid();
            idList.add(uid);
        }
        return baiduPoiMapper.selectBatchIds(idList);
    }
}

