package org.uppower.sevenlion.back.user.server;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.uppower.sevenlion.security.annotation.EnableSevenlionCode;
import org.uppower.sevenlion.security.annotation.EnableSevenlionSecurity;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/25 5:55 下午
 */
@SpringCloudApplication
@EnableSevenlionSecurity
@EnableSevenlionCode
public class BackUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackUserApplication.class, args);
    }
}
