package vip.ashes.travel.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.TravelGroupUser;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;
import vip.ashes.travel.user.mapper.TravelGroupMapper;
import vip.ashes.travel.user.mapper.TravelGroupUserMapper;
import vip.ashes.travel.user.mapper.TravelGroupUserReportMapper;
import vip.ashes.travel.user.service.TravelGroupUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class TravelGroupUserServiceImpl extends ServiceImpl<TravelGroupUserMapper, TravelGroupUser> implements TravelGroupUserService {
    private final TravelGroupUserMapper travelGroupUserMapper;
    private final TravelGroupMapper travelGroupMapper;
    private final TravelGroupUserReportMapper travelGroupUserReportMapper;

    @Override
    public Result addGroupUser(String groupId, String userId, boolean isLeader) {

        GroupInfoVo groupInfoVo = travelGroupMapper.getGroupInfoByGroupId(groupId);
        // 有空位
        int nowNum = groupInfoVo.getNowNum();
        if (nowNum == 1) {
            nowNum--;
        }
        if (nowNum < groupInfoVo.getGroupNum()) {
            TravelGroupUser groupUser = getGroupUser(groupId, userId);
            if (groupUser == null) {
                // 用户与团的id
                TravelGroupUser travelGroupUser = TravelGroupUser.builder()
                        .groupId(groupId)
                        .userId(userId)
                        .isLeader(isLeader)
                        .build();
                travelGroupUserMapper.insert(travelGroupUser);
                return Result.ok().message("加入成功");
            } else {
                return Result.error().message("已经加入过旅游团");
            }
        }
        return Result.error().message("旅游团已满");
    }

    @Override
    public TravelGroupUser getGroupUser(String groupId, String userId) {
        TravelGroupUser travelGroupUser = TravelGroupUser.builder()
                .groupId(groupId)
                .userId(userId)
                .build();

        QueryWrapper<TravelGroupUser> travelGroupUserQueryWrapper = new QueryWrapper<>(travelGroupUser);
        return travelGroupUserMapper.selectOne(travelGroupUserQueryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean dissolutionGroup(String groupId) {
        try {
            // 先删除旅游团
            int i = travelGroupMapper.deleteById(groupId);
            // 获取旅游团成员信息
            QueryWrapper<TravelGroupUser> eq = new QueryWrapper<TravelGroupUser>().eq(TravelGroupUser.COL_GROUP_ID, groupId);
            List<TravelGroupUser> travelGroupUsers = travelGroupUserMapper.selectList(eq);
            // 删除旅游团成员
            travelGroupUserMapper.delete(eq);

            List<String> groupUserIds = new ArrayList<>();
            for (TravelGroupUser travelGroupUser : travelGroupUsers) {
                groupUserIds.add(travelGroupUser.getGroupUserId());
            }
            // 删除旅游团成员报告
            travelGroupUserReportMapper.deleteReportByGroupUserId(groupUserIds);
            return i > 0;
        } catch (Exception e) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }
}

