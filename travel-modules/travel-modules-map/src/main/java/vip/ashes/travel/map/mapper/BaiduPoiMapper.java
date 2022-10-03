package vip.ashes.travel.map.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.ashes.travel.map.entity.BaiduPoi;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface BaiduPoiMapper extends BaseMapper<BaiduPoi> {
    /**
     * 获取攻略中存在景点列表并按照评分，评论，观看次数排序
     *
     * @param i         要获得的条数
     * @param baiduPois
     * @return 百度poi
     */
    List<BaiduPoi> getSizePoi(@Param("size") int i, @Param("baiduPois") ArrayList<String> baiduPois);

    /**
     * TODO 有时间就用 首页的话
     * 推荐顺序获取
     *
     * @param query 分页 不用管
     * @return
     */
    PageDTO<BaiduPoi> getPageList(PageDTO<BaiduPoi> query);
}
