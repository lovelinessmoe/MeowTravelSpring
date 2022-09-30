package vip.ashes.travel.user.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.user.entity.TravelGroup;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;
import vip.ashes.travel.user.mapper.TravelGroupMapper;
import vip.ashes.travel.user.service.TravelGroupService;

import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class TravelGroupServiceImpl extends ServiceImpl<TravelGroupMapper, TravelGroup> implements TravelGroupService {
    private final TravelGroupMapper travelGroupMapper;
    @Override
    public List<GroupInfoVo> getPageGroup(PageDTO<GroupInfoVo> page) {
        return travelGroupMapper.getPageGroup(page);
    }
}

