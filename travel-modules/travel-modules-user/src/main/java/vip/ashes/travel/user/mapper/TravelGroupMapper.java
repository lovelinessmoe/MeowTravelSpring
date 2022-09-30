package vip.ashes.travel.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import vip.ashes.travel.user.entity.TravelGroup;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;

import java.util.List;

@Mapper
public interface TravelGroupMapper extends BaseMapper<TravelGroup> {
    List<GroupInfoVo> getPageGroup(PageDTO<GroupInfoVo> page);
}
