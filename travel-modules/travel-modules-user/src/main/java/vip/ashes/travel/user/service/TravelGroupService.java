package vip.ashes.travel.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.user.entity.TravelGroup;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;

import java.util.List;

public interface TravelGroupService extends IService<TravelGroup> {


    List<GroupInfoVo> getPageGroup(PageDTO<GroupInfoVo> page);
}
