package vip.ashes.travel.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.user.entity.User;
import vip.ashes.travel.user.utils.LoginUserUtil;

import java.util.Collection;
import java.util.Map;

/**
 * @author loveliness
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final LoginUserUtil loginUserUtil;

    @SneakyThrows
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        User currentUser = loginUserUtil.getCurrentUser();
        Collection currentUserAuth = loginUserUtil.getCurrentUserAuth();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map = objectMapper.readValue(objectMapper.writeValueAsString(currentUser), Map.class);
        map.put("auth", currentUserAuth.toString());
        return Result.ok().data(map);
    }
}
