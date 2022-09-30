package vip.ashes.travel.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author loveliness
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "vip.ashes")
public class PayTravelApplication {
    public static void main(String[] args) {
        SpringApplication.run(PayTravelApplication.class, args);
    }
}
