package org.uppower.sevenlion.web.order.server.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.uppower.sevenlion.common.utils.CommonResult;
import org.uppower.sevenlion.web.order.common.model.vo.OrderVo;
import org.uppower.sevenlion.web.order.server.service.OrderManageService;
import org.uppower.sevenlion.web.product.common.client.ProductFeignClient;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/22 11:31 上午
 */
@RestController
@RequestMapping("/order")
public class OrderManageController {

    @Autowired
    private OrderManageService orderManageService;

    @Autowired
    private ProductFeignClient productFeignClient;


    @Autowired
    private RabbitTemplate rabbitTemplate;
    /**
     * 提交订单
     * @param vo
     * @return
     */
    @SentinelResource(value = "order")
    @PostMapping
    public CommonResult order(@RequestBody OrderVo vo) {
        return orderManageService.order(vo);
    }

    /**
     * MQ fanout模式发送消息
     * @return
     */
    @GetMapping("/fanout")
    public CommonResult fanout() {
        return orderManageService.fanout();
    }

    @GetMapping("/direct")
    public CommonResult direct(@RequestParam String routingKey) {
        return orderManageService.direct(routingKey);
    }

    @GetMapping("/productIndex")
    @SentinelResource("productIndex")
    public CommonResult productIndex() {
        return productFeignClient.getProductList(null,null);
    }


}
