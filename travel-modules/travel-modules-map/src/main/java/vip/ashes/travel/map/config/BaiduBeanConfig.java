package vip.ashes.travel.map.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vip.ashes.travel.map.util.BaiduUtil;

import javax.annotation.Resource;

/**
 * 注入百度地图工具类
 *
 * @author loveliness
 */

@Configuration
@RefreshScope
public class BaiduBeanConfig {
    @Value("${map:ak}")
    @Resource
    private String ak;

    @Resource
    private ObjectMapper objectMapper;


    @Bean
    public BaiduUtil baiduUtil() {
        return new BaiduUtil(ak, objectMapper);
    }
}
