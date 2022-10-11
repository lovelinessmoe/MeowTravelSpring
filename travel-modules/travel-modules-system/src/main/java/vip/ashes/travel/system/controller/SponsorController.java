package vip.ashes.travel.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.Vo.SponsorVo;
import vip.ashes.travel.system.service.SponsorService;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/sponsor")
@AllArgsConstructor
public class SponsorController {
    private final SponsorService sponsorService;

    /**
     * 赞助 列表 分页
     */
    @GetMapping("/list")
    public Result list(SponsorVo sponsorVo, PageDTO<Tactic> query) {
        PageDTO<SponsorVo> pages = sponsorService.getSponsorPage(query, sponsorVo);
        return Result.ok().data(pages);
    }
}
