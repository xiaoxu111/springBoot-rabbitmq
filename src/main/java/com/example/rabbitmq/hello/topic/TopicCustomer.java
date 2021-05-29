package com.example.rabbitmq.hello.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 * rabbitmq 第五种模型，路由之 topic模型
 */
@Component
public class TopicCustomer {

    /**
     * rabbitmq 第五种模型，路由之 topic 动态路由
     * 消费者1
     * @author xuyayuan
     * @date 2021/5/29 18:03
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, // 创建临时队列
            key = {"admin.*"}, // 模糊匹配路由
            exchange = @Exchange(name = "topic-springBoot", type = "topic") //指定交换机名称和类型
    ))
    public void revice1(String message) {
        System.out.println("message 1 : " + message);
    }

    /**
     * rabbitmq 第五种模型，路由之 topic 动态路由
     * 消费者2
     * @author xuyayuan
     * @date 2021/5/29 18:04
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, // 创建临时队列
            key = {"admin"}, // 模糊匹配路由
            exchange = @Exchange(name = "topic-springBoot", type = "topic") //指定交换机名称和类型
    ))
    public void revice2(String message) {
        System.out.println("message 2 : " + message);
    }

    /**
     * rabbitmq 第五种模型，路由之 topic 动态路由
     * 消费者3
     * @author xuyayuan
     * @date 2021/5/29 18:05
     * @param message 
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, // 创建临时队列
            key = {"admin.#"}, // 模糊匹配路由
            exchange = @Exchange(name = "topic-springBoot", type = "topic") //指定交换机名称和类型
    ))
    public void revice3(String message) {
        System.out.println("message 3 : " + message);
    }
}
