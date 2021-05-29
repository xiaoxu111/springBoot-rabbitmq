package com.example.topic;

import com.example.quene.util.ConnectionRabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 * 第五种模型 Routing 之订阅模型-Topic
 * Topic类型的Exchange与Direct相比，都是可以根据RoutingKey把消息路由到不同的队列。
 * 只不过Topic类型Exchange可以让队列在绑定Routing key的时候使用通配符！这种模型Routingkey
 * 一般都是由一个或多个单词组成，多个单词之间以”.”分割，例如： item.insert
 * <p>
 *  # 统配符
 *      * (star) can substitute for exactly one word.    匹配不多不少恰好1个词
 *      # (hash) can substitute for zero or more words.  匹配一个或多个词
 *  # 如:
 *      audit.#    匹配audit.irs.corporate或者 audit.irs 等
 *      audit.*   只能匹配 audit.irs
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = ConnectionRabbitMqUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 根据通道声明交换机
        channel.exchangeDeclare("topic_order", "topic");
        // 路由键
        String keyRoute = "user.save";
        System.out.println("keyRoute : " + keyRoute);
        // 通过通道和 路由key发布消息
        channel.basicPublish("topic_order", keyRoute, null,
                ("第五种模型 Routing 之订阅模型-Topic keyRoute： 【" + keyRoute + "】").getBytes());
        // 关闭资源
        ConnectionRabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }
}
