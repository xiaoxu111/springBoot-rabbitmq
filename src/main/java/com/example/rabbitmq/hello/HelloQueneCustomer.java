package com.example.rabbitmq.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 * springBoot整合rabbitmq 第一种模型队列 quene
 * @RabbitListener 与 @RabbitHandler
 * @RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用
 * @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型
 * <p>
 * 使用 @RabbitListener 注解标记方法，当监听到队列 debug 中有消息时则会进行接收并处理
 * @RabbitListener(queues = "debug")
 * public void processMessage1(Message bytes) {
 * System.out.println(new String(bytes));
 * }
 */
@Component
@RabbitListener(queuesToDeclare = @Queue("hello-springboot-rabbitmq"))
public class HelloQueneCustomer {
    @RabbitHandler
    public void receive1(String message) {
        System.out.println("message = " + message);
    }

}
