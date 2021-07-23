package org.uppower.sevenlion.web.cms.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.uppower.sevenlion.security.annotation.EnableSevenlionSecurity;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 3:50 下午
 */
@SpringBootApplication
@MapperScan("org.uppower.sevenlion.web.cms.dao")
@EnableCaching
@EnableSevenlionSecurity
public class WebCmsServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebCmsServerApplication.class, args);
    }
}
