package vip.ashes.travel.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vip.ashes.travel.auth.entity.SecurityUser;
import vip.ashes.travel.auth.entity.User;
import vip.ashes.travel.auth.mapper.AuthUserMapper;
import vip.ashes.travel.auth.service.AuthUserService;
import vip.ashes.travel.common.core.ResultCode;

import javax.annotation.Resource;

/**
 * @author loveliness
 */
@Service("authUserService")
@AllArgsConstructor
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, User> implements AuthUserService, UserDetailsService {

    @Resource
    private final AuthUserMapper authUserMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        QueryWrapper<User> eq = new QueryWrapper<User>().eq(User.COL_EMAIL, email);
        User user = authUserMapper.selectOne(eq);
        if (user == null) {
            throw new UsernameNotFoundException(ResultCode.USER_CREDENTIALS_ERROR.getMessage());
        }
        SecurityUser securityUser = new SecurityUser(user);
        if (!securityUser.isEnabled()) {
            throw new DisabledException(ResultCode.USER_ACCOUNT_DISABLE.getMessage());
        } else if (!securityUser.isAccountNonLocked()) {
            throw new LockedException(ResultCode.USER_ACCOUNT_LOCKED.getMessage());
        } else if (!securityUser.isAccountNonExpired()) {
            throw new AccountExpiredException(ResultCode.USER_ACCOUNT_EXPIRED.getMessage());
        } else if (!securityUser.isCredentialsNonExpired()) {
            throw new CredentialsExpiredException(ResultCode.USER_CREDENTIALS_EXPIRED.getMessage());
        }
        return securityUser;
    }

}
