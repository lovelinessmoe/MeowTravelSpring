package vip.ashes.travel.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import vip.ashes.travel.common.core.Result;
import vip.ashes.travel.common.core.ResultCode;
import vip.ashes.travel.system.entity.User;
import vip.ashes.travel.system.service.UserService;
import vip.ashes.travel.system.utils.LoginUserUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * @author loveliness
 */
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LoginUserUtil loginUserUtil;

    /**
     * 获取用户信息
     * @return 用户信息
     */
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

    /**
     * 新增或修改用户
     */
    @PostMapping("/submit")
    public Result submit(@RequestBody User user) {
        return Result.RCode(userService.saveOrUpdate(user), ResultCode.SUCCESS);
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        return Result.RCode(userService.updateById(user), ResultCode.SUCCESS);
    }

    /**
     * 删除用户
     */
    @PostMapping("/remove")
    public Result remove(@RequestParam String userId) {
        return Result.RCode(userService.removeById(userId), ResultCode.SUCCESS);
    }

    /**
     * 删除多个用户
     */
    @PostMapping("/removeMany")
    public Result removeMany(@RequestBody ArrayList<User> userList) {
        return Result.RCode(userService.removeByIds(userList), ResultCode.SUCCESS);
    }

    /**
     * 分页 用户
     */
    @GetMapping("/list")
    public Result list(User user, PageDTO<User> query) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>(user);
        PageDTO<User> pages = userService.page(query, userQueryWrapper);
        return Result.ok().data(pages);
    }
}
