package org.uppower.sevenlion.web.order.server.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.web.order.server.config.AliPayProperties;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/3 5:29 下午
 */
@Service
@Slf4j
public class AliPayService {

    @Autowired
    private AlipayTradePagePayRequest alipayRequest;

    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private AliPayProperties aliPayProperties;

    public String createOrderPC(Map<String,String> map) {
        String biz = "{";
        int i = 0;
        for (String key : map.keySet()) {
            i++;
            biz = biz +"\""+key+"\":\"" + map.get(key) + "\"";
            if (i!=map.size()) {
                biz+=",";
            }
        }
        biz+="}";
        alipayRequest.setBizContent(biz);
        String result = null;
        try {
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return result;
    }

    public CommonResult<Map<String,String>> parseRequest(HttpServletRequest request) {
        try{
            Map<String,String> params = new HashMap<String,String>();
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                params.put(parameterName, request.getParameter(parameterName));
            }
            String outTradeNo = request.getParameter("out_trade_no");
            //调用SDK验证签名
            boolean check = AlipaySignature.rsaCheckV1(params, aliPayProperties.getPublicKey(), "utf-8", "RSA2");
            if (check) {
               log.info("验证成功！");
               log.info("订单号为:" + outTradeNo);
               return CommonResult.success(params);
            }else {
                log.error("签名验证失败！");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.failed("验证失败！");
    }
}
