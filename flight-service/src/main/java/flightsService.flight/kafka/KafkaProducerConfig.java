package flightService.service.kafka;
/*
// KafkaProducerConfig class in your Flight microservice
import flightService.servicee.Entity.FlightCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, FlightCreatedEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        // Configure Kafka producer properties, e.g., bootstrap servers
        configProps.put("bootstrap.servers", "your-kafka-bootstrap-servers");
        configProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        configProps.put("value.serializer", JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, FlightCreatedEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
*/
