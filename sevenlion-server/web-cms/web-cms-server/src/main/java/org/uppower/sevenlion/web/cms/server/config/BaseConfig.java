package org.uppower.sevenlion.web.cms.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.uppower.sevenlion.web.cms.server.metabase.MetabaseService;
import org.uppower.sevenlion.web.cms.server.provider.metabase.MysqlMetabaseProvider;
import org.uppower.sevenlion.web.cms.server.provider.metabase.RedisMetabaseProvider;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/9 5:24 下午
 */
@Configuration
public class BaseConfig {


    @Bean
    public MetabaseService mysqlMetabaseService(MysqlMetabaseProvider mysqlMetabaseProvider) {
        MetabaseService metabaseService = new MetabaseService(mysqlMetabaseProvider);
        return metabaseService;
    }

    @Bean
    public MetabaseService redisMetabaseService(RedisMetabaseProvider redisMetabaseProvider) {
        MetabaseService metabaseService = new MetabaseService(redisMetabaseProvider);
        return metabaseService;
    }
}
