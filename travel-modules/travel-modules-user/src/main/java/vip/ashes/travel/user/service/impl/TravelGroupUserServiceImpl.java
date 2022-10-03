package vip.ashes.travel.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.TravelGroupUser;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;
import vip.ashes.travel.user.mapper.TravelGroupMapper;
import vip.ashes.travel.user.mapper.TravelGroupUserMapper;
import vip.ashes.travel.user.service.TravelGroupUserService;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class TravelGroupUserServiceImpl extends ServiceImpl<TravelGroupUserMapper, TravelGroupUser> implements TravelGroupUserService {
    private final TravelGroupUserMapper travelGroupUserMapper;
    private final TravelGroupMapper travelGroupMapper;

    @Override
    public Result addGroupUser(String groupId, String userId, boolean isLeader) {

        GroupInfoVo groupInfoVo = travelGroupMapper.getGroupInfoByGroupId(groupId);
        // 有空位
        if (groupInfoVo.getNowNum() < groupInfoVo.getGroupNum()) {
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
}

