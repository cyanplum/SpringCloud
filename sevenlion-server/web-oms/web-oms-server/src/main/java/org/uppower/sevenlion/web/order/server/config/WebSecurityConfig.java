package org.uppower.sevenlion.web.order.server.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/22 2:19 下午
 */
@Configuration
@EnableConfigurationProperties(AliPayProperties.class)
public class WebSecurityConfig {

    @Autowired
    private AliPayProperties aliPayProperties;

    @Bean
    public AlipayClient alipayClient() {
        return new DefaultAlipayClient(
                aliPayProperties.getGatewayHost(),
                aliPayProperties.getAppId(),
                aliPayProperties.getPrivateKey(),
                "json",
                "utf-8",
                aliPayProperties.getPublicKey(),
                "RSA2");
    }

    @Bean
    public AlipayTradePagePayRequest alipayTradePagePayRequest() {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(aliPayProperties.getReturnUrl());
        alipayRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());
        return alipayRequest;
    }
}
