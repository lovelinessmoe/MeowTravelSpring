package vip.ashes.travel.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import vip.ashes.travel.system.entity.Sponsor;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.Vo.SponsorVo;

public interface SponsorService extends IService<Sponsor>{


    PageDTO<SponsorVo> getSponsorPage(PageDTO<Tactic> query, SponsorVo sponsorVo);
}
