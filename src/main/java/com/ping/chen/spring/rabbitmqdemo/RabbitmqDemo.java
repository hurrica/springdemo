package com.ping.chen.spring.rabbitmqdemo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitmqDemo {
    private static final String exchange = "exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        publisher();
        consumer();
    }

    private static void consumer() throws IOException, TimeoutException{
        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接工厂连接地址（默认端口号为5672）
        connectionFactory.setHost("127.0.0.1");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //设置交换器
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT.getType());
        String routingKey = "key";
        String queueName = "myQueue";

        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchange, routingKey);
        final Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException
            {
                String msg = new String(body, "UTF-8");
                System.out.println(msg);
            }
        };
        channel.basicConsume(queueName, true, consumer);

    }

    private static void publisher() throws IOException, TimeoutException{
        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接工厂连接地址（默认端口号为5672）
        connectionFactory.setHost("127.0.0.1");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        //设置交换器
        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT.getType());

        //连接关闭时执行
        connection.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                //TODO
            }
        });

        //信道关闭时通知
        channel.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                //TODO
            }
        });

        //失败监听
        channel.addReturnListener(new ReturnListener() {
            @Override
            public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //TODO
            }
        });

        String routingKey = "key";
        try {
            String msg = "hello rabbitmq";
            channel.basicPublish(exchange, routingKey, true,null, msg.getBytes());
        } finally {
            channel.close();
        }
        connection.close();
    }
}
