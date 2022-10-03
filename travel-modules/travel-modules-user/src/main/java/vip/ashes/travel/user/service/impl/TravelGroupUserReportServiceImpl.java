package vip.ashes.travel.user.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.user.entity.TravelGroupUserReport;
import vip.ashes.travel.user.mapper.TravelGroupUserReportMapper;
import vip.ashes.travel.user.service.TravelGroupUserReportService;

import java.util.Date;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class TravelGroupUserReportServiceImpl extends ServiceImpl<TravelGroupUserReportMapper, TravelGroupUserReport> implements TravelGroupUserReportService {
    private final TravelGroupUserReportMapper travelGroupUserReportMapper;

    @Override
    public int checkDaily(TravelGroupUserReport travelGroupUserReport) {
        System.out.println(travelGroupUserReport);
        return travelGroupUserReportMapper.insert(travelGroupUserReport);
    }

    @Override
    public TravelGroupUserReport getUserCheckToday(String groupUserId) {
        return getUserCheck(groupUserId, DateUtil.formatDate(new Date()));
    }

    @Override
    public TravelGroupUserReport getUserCheck(String groupUserId, String date) {
        return travelGroupUserReportMapper.getUserCheck(groupUserId, date);
    }
}

