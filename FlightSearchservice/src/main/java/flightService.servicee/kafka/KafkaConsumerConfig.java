package flightService.servicee.kafka;

/*
import flightService.servicee.Entity.FlightCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer2;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FlightCreatedEvent> flightCreatedEventListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FlightCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(flightCreatedEventConsumerFactory());
        return factory;
    }

    private ConsumerFactory<String, FlightCreatedEvent> flightCreatedEventConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        // Configure Kafka consumer properties, e.g., bootstrap servers
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "your-kafka-bootstrap-servers");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "flight-search-service-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer2.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        //props.put(ErrorHandlingDeserializer2.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, FlightCreatedEvent.class.getName());
        return new DefaultKafkaConsumerFactory<>(props);
    }
}
*/