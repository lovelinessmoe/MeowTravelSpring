package vip.ashes.travel.auth.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.ashes.travel.auth.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取登录用户信息
 *
 * @author loveliness
 */
@Component
public class LoginUserUtil {

    public User getCurrentUser() {
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        User user = new User();
        user.setUserName(userJsonObject.getStr("user_name"));
        user.setUserId(Convert.toLong(userJsonObject.get("id")) + "");
//        user.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        return user;
    }
}
