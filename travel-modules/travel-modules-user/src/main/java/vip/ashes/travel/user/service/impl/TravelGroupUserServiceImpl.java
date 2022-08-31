package vip.ashes.travel.user.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.ashes.travel.user.mapper.TravelGroupUserMapper;
import vip.ashes.travel.user.entity.TravelGroupUser;
import vip.ashes.travel.user.service.TravelGroupUserService;

@Service
public class TravelGroupUserServiceImpl extends ServiceImpl<TravelGroupUserMapper, TravelGroupUser> implements TravelGroupUserService {

}
