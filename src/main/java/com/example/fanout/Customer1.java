package com.example.fanout;

import com.example.quene.util.ConnectionRabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 */
public class Customer1 {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = ConnectionRabbitMqUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 通过通道绑定交换机
        channel.exchangeDeclare("orderFanOut", "fanout");
        // 创建临时队列
        String queneName = channel.queueDeclare().getQueue();
        // 将临时队列和交换机进行绑定
        channel.queueBind(queneName, "orderFanOut", "");
        // 消费者消费消息
        channel.basicConsume(queneName, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                System.out.println("消费者 1 消费的消息:" + new String(body));
            }
        });
    }
}
