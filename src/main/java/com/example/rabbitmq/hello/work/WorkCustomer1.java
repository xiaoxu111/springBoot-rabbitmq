package com.example.rabbitmq.hello.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 * work模型 说明:默认在Spring AMQP实现中Work这种方式就是公平调度,如果需要实现能者多劳需要外配置
 */
@Component
public class WorkCustomer1 {

    @RabbitListener(queuesToDeclare = @Queue("work-springboot-rabbitmq"))
    public void revice1(String message) {
        System.out.println("work message1 = " + message);
    }

    @RabbitListener(queuesToDeclare = @Queue("work-springboot-rabbitmq"))
    public void revice2(String message) {
        System.out.println("work message2 = " + message);
    }
}
