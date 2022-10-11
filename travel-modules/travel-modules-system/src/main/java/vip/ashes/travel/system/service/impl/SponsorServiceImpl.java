package vip.ashes.travel.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.system.entity.Sponsor;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.User;
import vip.ashes.travel.system.entity.Vo.SponsorVo;
import vip.ashes.travel.system.mapper.SponsorMapper;
import vip.ashes.travel.system.service.SponsorService;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class SponsorServiceImpl extends ServiceImpl<SponsorMapper, Sponsor> implements SponsorService {
    private final SponsorMapper sponsorMapper;

    @Override
    public PageDTO<SponsorVo> getSponsorPage(PageDTO<Tactic> query, SponsorVo sponsorVo) {
        QueryWrapper<SponsorVo> sponsorVoQueryWrapper = new QueryWrapper<>(sponsorVo)
                .like(StrUtil.isNotBlank(sponsorVo.getTitle()),
                        "t." + Tactic.COL_TITLE,
                        sponsorVo.getTitle())
                .like(StrUtil.isNotBlank(sponsorVo.getUserName()),
                        "u." + User.COL_USER_NAME,
                        sponsorVo.getUserName())
                .like(StrUtil.isNotBlank(sponsorVo.getEmail()),
                        "u." + User.COL_EMAIL,
                        sponsorVo.getEmail())
                .eq(sponsorVo.getType() != null,
                        "s." + Sponsor.COL_TYPE,
                        sponsorVo.getType());
        return sponsorMapper.getSponsorPage(query, sponsorVoQueryWrapper);
    }
}
