package org.uppower.sevenlion.web.order.server.config;

import org.springframework.amqp.core.*;
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
 * @date 2021/5/17 9:49 下午
 */
@Configuration
public class RabbitMqConfig {


    //创建fanout模式的交换机
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout_order_exchange",true,false);
    }

    //创建direct模式交换机
    @Bean
    public DirectExchange directExchange() {
        return (DirectExchange) ExchangeBuilder.directExchange("direct_order_exchange").durable(true).build();
        //相同的效果
        //return new DirectExchange("direct_order_exchange",true,false);
    }
    //创建两个队列
    @Bean
    public Queue smsFanoutQueue() {
        return new Queue("sms.fanout.queue",true);
    }
    @Bean
    public Queue emailFanoutQueue() {
        return new Queue("email.fanout.queue",true);
    }
    //创建两个队列
    @Bean
    public Queue smsDirectQueue() {
        return QueueBuilder.durable("sms.direct.queue").build();
        //相同效果
        //return new Queue("sms.direct.queue",true);
    }
    @Bean
    public Queue emailDirectQueue() {
        return new Queue("email.direct.queue",true);
    }
    //将交换机与队列进行绑定
    @Bean
    public Binding smsFanoutBinding() {
        return BindingBuilder.bind(smsFanoutQueue()).to(fanoutExchange());
    }
    @Bean
    public Binding emailFanoutBinding(){
        return BindingBuilder.bind(emailFanoutQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding smsDirectBinding() {
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }
    @Bean
    public Binding emailDirectBinding() {
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email");
    }
}
