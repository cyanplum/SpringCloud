package org.uppower.sevenlion.back.user.server.config;

import org.springframework.context.annotation.Configuration;
import org.uppower.sevenlion.security.common.UrlMatcherRegistry;
import org.uppower.sevenlion.security.common.UrlStrategyMatcher;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/24 6:03 下午
 */
@Configuration
public class WebSecurityConfig implements UrlStrategyMatcher {

    @Override
    public void handleUrl(UrlMatcherRegistry urlMatcherRegistry) {
        urlMatcherRegistry.ignoreUrl(
                "/favicon.ico",
                "/webjars/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/static/**",
                "/route/**",
                "/swagger-resources/**",
                "/code/image");
    }


}
