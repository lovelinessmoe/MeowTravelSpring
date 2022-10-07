package vip.ashes.travel.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.TravelGroup;
import vip.ashes.travel.user.entity.TravelGroupUser;
import vip.ashes.travel.user.entity.TravelGroupUserReport;
import vip.ashes.travel.user.entity.User;
import vip.ashes.travel.user.entity.Vo.CheckGroupInfo;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;
import vip.ashes.travel.user.service.TravelGroupService;
import vip.ashes.travel.user.service.TravelGroupUserReportService;
import vip.ashes.travel.user.service.TravelGroupUserService;
import vip.ashes.travel.user.utils.LoginUserUtil;

import java.util.List;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private final TravelGroupService travelGroupService;
    private final TravelGroupUserService travelGroupUserService;
    private final TravelGroupUserReportService travelGroupUserReportService;
    private final LoginUserUtil loginUserUtil;

    /**
     * 分页查询旅游团信息
     *
     * @param page           分页
     * @param onlyShowMyJoin 是否只展示我参加的
     * @return GroupInfoVo
     */
    @GetMapping("getPageGroup")
    public Result getPageGroup(PageDTO<GroupInfoVo> page, @RequestParam boolean onlyShowMyJoin) {
        return Result.ok().data(travelGroupService.getPageGroup(onlyShowMyJoin, page));
    }

    /**
     * 创建或修改旅游团
     *
     * @param travelGroup 旅游团信息
     * @return 信息
     */
    @PostMapping("saveOrUpdateGroup")
    @Transactional(rollbackFor = Exception.class)
    public Result saveOrUpdateGroup(@RequestBody TravelGroup travelGroup) {
        // 存在该旅游团，更新信息
        if (StrUtil.isNotBlank(travelGroup.getGroupId())) {
            travelGroupService.updateById(travelGroup);
        } else {
            // 不存在 ，添加旅游团
            try {
                travelGroupService.save(travelGroup);

                // 添加队长信息
                User currentUser = loginUserUtil.getCurrentUser();
                travelGroupUserService.addGroupUser(
                        travelGroup.getGroupId(),
                        currentUser.getUserId(),
                        true);

            } catch (Exception e) {
                e.printStackTrace();
                //手动回滚事务
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return Result.error();
            }
        }
        return Result.ok();
    }

    /**
     * 加入旅游团
     *
     * @param groupId 旅游团ID
     * @return 成功信息
     */
    @PostMapping("joinGroup")
    public Result joinGroup(String groupId) {
        User currentUser = loginUserUtil.getCurrentUser();
        return travelGroupUserService.addGroupUser(groupId, currentUser.getUserId(), false);
    }

    /**
     * 每日打卡
     *
     * @param groupId               旅游团ID
     * @param travelGroupUserReport 包含位置信息
     * @return 成功信息
     */
    @PostMapping("checkDaily")
    public Result checkDaily(@RequestParam String groupId,
                             @RequestBody TravelGroupUserReport travelGroupUserReport) {
        TravelGroup byId = travelGroupService.getById(groupId);
        if (byId.getIsOpenReport()) {


            String userId = loginUserUtil.getCurrentUser().getUserId();
            TravelGroupUser groupUser = travelGroupUserService.getGroupUser(groupId, userId);
            String groupUserId = groupUser.getGroupUserId();
            TravelGroupUserReport todayLog = travelGroupUserReportService.getUserCheckToday(groupUserId);
            if (todayLog != null) {
                return Result.error().message("今天已经打过卡了哦");
            } else {
                travelGroupUserReport.setGroupUserId(groupUserId);
                travelGroupUserReportService.checkDaily(travelGroupUserReport);
                return Result.ok().message("打卡成功");
            }
        } else {
            return Result.error().message("该旅游团未开启健康打卡");
        }
    }

    @GetMapping("getMyGroup")
    public Result getMyGroup() {
        String userId = loginUserUtil.getCurrentUser().getUserId();
        List<TravelGroup> myGroup = travelGroupService.getMyGroup(userId);
        return Result.ok().data(myGroup);
    }

    @GetMapping("getGroupCheckInfo")
    public Result getGroupCheckInfo(String groupId) {
        List<CheckGroupInfo> checkGroupInfos = travelGroupUserReportService.getTodayGroupCheckInfo(groupId);
        return Result.ok().data(checkGroupInfos);
    }

    @PostMapping("removeUserFromGroup")
    public Result removeUserFromGroup(String userId, String groupId) {
        QueryWrapper<TravelGroupUser> eq = new QueryWrapper<TravelGroupUser>()
                .eq(TravelGroupUser.COL_USER_ID, userId)
                .eq(TravelGroupUser.COL_GROUP_ID, groupId);

        TravelGroupUser one = travelGroupUserService.getOne(eq);
        if (one.getIsLeader()) {
            boolean dissolution = travelGroupUserService.dissolutionGroup(groupId);
            if (dissolution) {
                return Result.ok().message("解散成功");
            } else {
                return Result.error().message("解散失败");
            }
        } else {
            boolean remove = travelGroupUserService.remove(eq);
            if (remove) {
                return Result.ok().message("踢出旅游团成功");
            } else {
                return Result.error().message("踢出旅游团失败");
            }
        }
    }

}
