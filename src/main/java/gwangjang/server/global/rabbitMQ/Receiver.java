package gwangjang.server.global.rabbitMQ;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Receiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true"), //
            key = "test"))
    public void handleMsg1(Email in) {
        System.out.println(in.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "kkaok", durable = "true"),
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true"), //
            key = "test.0001"))
    public void handleMsg2(Email in) {
        System.out.println(in.toString());
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "subscribe.data.queue", durable = "true"),
            exchange = @Exchange(value = "amq.topic", type = "topic", durable = "true"), //
            key = "subscribe.data"))
    public void handleSubscribeData(SubscribeDataMessage message) {
        List<SubscribeData> subscribeDataList = message.getSubscribeDataList();
        // Process the received subscribeDataList
        for (SubscribeData subscribeData : subscribeDataList) {
            System.out.println(subscribeData.toString());
            // Add your logic to handle each SubscribeData object
        }
    }

}
