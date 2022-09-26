package vip.ashes.travel.map.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.map.entity.UserRatePoi;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper
public interface UserRatePoiMapper extends BaseMapper<UserRatePoi> {
    List<UserRatePoi> getAllSightsPoiRate();
}
