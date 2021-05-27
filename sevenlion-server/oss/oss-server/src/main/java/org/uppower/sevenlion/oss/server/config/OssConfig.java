package org.uppower.sevenlion.oss.server.config;

import io.minio.MinioClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.uppower.sevenlion.oss.common.minio.MinioTemplate;
import org.uppower.sevenlion.oss.common.oss.OssTemplate;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 5:02 下午
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
@Slf4j
public class OssConfig {

    @Autowired
    private OssProperties properties;


    @Bean
    @SneakyThrows
    @ConditionalOnProperty(prefix = "oss", name = "minio")
    public OssTemplate minioTemplate() {
        OssTemplate ossTemplate = new MinioTemplate(new MinioClient(
                properties.getUrl(),
                properties.getAccessKey(),
                properties.getSecretKey()
        ));
        log.info("minioTemplate服务启动成功");
        return ossTemplate;
    }
}
