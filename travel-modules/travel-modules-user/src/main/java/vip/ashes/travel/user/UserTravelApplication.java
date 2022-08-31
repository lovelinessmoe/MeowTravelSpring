package vip.ashes.travel.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author loveliness
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "vip.ashes")
public class UserTravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserTravelApplication.class, args);
    }
}
