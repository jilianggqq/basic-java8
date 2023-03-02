package edu.gqq.gqqkafka;


import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class GQQKafkaProducerConfig {

    private final Logger logger = LoggerFactory.getLogger(GQQKafkaProducerConfig.class);

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    //    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        logger.debug("## generating producer - kafkaTemplate ##");
        return new KafkaTemplate<>(producerFactory());
    }

    /*
     *  we need to configure a ProducerFactory
     * which sets the strategy for creating Kafka Producer instances.
     */
    // should not be a bean, because only greetingKafkaTemplate is using it.
//    @Bean
    public ProducerFactory<String, GQQGreeting> greetingProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    /*
    Then we need a KafkaTemplate which wraps a Producer instance
    and provides convenience methods for sending messages to Kafka topics.
     */
    @Bean
    public KafkaTemplate<String, GQQGreeting> greetingKafkaTemplate() {
        logger.debug("## generating producer - greetingKafkaTemplate ##");
        return new KafkaTemplate<>(greetingProducerFactory());
    }

}
