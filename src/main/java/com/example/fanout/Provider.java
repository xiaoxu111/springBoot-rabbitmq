package com.example.fanout;

import com.example.quene.util.ConnectionRabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author xuyayuan
 * @data 2021年05月28日
 * 在广播模式下，消息发送流程是这样的： fanout
 * <p>
 * 可以有多个消费者
 * 每个消费者有自己的queue（队列）
 * 每个队列都要绑定到Exchange（交换机）
 * 生产者发送的消息，只能发送到交换机，交换机来决定要发给哪个队列，生产者无法决定。
 * 交换机把消息发送给绑定过的所有队列
 * 队列的消费者都能拿到消息。实现一条消息被多个消费者消费
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = ConnectionRabbitMqUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 通过通道声明交换机
        channel.exchangeDeclare("orderFanOut", "fanout"); //广播，一条消息多个消费者同时消费
        // 发布消息
        channel.basicPublish("orderFanOut", "", null, "hello-fanout".getBytes());
        // 关闭资源
        ConnectionRabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }
}
