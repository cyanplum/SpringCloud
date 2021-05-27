package org.uppower.sevenlion.oss.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.uppower.sevenlion.cloud.security.annotation.EnableSevenlionSecurity;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/24 11:54 下午
 */
@SpringBootApplication
@EnableSevenlionSecurity
public class OssServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssServerApplication.class, args);
    }
}
