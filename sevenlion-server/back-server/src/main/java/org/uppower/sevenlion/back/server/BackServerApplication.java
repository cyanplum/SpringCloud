package org.uppower.sevenlion.back.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
 * @date 2021/7/8 5:14 下午
 */
@EnableSevenlionSecurity
@EnableSevenlionCode
@MapperScan("org.uppower.sevenlion.back.server.mapper")
@SpringBootApplication
public class BackServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackServerApplication.class, args);
    }
}
