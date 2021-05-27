//package org.uppower.sevenlion.web.user.server.service;
//
//import org.springframework.amqp.rabbit.annotation.*;
//import org.springframework.stereotype.Service;
//
///**
// * @author create by:
// * *      ____        ___  ___       __          __
// * *    /  _  \     /   |/   |      | |        / /
// * *   | | | |     / /|   /| |     | |  __   / /
// * *  | | | |     / / |__/ | |    | | /  | / /
// * * | |_| |_    / /       | |   | |/   |/ /
// * * \_______|  /_/        |_|  |___/|___/
// * @date 2021/5/20 11:18 下午
// */
//@Service
//public class RabbitMqService {
//
//    @RabbitHandler
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("sms.direct.queue"),
//            exchange = @Exchange("direct_order_exchange"),
//            key = ("sms")
//    ))
//    public void receiveSmsDirectMessage(String message) {
//        System.out.println("sms direct.queue ----> 订单id为 " + message);
//    }
//
//    @RabbitHandler
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("email.direct.queue"),
//            exchange = @Exchange("direct_order_exchange"),
//            key = ("email")
//    ))
//    public void receiveEmailDirectMessage(String message) {
//        System.out.println("email direct.queue ----> 订单id为 " + message);
//    }
//
//    @RabbitHandler
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("sms.fanout.queue"),
//            exchange = @Exchange(value = "fanout_order_exchange",type = "fanout")
//    ))
//    public void receiveSmsFanoutMessage(String message) {
//        System.out.println("sms fanout.queue ----> 订单id为 " + message);
//    }
//
//    @RabbitHandler
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("email.fanout.queue"),
//            exchange = @Exchange(value = "fanout_order_exchange",type = "fanout")
//    ))
//    public void receiveEmailFanoutMessage(String message) {
//        System.out.println("email fanout.queue ----> 订单id为 " + message);
//    }
//
//}
