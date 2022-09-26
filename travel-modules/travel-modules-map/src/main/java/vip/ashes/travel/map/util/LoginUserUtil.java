package vip.ashes.travel.map.util;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vip.ashes.travel.map.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;


/**
 * 获取登录用户信息
 *
 * @author loveliness
 */
@Component
public class LoginUserUtil {

    @SneakyThrows
    public User getCurrentUser() {
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
//        user.setRoles(Convert.toList(String.class,userJsonObject.get("authorities")));
        String user = userJsonObject.getStr("user");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(user, User.class);
    }

    @SneakyThrows
    public Collection getCurrentUserAuth() {
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader("user");
        JSONObject userJsonObject = new JSONObject(userStr);
        String auth = userJsonObject.getStr("auth");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(auth, Collection.class);
    }
}
