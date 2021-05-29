package com.example.rabbitmq.hello;

import com.example.SpringBootRabbitmqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 * @SpringBootTest Spring将加载所有被管理的bean，基本等同于启动了整个服务，此时便可以开始功能测试
 * @RunWith 当一个类用@RunWith注释或继承一个用@RunWith注释的类时，JUnit将调用它所引用的类来运行该类中的测试而不是开发者去在junit内部去构建它
 */
@SpringBootTest(classes = SpringBootRabbitmqApplication.class)
@RunWith(SpringRunner.class)
public class HelloQuene {

    // 注入rabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * rabbitmq 第一种模型 队列
     * @author xuyayuan
     * @date 2021/5/29 16:34
     */
    @Test
    public void testHelloQuene() {
        // rabbitmq第一种模型 队列 生产者
        rabbitTemplate.convertAndSend("hello-springboot-rabbitmq", "hello-springboot");
    }

    /**
     * rabbitmq 第二种模型  work队列
     * @author xuyayuan
     * @date 2021/5/29 16:34
     */
    @Test
    public void testWorkQuene() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work-springboot-rabbitmq", "workHello-springBoot" + "【" + i + "】");
        }
    }

}