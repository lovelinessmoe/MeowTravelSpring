package vip.ashes.travel.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.ashes.travel.system.mapper.UserMapper;
import vip.ashes.travel.system.entity.User;
import vip.ashes.travel.system.service.UserService;
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
