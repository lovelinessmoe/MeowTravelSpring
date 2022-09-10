package vip.ashes.travel.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.user.entity.Tactic;
import vip.ashes.travel.user.entity.User;
import vip.ashes.travel.user.entity.Vo.TacticDetailVO;
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
    private final LoginUserUtil loginUserUtil;

    /**
     * 详情
     */
    @GetMapping("/detail/{tacticId}")
    public Result detail(@PathVariable("tacticId") String tacticId) {
        TacticDetailVO tacticDetail = tacticService.getTacticDetail(tacticId);
        return Result.ok().data(tacticDetail);
    }

    /**
     * 新增或修改文章
     */
    @PostMapping("/submit")
    public Result submit(@RequestBody Tactic tactic) {
        return Result.RCode(tacticService.saveOrUpdate(tactic), ResultCode.SUCCESS);
    }

    /**
     * 修改文章
     */
    @PostMapping("/update")
    public Result update(@RequestBody Tactic tactic) {
        return Result.RCode(tacticService.updateById(tactic), ResultCode.SUCCESS);
    }

    /**
     * 删除文章
     */
    @PostMapping("/remove")
    public Result remove(@RequestParam String tacticId) {
        return tacticService.removeTactic(tacticId);
    }

    /**
     * 分页 文章
     */
    @GetMapping("/list")
    public Result list(Tactic tactic, PageDTO<Tactic> query) {
        QueryWrapper<Tactic> articleQueryWrapper = new QueryWrapper<>(tactic);
        articleQueryWrapper.orderByDesc(Tactic.COL_IS_TOP);
        articleQueryWrapper.orderByDesc(Tactic.COL_VIEWS_COUNT);
        articleQueryWrapper.orderByDesc(Tactic.COL_COMMENTS_COUNT);
        articleQueryWrapper.orderByDesc(Tactic.COL_CREATE_TIME);
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
    /*@PostMapping("/saveOrUpdateTactic")
    public Result saveOrUpdateTactic(@RequestBody TacticDetailVO tacticVO) {
        User currentUser = loginUserUtil.getCurrentUser();
        tacticVO.setUserId(currentUser.getUserId());
        return tacticService.save(tacticVO);
    }*/
}
