package ChatService.Chat.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "chat_topic", groupId = "group1")
    public void consumeMessage(String message) {
        System.out.println("Received message: " + message);

    }
}