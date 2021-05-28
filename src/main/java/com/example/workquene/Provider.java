package com.example.workquene;

import com.example.quene.util.ConnectionRabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @author xuyayuan
 * @data 2021年05月28日
 * 总结:默认情况下 work quene 工作模型，RabbitMQ将按顺序将每个消息发送给下一个使用者。平均而言，每个消费者都会收到相同数量的消息。这种分发消息的方式称为循环。
 * 缺点：平均分配，可能会出现消息堆积，快的消费者执行完，而慢的消费者在一条条的执行消息
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        // 获取连接对象
        Connection connection = ConnectionRabbitMqUtils.getConnection();
        // 创建通道对象
        Channel channel = connection.createChannel();
        // 通过通道声明队列
        channel.queueDeclare("work", true, false, false, null);
        for (int i = 0; i < 20; i++) {
            // 发布消息
            channel.basicPublish("", "work", null, (i + "word-quene").getBytes());
        }
        // 关闭通道和连接对象
        ConnectionRabbitMqUtils.closeConnectionAndChannel(channel, connection);
    }
}
