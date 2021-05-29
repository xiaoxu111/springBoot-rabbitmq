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

    @Test
    public void testHelloQuene() {
        // rabbitmq第一种模型 队列 生产者
        rabbitTemplate.convertAndSend("hello-springboot-rabbitmq", "hello-springboot");
    }

}
