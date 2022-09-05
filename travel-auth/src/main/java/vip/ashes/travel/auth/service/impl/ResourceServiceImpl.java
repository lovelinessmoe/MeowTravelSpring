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
 *
 * @author loveliness
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;

    @Resource
    private RedisService redisService;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        String systemPrefixUrl = "/system/";
        resourceRolesMap.put(systemPrefixUrl + "**", CollUtil.toList("ROLE_ADMIN"));
        String userPrefixUrl = "/user/";
        resourceRolesMap.put(userPrefixUrl + "**", CollUtil.toList("ROLE_ADMIN", "ROLE_USER"));
        redisService.setCacheMap(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
