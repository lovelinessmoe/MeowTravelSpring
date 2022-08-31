package vip.ashes.travel.user.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.ashes.travel.user.mapper.RoleMapper;
import vip.ashes.travel.user.entity.Role;
import vip.ashes.travel.user.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
