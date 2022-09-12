package vip.ashes.travel.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.ashes.travel.system.entity.User;
import vip.ashes.travel.system.entity.Vo.UserLocationVo;
import vip.ashes.travel.system.mapper.UserMapper;
import vip.ashes.travel.system.service.UserService;

import java.util.List;

/**
 * @author loveliness
 */
@Service
@AllArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public List<UserLocationVo> getUserLocation() {
        return userMapper.getUserLocation();
    }
}

