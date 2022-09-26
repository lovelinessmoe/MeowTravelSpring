package vip.ashes.travel.map.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.map.entity.UserRatePoi;
import vip.ashes.travel.map.mapper.UserRatePoiMapper;

import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class UserRatePoiServiceImpl extends ServiceImpl<UserRatePoiMapper, UserRatePoi> implements UserRatePoiService {

    private final UserRatePoiMapper userRatePoiMapper;

    @Override
    public List<UserRatePoi> getAllSightsPoiRate() {
        return userRatePoiMapper.getAllSightsPoiRate();
    }

    @Override
    public int submitRate(UserRatePoi userRatePoi) {
        // 克隆一个生成Wrapper
        UserRatePoi clone = ObjectUtil.clone(userRatePoi);
        userRatePoi.setRate(null);
        QueryWrapper<UserRatePoi> userRatePoiQueryWrapper = new QueryWrapper<>(userRatePoi);
        Long aLong = userRatePoiMapper.selectCount(userRatePoiQueryWrapper);
        int resCode;
        if (aLong != 0) {
            UpdateWrapper<UserRatePoi> userRatePoiUpdateWrapper = new UpdateWrapper<>(userRatePoi);
            userRatePoiUpdateWrapper.set(UserRatePoi.COL_RATE, clone.getRate());
            resCode = userRatePoiMapper.update(null, userRatePoiUpdateWrapper);
        } else {
            resCode = userRatePoiMapper.insert(clone);
        }
        return resCode;
    }
}
