package vip.ashes.travel.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import vip.ashes.travel.auth.service.AuthRoleService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author loveliness
 */
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {
    private static SecurityUser securityUser;

    private User user;

    @Resource
    private AuthRoleService roleService;
    /**
     * 用户状态
     */
    private Boolean enabled;
    /**
     * 权限数据
     */
    private Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

    public SecurityUser(User user) {
        this.user = user;
        this.setEnabled(true);

        Role roleById = securityUser.roleService.getById(user.getRoleId());
        String role = roleById.getRoleNameEn();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
    }

    /**
     * 在非controller和service中注入service和mapper需要下注解
     */
    @PostConstruct
    public void init() {
        securityUser = this;
        securityUser.roleService = this.roleService;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
