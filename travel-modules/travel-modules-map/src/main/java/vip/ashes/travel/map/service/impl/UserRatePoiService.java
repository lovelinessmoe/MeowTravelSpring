package vip.ashes.travel.map.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.map.entity.UserRatePoi;

import java.util.List;

public interface UserRatePoiService extends IService<UserRatePoi> {


    List<UserRatePoi> getAllSightsPoiRate();

    int submitRate(UserRatePoi userRatePoi);
}
