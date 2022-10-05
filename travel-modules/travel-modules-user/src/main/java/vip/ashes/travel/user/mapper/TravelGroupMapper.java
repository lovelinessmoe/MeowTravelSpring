package vip.ashes.travel.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.ashes.travel.user.entity.TravelGroup;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;

import java.util.List;

/**
 * @author loveliness
 */
@Mapper
public interface TravelGroupMapper extends BaseMapper<TravelGroup> {
    /**
     * 获取分页旅游团
     * @param userId 用户ID
     * @param page 分页信息
     * @return  List<GroupInfoVo>
     */
    List<GroupInfoVo> getPageGroup(String userId, PageDTO<GroupInfoVo> page);

    /**
     * 通过GroupId获取旅游团信息
     * @param groupId 旅游团ID
     * @return 包含参加人数的VO
     */
    GroupInfoVo getGroupInfoByGroupId(@Param("groupId") String groupId);

    List<TravelGroup> getMyGroup(@Param("userId") String userId);
}
