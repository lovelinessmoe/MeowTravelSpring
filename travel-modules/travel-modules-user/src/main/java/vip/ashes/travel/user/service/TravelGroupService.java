package vip.ashes.travel.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.ashes.travel.user.entity.TravelGroup;
import vip.ashes.travel.user.entity.Vo.GroupInfoVo;

import java.util.List;

/**
 * @author loveliness
 */
public interface TravelGroupService extends IService<TravelGroup> {


    List<GroupInfoVo> getPageGroup(boolean onlyShowMyJoin, PageDTO<GroupInfoVo> page);
}
