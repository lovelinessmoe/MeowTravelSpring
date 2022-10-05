package vip.ashes.travel.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.user.entity.TravelGroupUserReport;
import vip.ashes.travel.user.entity.Vo.CheckGroupInfo;

import java.util.List;

public interface TravelGroupUserReportService extends IService<TravelGroupUserReport> {


    int checkDaily(TravelGroupUserReport travelGroupUserReport);

    TravelGroupUserReport getUserCheckToday(String groupUserId);

    TravelGroupUserReport getUserCheck(String groupUserId, String date);

    List<CheckGroupInfo> getTodayGroupCheckInfo(String groupId);

    List<CheckGroupInfo> getGroupCheckInfo(String groupId, String date);
}
