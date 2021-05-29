package com.example.rabbitmq.hello.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.naming.Name;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 * rabbitmq第三种模型，广播fanout模型
 */
@Component
public class FanoutCustomer {

    /**
     * fanout模型 消费者1
     * @author xuyayuan
     * @date 2021/5/29 17:33
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "fanout-springBoot", type = "fanout")
    ))
    public void revice1(String message) {
        System.out.println("message 1 : " + message);
    }

    /**
     * fanout模型 消费者2
     * @author xuyayuan
     * @date 2021/5/29 17:33
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(name = "fanout-springBoot", type = "fanout")
    ))
    public void revice2(String message) {
        System.out.println("message 2 : " + message);
    }
}
