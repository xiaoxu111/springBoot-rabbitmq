package com.example.workquene;/**
 * package_name:com.example.workquene
 *
 * @author:徐亚远 Date:2021/5/28 21:50
 * 项目名:springBoot-rabbitmq
 * Description:TODO
 * Version: 1.0
 **/

import com.example.quene.util.ConnectionRabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author xuyayuan
 * @data 2021年05月28日
 */
public class CustomerQuickly {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = ConnectionRabbitMqUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 通过通道绑定消费的队列
        channel.queueDeclare("work", true, false, false, null);

        // rabbitmq 自动确认机制，能者多劳，避免平均分配，消息堆积现象
        // 第一步 在消费者中添加 channel.basicQos(1); 一次只接受一条未确认的消息
        // 第二步 关闭消息自动确认机制 autoAck 设置为false
        // 第三步 手动确认消息 channel.basicAck(envelope.getDeliveryTag(),false);

        channel.basicQos(1);
        // 消费消息
        channel.basicConsume("work", false, new DefaultConsumer(channel) {
            @Override // 最后一个参数消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2消费的消息:" + new String(body));
                // 手动确认消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
