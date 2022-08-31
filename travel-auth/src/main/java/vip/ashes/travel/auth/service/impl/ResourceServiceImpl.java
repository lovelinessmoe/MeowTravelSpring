package vip.ashes.travel.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import vip.ashes.travel.common.redis.constant.RedisConstant;
import vip.ashes.travel.common.redis.service.RedisService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;

    @Resource
    private RedisService redisService;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/system/user/getUserInfo", CollUtil.toList("ROLE_ADMIN"));
        resourceRolesMap.put("/system/user/submit", CollUtil.toList("ROLE_ADMIN"));
        resourceRolesMap.put("/system/user/update", CollUtil.toList("ROLE_ADMIN"));
        resourceRolesMap.put("/system/user/remove", CollUtil.toList("ROLE_ADMIN"));
        resourceRolesMap.put("/system/user/removeMany", CollUtil.toList("ROLE_ADMIN"));
        resourceRolesMap.put("/system/user/list", CollUtil.toList("ROLE_ADMIN"));

        resourceRolesMap.put("/user/user/getUserInfo", CollUtil.toList("ROLE_ADMIN","ROLE_USER"));
        redisService.setCacheMap(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
