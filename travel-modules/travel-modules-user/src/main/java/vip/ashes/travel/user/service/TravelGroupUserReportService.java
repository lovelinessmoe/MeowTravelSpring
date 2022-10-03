package vip.ashes.travel.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.user.entity.TravelGroupUserReport;

public interface TravelGroupUserReportService extends IService<TravelGroupUserReport> {


    int checkDaily(TravelGroupUserReport travelGroupUserReport);

    TravelGroupUserReport getUserCheckToday(String groupUserId);

    TravelGroupUserReport getUserCheck(String groupUserId, String date);
}
