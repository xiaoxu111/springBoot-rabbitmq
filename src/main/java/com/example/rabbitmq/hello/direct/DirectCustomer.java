package com.example.rabbitmq.hello.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author xuyayuan
 * @data 2021年05月29日
 * rabbitmq 第四种模式，路由模式 direct
 */
@Component
public class DirectCustomer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, // 创建临时队列
            key = {"info", "error"}, // 路由key
            exchange = @Exchange(name = "direct-springBoot", type = "direct") // 指定交换机名称和类型
    )) // 让队列和交换机进行绑定
    public void revice1(String message){
        System.out.println("message 1 : " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, // 创建临时队列
            key = {"info", "error", "warn"}, // 路由key
            exchange = @Exchange(name = "direct-springBoot", type = "direct") // 指定交换机名称和类型
    )) // 让队列和交换机进行绑定
    public void revice2(String message){
        System.out.println("message 2 : " + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue, // 创建临时队列
            key = {"error"}, // 路由key
            exchange = @Exchange(name = "direct-springBoot", type = "direct") // 指定交换机名称和类型
    )) // 让队列和交换机进行绑定
    public void revice3(String message){
        System.out.println("message 3 : " + message);
    }
}
