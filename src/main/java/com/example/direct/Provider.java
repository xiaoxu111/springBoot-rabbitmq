package com.example.direct;

import com.example.quene.util.ConnectionRabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author xuyayuan
 * @data 2021年05月29日
 * 第四种模型 direct 路由模型
 * 在Fanout模式中，一条消息，会被所有订阅的队列都消费。但是，在某些场景下，我们希望不同的消息被不同的队列消费。这时就要用到Direct类型的Exchange。
 * <p>
 * 在Direct模型下：
 * 队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）
 * 消息的发送方在 向 Exchange发送消息时，也必须指定消息的 RoutingKey。
 * <p>
 * Exchange不再把消息交给每一个绑定的队列，而是根据消息的Routing Key进行判断，只有队列的Routingkey与消息的 Routing key完全一致，才会接收到消息
 * <p>
 * P：生产者，向Exchange发送消息，发送消息时，会指定一个routing key。
 * X：Exchange（交换机），接收生产者的消息，然后把消息递交给 与routing key完全匹配的队列
 * C1：消费者，其所在队列指定了需要routing key 为 error 的消息
 * C2：消费者，其所在队列指定了需要routing key 为 info、error、warning 的消息
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = ConnectionRabbitMqUtils.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 通过通道声明交换机 direct 路由类型 参数1:交换机名称 参数2:交换机类型 基于指令的Routing key转发
        channel.exchangeDeclare("direct_order", "direct");
        String keyRoute = "warn";
        // 通过通道发布消息
        channel.basicPublish("dirct_order", keyRoute, null, ("通过路由的方式发布消息 keyRoute : 【" + keyRoute + "】").getBytes());
        // 关闭资源
        ConnectionRabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }
}
