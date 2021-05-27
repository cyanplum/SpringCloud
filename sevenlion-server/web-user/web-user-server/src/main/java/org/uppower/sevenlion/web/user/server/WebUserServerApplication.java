package org.uppower.sevenlion.web.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.uppower.sevenlion.security.annotation.EnableSevenlionSecurity;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/20 10:25 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSevenlionSecurity
@EnableFeignClients(basePackages = "org.uppower.sevenlion.web.**.common")
public class WebUserServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebUserServerApplication.class,args);
    }
}
