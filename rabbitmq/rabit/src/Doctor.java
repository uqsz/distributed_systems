import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Doctor extends Names {

    public static void main(String[] argv) throws Exception {
        String queueName = null;
        String queueKey = null;
        if (argv.length != 1) {
            System.err.println("Usage: java Doctor <number>");
            System.exit(1);
        }
        String arg = argv[0];
        try {
            int number = Integer.parseInt(arg);
            if (number == 1) {
                System.out.println("Hello Doctor 1: ");
                queueName = QUEUE_DOCTOR1_NAME;
                queueKey = KEY_DOCTOR_1;
            } else {
                System.out.println("Hello Doctor 2: ");
                queueName = QUEUE_DOCTOR2_NAME;
                queueKey = KEY_DOCTOR_2;
            }
        } catch (NumberFormatException e) {
            System.err.println("Argument is not a valid integer: " + arg);
            System.exit(1);
        }

        // connection & channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // exchange
        channel.exchangeDeclare(EXAMINATION_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(RESULTS_EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        // queue
        channel.queueDeclare(queueName, true, false, false, null);
        channel.queueBind(queueName, RESULTS_EXCHANGE_NAME, queueKey);

        // header
        Map<String, Object> headers = new HashMap<>();
        headers.put("replyKeyName", queueKey);
        AMQP.BasicProperties properties = new AMQP.BasicProperties.Builder()
                .headers(headers)
                .build();

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, StandardCharsets.UTF_8);
                System.out.println("Received: " + message);
            }
        };

        channel.basicConsume(queueName, true, consumer);

        Random random = new Random();
        while (true) {
            int index = random.nextInt(3);

            int delay = random.nextInt(10000) + 4000;
            int patientID = random.nextInt(100000);
            switch (index) {
                case 0:
                    System.out.println("[EXAMINATION] ELBOW for patient " + patientID);
                    channel.basicPublish(EXAMINATION_EXCHANGE_NAME, KEY_ELBOW, properties, ("Elbow|" + patientID).getBytes(StandardCharsets.UTF_8));
                    break;
                case 1:
                    System.out.println("[EXAMINATION] HIP for patient " + patientID);
                    channel.basicPublish(EXAMINATION_EXCHANGE_NAME, KEY_HIP, properties, ("Hip|" + patientID).getBytes(StandardCharsets.UTF_8));
                    break;
                case 2:
                    System.out.println("[EXAMINATION] KNEE for patient " + patientID);
                    channel.basicPublish(EXAMINATION_EXCHANGE_NAME, KEY_KNEE, properties, ("Knee|" + patientID).getBytes(StandardCharsets.UTF_8));
                    break;
            }

            Thread.sleep(delay);
        }
    }
}
