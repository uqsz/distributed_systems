import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Technician extends Names {

    public static void main(String[] argv) throws Exception {
        int number = 0;
        if (argv.length != 1) {
            System.err.println("Usage: java Doctor <number>");
            System.exit(1);
        }
        String arg = argv[0];
        try {
            number = Integer.parseInt(arg);
            if (number == 1) {
                System.out.println("Hello Technician 1 ");

            } else {
                System.out.println("Hello Technician 2 ");
            }
        } catch (NumberFormatException e) {
            System.err.println("Argument is not a valid integer: " + arg);
            System.exit(1);
        }
        // info
        System.out.println("Technician is starting work!");

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchanges
        channel.exchangeDeclare(EXAMINATION_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(RESULTS_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);


        // consumer (message handling)
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                String replyKey = properties.getHeaders().get("replyKeyName").toString();
                System.out.println("[EXAMINATION_REQUEST]: " + replyKey + "|" + message);
                channel.basicPublish(RESULTS_EXCHANGE_NAME, replyKey, null, (replyKey + "|" + message + "|DONE").getBytes(StandardCharsets.UTF_8));
            }
        };
        // queues

        channel.queueDeclare(QUEUE_HIP_NAME, true, false, false, null);
        channel.queueBind(QUEUE_HIP_NAME, EXAMINATION_EXCHANGE_NAME, KEY_HIP);
        channel.basicConsume(QUEUE_HIP_NAME, true, consumer);

        if (number == 2) {
            channel.queueDeclare(QUEUE_ELBOW_NAME, true, false, false, null);
            channel.queueBind(QUEUE_ELBOW_NAME, EXAMINATION_EXCHANGE_NAME, KEY_ELBOW);
            channel.basicConsume(QUEUE_ELBOW_NAME, true, consumer);
        } else {
            channel.queueDeclare(QUEUE_KNEE_NAME, true, false, false, null);
            channel.queueBind(QUEUE_KNEE_NAME, EXAMINATION_EXCHANGE_NAME, KEY_KNEE);
            channel.basicConsume(QUEUE_KNEE_NAME, true, consumer);
        }
    }
}
