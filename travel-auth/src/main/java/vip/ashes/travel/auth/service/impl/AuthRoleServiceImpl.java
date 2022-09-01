package vip.ashes.travel.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import vip.ashes.travel.auth.entity.Role;
import vip.ashes.travel.auth.mapper.AuthRoleMapper;
import vip.ashes.travel.auth.service.AuthRoleService;

/**
 * @author loveliness
 */
@Service("authRoleService")
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, Role> implements AuthRoleService {

}
