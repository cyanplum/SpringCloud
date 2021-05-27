//package org.uppower.sevenlion.web.user.server.service;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.annotation.RabbitListeners;
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
// * @date 2021/5/20 10:37 下午
// */
////@Service
//@RabbitListener(queues = "sms.fanout.queue")
//public class FanoutSmsService {
//
//    @RabbitHandler
//    public void receiveMessage(String message) {
//        System.out.println("sms fanout.queue ----> 订单id为 " + message);
//    }
//}
