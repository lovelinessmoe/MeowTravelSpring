package vip.ashes.travel.user.controller;

import cn.hutool.core.util.StrUtil;
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
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;
import vip.ashes.travel.user.service.TravelGroupService;
import vip.ashes.travel.user.service.TravelGroupUserReportService;
import vip.ashes.travel.user.service.TravelGroupUserService;
import vip.ashes.travel.user.utils.LoginUserUtil;

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
     * @param page 分页
     * @param onlyShowMyJoin 是否只展示我参加的
     * @return GroupInfoVo
     */
    @GetMapping("getPageGroup")
    public Result getPageGroup(PageDTO<GroupInfoVo> page, @RequestParam boolean onlyShowMyJoin) {
        return Result.ok().data(travelGroupService.getPageGroup(onlyShowMyJoin, page));
    }

    /**
     * 创建或修改旅游团
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
     * @param groupId 旅游团ID
     * @param travelGroupUserReport 包含位置信息
     * @return 成功信息
     */
    @PostMapping("checkDaily")
    public Result checkDaily(@RequestParam String groupId,
                             @RequestBody TravelGroupUserReport travelGroupUserReport) {
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
    }

}
