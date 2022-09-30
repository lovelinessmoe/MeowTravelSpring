package vip.ashes.travel.map.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
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
        if (idList.isEmpty()) {
            return new ArrayList<>();
        } else {
            return baiduPoiMapper.selectBatchIds(idList);
        }
    }

    @Override
    public List<BaiduPoi> getBaiDuPoiSuggestApi(String key) {
        QueryWrapper<BaiduPoi> baiduPoiQueryWrapper = new QueryWrapper<>();
        baiduPoiQueryWrapper.like(BaiduPoi.COL_NAME, key)
                .select(BaiduPoi.COL_NAME, BaiduPoi.COL_UID, BaiduPoi.COL_ADDRESS);
        return baiduPoiMapper.selectList(baiduPoiQueryWrapper);
    }

    @Override
    public List<BaiduPoi> getSizePoi(int i, List<BaiduPoi> baiduPois) {
        ArrayList<String> strings = new ArrayList<>();
        for (BaiduPoi pois : baiduPois) {
            strings.add(pois.getUid());
        }
        List<BaiduPoi> sizePoi = baiduPoiMapper.getSizePoi(i, strings);
        return sizePoi;
    }

    @Override
    public PageDTO<BaiduPoi> getPageList(BaiduPoi baiduPoi, PageDTO<BaiduPoi> query) {
        QueryWrapper<BaiduPoi> baiduPoiQueryWrapper = new QueryWrapper<>(baiduPoi);
        baiduPoiQueryWrapper.orderByDesc(BaiduPoi.COL_POI_PHOTO_URL);
        return baiduPoiMapper.selectPage(query, baiduPoiQueryWrapper);
    }
}

