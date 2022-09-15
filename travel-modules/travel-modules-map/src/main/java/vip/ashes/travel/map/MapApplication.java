package vip.ashes.travel.map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author loveliness
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MapApplication {
    public static void main(String[] args) {
        SpringApplication.run(MapApplication.class, args);
    }
}
