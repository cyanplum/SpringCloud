//package org.uppower.sevenlion.web.user.server.service;
//
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
//@RabbitListener(queues = "email.fanout.queue")
//public class FanoutEmailService {
//
//    @RabbitHandler
//    public void receiveMessage(String message) {
//        System.out.println("email fanout.queue ----> 订单id为 " + message);
//    }
//}
