package org.uppower.sevenlion.web.order.server.config;

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
 * @date 2021/4/3 1:57 下午
 */
@Data
@ConfigurationProperties(prefix = "alipay")
public class AliPayProperties {

    private String appId = "";

    private String privateKey = "";

    private String protocol = "";

    private String gatewayHost = "";

    private String publicKey = "";

    private String notifyUrl = "";

    private String returnUrl = "";
}
