package vip.ashes.travel.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.ashes.travel.user.entity.TravelGroupUserReport;

@Mapper
public interface TravelGroupUserReportMapper extends BaseMapper<TravelGroupUserReport> {
    /**
     * 获取某一日期的打卡记录
     *
     * @param groupUserId TravelGroupUser的主键
     * @param date        当前日期 格式 2022-10-03
     * @return 查询记录
     */
    TravelGroupUserReport getUserCheck(@Param("groupUserId") String groupUserId, @Param("date") String date);
}
