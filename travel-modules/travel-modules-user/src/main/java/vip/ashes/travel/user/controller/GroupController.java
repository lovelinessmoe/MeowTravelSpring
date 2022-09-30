package vip.ashes.travel.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.TravelGroup;
import vip.ashes.travel.user.entity.TravelGroupUser;
import vip.ashes.travel.user.entity.User;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;
import vip.ashes.travel.user.service.TravelGroupService;
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
    private final LoginUserUtil loginUserUtil;

    @GetMapping("getPageGroup")
    public Result getPageGroup(PageDTO<GroupInfoVo> page) {
        return Result.ok().data(travelGroupService.getPageGroup(page));
    }

    @PostMapping("crateGroup")
    @Transactional(rollbackFor = Exception.class)
    public Result crateGroup(TravelGroup travelGroup) {
        try {
            travelGroupService.save(travelGroup);

            User currentUser = loginUserUtil.getCurrentUser();
            String userId = currentUser.getUserId();
            TravelGroupUser travelGroupUser = TravelGroupUser.builder()
                    .groupId(travelGroup.getGroupId())
                    .userId(userId)
                    .isLeader(true)
                    .build();
            travelGroupUserService.save(travelGroupUser);
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error();
        }
        return Result.ok();
    }

}
