//package org.uppower.sevenlion.web.order.server.service;
//
//import cn.sevenlion.utils.response.CommonResult;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.uppower.sevenlion.web.order.common.enums.PayStatusEnum;
//import org.uppower.sevenlion.web.order.common.model.entity.AlipayLogEntity;
//import org.uppower.sevenlion.web.order.common.model.entity.OrderEntity;
//import org.uppower.sevenlion.web.order.common.model.jsonobject.ProductSnapObject;
//import org.uppower.sevenlion.web.order.common.model.result.CartResult;
//import org.uppower.sevenlion.web.order.common.model.vo.OrderVo;
//import org.uppower.sevenlion.web.order.common.utils.RedisUtils;
//import org.uppower.sevenlion.web.order.common.utils.TradeOrderNoGenerator;
//import org.uppower.sevenlion.web.order.dao.AliPayLogMapper;
//import org.uppower.sevenlion.web.order.dao.OrderMapper;
//import org.uppower.sevenlion.web.pms.common.client.ProductFeignClient;
//import org.uppower.sevenlion.web.pms.common.model.bo.ProductOrderBo;
//import org.uppower.sevenlion.web.pms.common.model.vo.ProductDetailVo;
//
//
//import java.util.*;
//import java.util.function.Function;
//import java.util.stream.Collectors;
//
///**
// * @author create by:
// * *      ____        ___  ___       __          __
// * *    /  _  \     /   |/   |      | |        / /
// * *   | | | |     / /|   /| |     | |  __   / /
// * *  | | | |     / / |__/ | |    | | /  | / /
// * * | |_| |_    / /       | |   | |/   |/ /
// * * \_______|  /_/        |_|  |___/|___/
// * @date 2021/4/22 11:33 上午
// */
//@Service
//public class OrderManageService {
//
//    @Autowired
//    private OrderMapper orderMapper;
//
//    @Autowired
//    private ProductFeignClient productFeignClient;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Autowired
//    private AliPayLogMapper aliPayLogMapper;
//
//    @Autowired
//    private AliPayService aliPayService;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public static final String PRODUCT_STOCK = "productStock";
//
//
//
//    /**
//     * 提交订单
//     * @param vo
//     * @return
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public CommonResult order(OrderVo vo) {
//        //得到商品信息
//        CommonResult<List<ProductDetailVo>> productList = productFeignClient.getProductList(vo.getProductOrders()
//                        .stream()
//                        .map(ProductOrderBo::getProductId)
//                        .collect(Collectors.toList()),
//                null);
//        if (!productList.isSuccess() && productList.getData().isEmpty()) {
//            throw new RuntimeException("商品获取错误！");
//        }
//        Map<Long, ProductDetailVo> productMap = productList.getData().stream().collect(Collectors.toMap(ProductDetailVo::getId, Function.identity()));
//        //准备生成的订单
//        List<OrderEntity> insertOrderList = new ArrayList<>(vo.getProductOrders().size());
//        //预生成订单
//        vo.getProductOrders().forEach(it -> {
//            //判断数据库中库存是否充足
//            if (it.getNumber() > productMap.get(it.getProductId()).getStock()) {
//                throw new RuntimeException("库存不足");
//            }
//            //预生成订单信息
//            Long tradeOrderNo = TradeOrderNoGenerator.snowflakeId();
//            ProductDetailVo productDetailVo = productMap.get(it.getProductId());
//            OrderEntity orderEntity = new OrderEntity();
//            BeanUtils.copyProperties(it,orderEntity);
//            orderEntity.setAddress(vo.getAddress());
//            orderEntity.setTradeOrderNo(tradeOrderNo);
//            orderEntity.setCount(it.getNumber());
//            orderEntity.setPrice(productDetailVo.getSpecialPrice()*it.getNumber());
//            orderEntity.setUserId(vo.getId());
//            ProductSnapObject productSnapObject = new ProductSnapObject();
//            BeanUtils.copyProperties(productDetailVo,productSnapObject);
//            orderEntity.setSnapProductInfo(productSnapObject);
//            insertOrderList.add(orderEntity);
//        });
//        //1.redis 分布式锁扣减库存
//        try{
//            if (!RedisUtils.tryLock(PRODUCT_STOCK,36000)) {
//                throw new RuntimeException("库存查询失败！");
//            }
//            //2.扣库存
//            List<ProductOrderBo> productOrders = vo.getProductOrders();
//            CommonResult commonResult = productFeignClient.cutProductStock(productOrders);
//            if (!commonResult.isSuccess()) {
//                throw new RuntimeException("扣减库存失败");
//            }
//        }catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            //3.释放锁
//            RedisUtils.delete(PRODUCT_STOCK);
//        }
//        //从购物车下单,更新购物车
//        if (vo.getFlag() == 1) {
//            vo.getProductOrders().forEach(it -> {
//                Object product = redisTemplate.opsForHash().get(vo.getId().toString(), it.getProductId().toString());
//                CartResult cartResult = null;
//                try {
//                    cartResult = objectMapper.readValue(objectMapper.writeValueAsString(product), CartResult.class);
//                } catch (Exception e) {
//                }
//                if (cartResult != null) {
//                    if (cartResult.getNumber() - it.getNumber() > 0) {
//                        redisTemplate.opsForHash().put(vo.getId().toString(),it.getProductId().toString(),cartResult);
//                    }else {
//                        redisTemplate.opsForHash().delete(vo.getId().toString(),it.getProductId().toString());
//                    }
//                }
//            });
//        }
//        if (orderMapper.insertList(insertOrderList) != insertOrderList.size()) {
//            throw new RuntimeException("订单生成失败！");
//        }
//        List<Long> collectOrderNo = insertOrderList.stream().map(it -> it.getTradeOrderNo()).collect(Collectors.toList());
//        String orderNo = null;
//        try {
//            orderNo = objectMapper.writeValueAsString(collectOrderNo);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        Long money = 0L;
//        for (OrderEntity orderEntity : insertOrderList) {
//            money += orderEntity.getPrice();
//        }
//        //生成支付记录
//        AlipayLogEntity alipayLogEntity = new AlipayLogEntity();
//        alipayLogEntity.setStatus(PayStatusEnum.FAILED.getStatus());
//        alipayLogEntity.setTradeOrderNo(orderNo);
//        alipayLogEntity.setAmount(money);
//        alipayLogEntity.setUserId(vo.getId());
//        Long businessOrderNo = TradeOrderNoGenerator.snowflakeId();
//        alipayLogEntity.setBusinessOrderNo(businessOrderNo);
//        if (aliPayLogMapper.insert(alipayLogEntity) != 1) {
//            throw new RuntimeException("系统错误,请稍后再试");
//        }
//        //调用支付宝
//        Map<String,String> map = new HashMap<>();
//        map.put("out_trade_no",businessOrderNo.toString());
//        map.put("subject","测试");
//        map.put("body","xx");
//        map.put("total_amount",money.toString());
//        map.put("product_code","FAST_INSTANT_TRADE_PAY");
//        String orderPC = null;
//        try {
//            orderPC = aliPayService.createOrderPC(map);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return CommonResult.success(orderPC);
//    }
//
//    public CommonResult fanout() {
//        String orderId = UUID.randomUUID().toString();
//        String exchangeName = "fanout_order_exchange";
//        String routingKey = "";
//        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
//        return CommonResult.success();
//    }
//
//    public CommonResult direct(String routingKey) {
//        String orderId = UUID.randomUUID().toString();
//        String exchangeName = "direct_order_exchange";
//        //String routingKey = "email";
//        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
//        return CommonResult.success();
//    }
//}
