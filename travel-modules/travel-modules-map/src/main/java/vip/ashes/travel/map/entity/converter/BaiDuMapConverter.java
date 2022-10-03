package vip.ashes.travel.map.entity.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import vip.ashes.travel.map.entity.BaiduPoi;
import vip.ashes.travel.map.entity.Vo.BaiDuPoiDetailVo;
import vip.ashes.travel.map.entity.Vo.BaiDuSearchVo;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper(componentModel = "spring")
public interface BaiDuMapConverter {
    /**
     * 类型转换
     *
     * @param searchVo 搜索结果
     * @return 数据库poi
     */
    @Mappings({
            @Mapping(target = "locationLat", source = "location.lat"),
            @Mapping(target = "locationLng", source = "location.lng"),
    })
    BaiduPoi getPoiBySearchVo(BaiDuSearchVo.ResultsDTO searchVo);

    /**
     * list Dict 转换
     *
     * @param searchVos 搜索结果集合
     * @return 数据库poi集合
     */
    List<BaiduPoi> getPoiListBySearchVoList(List<BaiDuSearchVo.ResultsDTO> searchVos);


    /**
     * 详细地点转数据库
     *
     * @param resultDTO 详细地点的详情
     * @return 数据库poi
     */
    @Mappings({
            @Mapping(target = "locationLat", source = "location.lat"),
            @Mapping(target = "locationLng", source = "location.lng"),
    })
    BaiduPoi getPoiByDetailVo(BaiDuPoiDetailVo.ResultDTO resultDTO);

}
