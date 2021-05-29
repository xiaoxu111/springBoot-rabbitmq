package com.example.direct;

import com.example.quene.util.ConnectionRabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 */
public class Customer2 {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = ConnectionRabbitMqUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 通过通道绑定交换机
        channel.exchangeDeclare("dirct_order", "direct");
        // 创建临时队列
        String queneName = channel.queueDeclare().getQueue();
        // 交换机绑定队列
        channel.queueBind(queneName, "dirct_order", "error" );
        channel.queueBind(queneName, "dirct_order", "info" );
        // 消费者消费消息
        channel.basicConsume(queneName, true, new DefaultConsumer(channel){
            @Override // 最后一个参数消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者 -2 消费的消息:" + new String(body));
            }
        });
    }
}
