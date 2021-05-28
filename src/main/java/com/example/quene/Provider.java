package com.example.quene;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuyayuan
 * 直连模型 生产者生产消息
 * @data 2021年05月28日
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
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
        // 通道绑定相应的消息队列
        // 参数1 队列的名称 如果队列不存在则自动创建
        // 参数二 用来定义队列是否持久化 true 持久化队列 false 不持久化 , 如果队列没有持久化，那么当rabbitmq重启的时候队列会丢失
        // 参数三 exclusive 是否独占队列  true 独占队列  false 不独占 如果设置为true 则该队列只能有当前通道绑定，如果其他通道绑定会报错 推荐为false
        // 参数四 autoDelete 是否在消费者消费完成后自动删除队列（消费者断开连接后进行删除队列）  true 自动删除  false 不自动删除
        // 参数五 额外附件参数
        channel.queueDeclare("hello", false, false, false, null);

        /*注意: 保持消息和队列都不丢失当rabbitmq重启的时候需要设置 queueDeclare()的durable为true，
         *同时设置 basicPublish()的 props为MessageProperties.PERSISTENT_TEXT_PLAIN
         *注意： 生产者和消费者他们两者的参数要一直
         *
         */

        // 发布消息
        // 参数一 交换机名称
        // 参数二 队列名称
        // 参数三 传递消息额外设置
        // 参数四 消息的具体内容
        channel.basicPublish("", "hello", null, "hello rabbitMq".getBytes());
        // 关闭通道
        channel.close();
        // 关闭连接对象
        connection.close();
    }
}
