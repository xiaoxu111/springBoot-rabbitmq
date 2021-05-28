package com.example.quene;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuyayuan
 * 消费者消费消息 直连模型
 * @data 2021年05月28日
 */
public class Customer {

    public static void main(String[] args) throws IOException {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置rabbitmq主机
        connectionFactory.setHost("192.168.0.171");
        // 设置端口号
        connectionFactory.setPort(5672);
        // 设置虚拟主机的用户
        connectionFactory.setUsername("xuyy");
        // 设置虚拟主机的密码
        connectionFactory.setPassword("123456");
        // 设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/xuyy");
        // 建立连接
        Connection connection = null;
        try {
            // 获取连接对象
            connection = connectionFactory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        // 创建通道
        Channel channel = null;
        try {
            // 获取通道
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 通道绑定对象
        channel.queueDeclare("hello", false, false, false, null);

        // 消费消息
        // 参数一 消费那个队列的消息
        // 参数二 是否开启消息的自动确认
        // 参数三 消费时的回调接口
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            @Override // 最后一个参数消息队列中取出的消息
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者消费的消息:" + new String(body));
            }
        });
    }
}
