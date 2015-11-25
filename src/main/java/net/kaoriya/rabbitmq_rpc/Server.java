package net.kaoriya.rabbitmq_rpc;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.MessageProperties;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("Server!");

        ConnectionFactory f = new ConnectionFactory();
        f.setHost(Main.HOST);
        Connection connection = f.newConnection();
        final Channel ch = connection.createChannel();

        ch.queueDeclare(Main.SERVER_QUEUE, false, true, false, null);
        ch.basicConsume(Main.SERVER_QUEUE, true, new DefaultConsumer(ch) {
            @Override
            public void handleDelivery(
                    String consumerTag,
                    Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException
            {
                String replyTo = properties.getReplyTo();
                System.out.printf("replyTo: %s", replyTo);
                System.out.println();
                ch.basicPublish("", replyTo, MessageProperties.MINIMAL_BASIC,
                        "Hello client!".getBytes());
            }
        });
    }
}
