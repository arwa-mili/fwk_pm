package ChatService.Chat.Controller;


import ChatService.Chat.kafka.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/chat")
public class ChatController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return "Message sent to Kafka: " + message;
    }
}