package vip.ashes.travel.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.TravelGroupUser;

/**
 * @author loveliness
 */
public interface TravelGroupUserService extends IService<TravelGroupUser> {


    /**
     * 加入旅游图
     *
     * @param groupId  旅游团id
     * @param userId   用户id
     * @param isLeader 是否是团长
     * @return 影响的条数
     */
    Result addGroupUser(String groupId, String userId, boolean isLeader);

    /**
     * 获取GroupUser实体
     *
     * @param groupId 旅游团id
     * @param userId  用户id
     * @return TravelGroupUser
     */
    TravelGroupUser getGroupUser(String groupId, String userId);

    /**
     * 解散旅游团
     *
     * @param groupId 旅游团id
     * @return 是否成功
     */
    boolean dissolutionGroup(String groupId);
}
