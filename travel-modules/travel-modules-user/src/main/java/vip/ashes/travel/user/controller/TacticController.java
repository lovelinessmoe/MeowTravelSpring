package vip.ashes.travel.user.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.user.entity.Tactic;
import vip.ashes.travel.user.entity.TacticDetail;
import vip.ashes.travel.user.entity.User;
import vip.ashes.travel.user.entity.Vo.TacticDetailVo;
import vip.ashes.travel.user.entity.converter.TacticConverter;
import vip.ashes.travel.user.service.TacticDetailService;
import vip.ashes.travel.user.service.TacticService;
import vip.ashes.travel.user.utils.LoginUserUtil;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/tactic")
public class TacticController {

    private final TacticService tacticService;
    private final TacticDetailService tacticDetailService;
    private final LoginUserUtil loginUserUtil;
    private final TacticConverter tacticConverter;

    /**
     * 详情
     */
    @GetMapping("/detail/{tacticId}")
    public Result detail(@PathVariable("tacticId") String tacticId) {
        TacticDetailVo tacticDetail = tacticService.getTacticDetail(tacticId);
        return Result.ok().data(tacticDetail);
    }

    /**
     * 删除文章
     */
    @PostMapping("/remove")
    public Result remove(@RequestParam String tacticId) {
        String userId = loginUserUtil.getCurrentUser().getUserId();
        Tactic byId = tacticService.getById(tacticId);
        if (byId.getUserId().equals(userId)) {
            return tacticService.removeTactic(tacticId);
        } else {
            return Result.error().message("这不是你发布的攻略");
        }
    }

    /**
     * 分页 文章
     */
    @GetMapping("/list")
    public Result list(String key, PageDTO<Tactic> query) {
        QueryWrapper<Tactic> articleQueryWrapper = new QueryWrapper<>();
        if (!StrUtil.isBlank(key)) {
            articleQueryWrapper.like(Tactic.COL_TITLE, key).or()
                    .like(Tactic.COL_SHORT_MSG, key).or()
                    .like(Tactic.COL_TACTIC_ID, key).or()
                    .eq(Tactic.COL_UID, key);
        }
        articleQueryWrapper.orderByDesc(Tactic.COL_IS_TOP)
                .orderByDesc(Tactic.COL_CREATE_TIME)
                .orderByDesc(Tactic.COL_VIEWS_COUNT)
                .orderByDesc(Tactic.COL_COMMENTS_COUNT);

        PageDTO<Tactic> pages = tacticService.page(query, articleQueryWrapper);

        return Result.ok()
                //文章列表
                .data("records", pages.getRecords())
                //是否有下一页
                .data("hasNextPage", pages.hasNext());
    }

    /**
     * 添加 文章
     */
    @PostMapping("/addTactic")
    @Transactional(rollbackFor = Exception.class)
    public Result addTactic(@RequestBody TacticDetailVo tacticVO) {
        User currentUser = loginUserUtil.getCurrentUser();
        tacticVO.setUserId(currentUser.getUserId());

        //VO转换为实体
        Tactic tactic = tacticConverter.tacticDetailVOToTactic(tacticVO);
        TacticDetail tacticDetail = tacticConverter.tacticDetailVOToTacticDetail(tacticVO);
        try {
            tacticService.save(tactic);
            tacticDetailService.save(tacticDetail);
        } catch (Exception e) {
            e.printStackTrace();
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.RCode(false, ResultCode.ARTICLE_NOT_MODIFY);
        }
        return Result.ok();
    }

    /**
     * 修改 文章
     */
    @PostMapping("/editTactic")
    @Transactional(rollbackFor = Exception.class)
    public Result editTactic(@RequestBody TacticDetailVo tacticVO) {
        User currentUser = loginUserUtil.getCurrentUser();
        tacticVO.setUserId(currentUser.getUserId());

        //VO转换为实体
        Tactic tactic = tacticConverter.tacticDetailVOToTactic(tacticVO);
        TacticDetail tacticDetail = tacticConverter.tacticDetailVOToTacticDetail(tacticVO);
        try {
            tacticService.updateById(tactic);
            tacticDetailService.updateById(tacticDetail);
        } catch (Exception e) {
            //手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.RCode(false, ResultCode.ARTICLE_NOT_MODIFY);
        }
        return Result.ok();
    }


    /**
     * 分页 文章
     */
    @GetMapping("/getUserTacticList")
    public Result getUserTacticList(Tactic tactic, PageDTO<Tactic> query) {
        User currentUser = loginUserUtil.getCurrentUser();

        QueryWrapper<Tactic> articleQueryWrapper = new QueryWrapper<>(tactic);
        articleQueryWrapper.orderByDesc(Tactic.COL_CREATE_TIME);
        articleQueryWrapper.eq(Tactic.COL_USER_ID, currentUser.getUserId());
        PageDTO<Tactic> pages = tacticService.page(query, articleQueryWrapper);

        return Result.ok()
                //文章列表
                .data("records", pages.getRecords())
                //是否有下一页
                .data("hasNextPage", pages.hasNext());
    }

}
