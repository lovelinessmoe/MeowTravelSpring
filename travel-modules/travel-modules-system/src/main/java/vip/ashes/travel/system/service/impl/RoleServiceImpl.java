package vip.ashes.travel.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.ashes.travel.system.entity.Role;
import vip.ashes.travel.system.mapper.RoleMapper;
import vip.ashes.travel.system.service.RoleService;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
