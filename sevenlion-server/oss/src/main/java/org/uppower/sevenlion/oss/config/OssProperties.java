package org.uppower.sevenlion.oss.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/1/2 4:38 下午
 */
@Data
@ConfigurationProperties(prefix = "oss")
public class OssProperties {
    /**
     * 阿里云oss EndPoint
     * minioUrl
     */
    private String url;

    /**
     * cdn路径
     */
    private String cdnUrl;

    /**
     * 用户名
     */
    private String accessKey;

    /**
     * 密码
     */
    private String secretKey;
}
