package vip.ashes.travel.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author loveliness
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "vip.ashes")
public class SystemTravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemTravelApplication.class, args);
    }
}
