package vip.ashes.travel.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.User;
import vip.ashes.travel.system.entity.Vo.TacticDetailVO;
import vip.ashes.travel.system.service.TacticService;
import vip.ashes.travel.system.utils.LoginUserUtil;

import java.util.ArrayList;

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
     * 删除多个文章
     */
    @PostMapping("/removeMany")
    public Result removeMany(@RequestBody ArrayList<Tactic> tacticList) {
        return tacticService.removeMany(tacticList);
    }

    /**
     * 分页 文章
     */
    @GetMapping("/list")
    public Result list(Tactic tactic, PageDTO<Tactic> query) {
        QueryWrapper<Tactic> tacticQueryWrapper = new QueryWrapper<>(tactic);
        tacticQueryWrapper.orderByDesc(Tactic.COL_CREATE_TIME);
        PageDTO<Tactic> pages = tacticService.page(query, tacticQueryWrapper);
        return Result.ok().data(pages);
    }

    /**
     * 添加 文章
     */
    @PostMapping("/saveOrUpdateTactic")
    public Result saveOrUpdateTactic(@RequestBody TacticDetailVO tacticVO) {
        User currentUser = loginUserUtil.getCurrentUser();
        tacticVO.setUserId(currentUser.getUserId());
        return tacticService.saveOrUpdateTactic(tacticVO);
    }

    /**
     * 更换置顶状态
     */
    @PostMapping("/switchTop")
    public Result switchTop(@RequestParam String tacticId) {
        UpdateWrapper<Tactic> tacticUpdateWrapper = new UpdateWrapper<>();
        //isTop取反
        tacticUpdateWrapper.setSql(Tactic.COL_IS_TOP + "= NOT " + Tactic.COL_IS_TOP);
        //限定文章
        tacticUpdateWrapper.eq(Tactic.COL_TACTIC_ID, tacticId);
        //更新
        return Result.ok().data(tacticService.update(tacticUpdateWrapper));
    }

    /**
     * 详情
     */
    @GetMapping("/detail/{tacticId}")
    public Result detail(@PathVariable("tacticId") String tacticId) {
        TacticDetailVO tacticDetail = tacticService.getTacticDetail(tacticId);
        return Result.ok().data(tacticDetail);
    }
}
