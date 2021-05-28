package com.example.quene.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author xuyayuan
 * @data 2021年05月28日
 */
public class ConnectionRabbitMqUtils {

    private static ConnectionFactory factory = null;
    // 重量级资源 放在静态代码块中，在类加载的时候执行且只执行一次
    {
        factory = new ConnectionFactory();
        // 设置主机
        // 设置主机
        factory.setHost("192.168.0.171");
        // 设置端口号
        factory.setPort(5672);
        // 设置用户
        factory.setUsername("xuyy");
        // 设置密码
        factory.setPassword("123456");
        // 设置虚拟主机
        factory.setVirtualHost("/xuyy");
    }
    /**
     * 定义提供连接对象的方法
     * @author xuyy
     * @date 2021/5/28 16:42
     * @return com.rabbitmq.client.Connection
     */
    public static Connection getConnection() {
        try {
           Connection connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 提供关闭资源的方法
     * @author xuyy
     * @date 2021/5/28 16:44
     * @param connection
     * @param channel 
     */
    public static void closeConnectionAndChannel(Connection connection, Channel channel) {
        try {
            if (connection != null) connection.close();
            if (channel != null) channel.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    


}
