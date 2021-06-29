package org.uppower.sevenlion.back.system.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.uppower.sevenlion.security.annotation.EnableSevenlionSecurity;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/27 11:48 上午
 */
@SpringBootApplication
@EnableSevenlionSecurity
public class BackSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackSystemApplication.class,args);
    }
}
