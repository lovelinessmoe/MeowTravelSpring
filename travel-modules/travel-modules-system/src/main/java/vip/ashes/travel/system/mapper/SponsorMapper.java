package vip.ashes.travel.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import vip.ashes.travel.system.entity.Sponsor;
import vip.ashes.travel.system.entity.Tactic;
import vip.ashes.travel.system.entity.Vo.SponsorVo;

/**
 * @author loveliness
 */
@Mapper
public interface SponsorMapper extends BaseMapper<Sponsor> {
    PageDTO<SponsorVo> getSponsorPage(@Param("query") PageDTO<Tactic> query, @Param(Constants.WRAPPER) QueryWrapper<SponsorVo> sponsorVoQueryWrapper);
}
