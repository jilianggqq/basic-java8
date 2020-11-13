package edu.gqq.gqqkafka;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/*
 * @EnableKafka annotation is required on the configuration class to
 * enable detection of @KafkaListener annotation on spring managed beans:
 */
@EnableKafka
@Configuration
public class GQQKafkaConsumerConfig {

    private final Logger logger = LoggerFactory.getLogger(GQQKafkaConsumerConfig.class);

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public ConsumerFactory<String, String> consumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    /*
     * For consuming messages, we need to configure a ConsumerFactory and a KafkaListenerContainerFactory.
     * Once these beans are available in the Spring bean factory,
     * POJO based consumers can be configured using @KafkaListener annotation.
     */
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
        String groupId) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory(groupId));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> fooKafkaListenerContainerFactory() {
        logger.debug("## generating consumer - fooKafkaListenerContainerFactory ##");
        return kafkaListenerContainerFactory("foo");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> barKafkaListenerContainerFactory() {
        logger.debug("## generating consumer - barKafkaListenerContainerFactory ##");
        return kafkaListenerContainerFactory("bar");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> thirdKafkaListenerContainerFactory() {
        logger.debug("## generating consumer - thirdKafkaListenerContainerFactory ##");
        return kafkaListenerContainerFactory("third");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> headersKafkaListenerContainerFactory() {
        logger.debug("## generating consumer - headersKafkaListenerContainerFactory ##");

        return kafkaListenerContainerFactory("headers");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> partitionsKafkaListenerContainerFactory() {

        logger.debug("## generating consumer - partitionsKafkaListenerContainerFactory ##");
        return kafkaListenerContainerFactory("partitions");
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> filterKafkaListenerContainerFactory() {
        logger.debug("## generating consumer - filterKafkaListenerContainerFactory ##");
        ConcurrentKafkaListenerContainerFactory<String, String> factory = kafkaListenerContainerFactory(
            "filter");
        factory.setRecordFilterStrategy(record -> record.value()
            .contains("World"));
        return factory;
    }

    public ConsumerFactory<String, GQQGreeting> greetingConsumerFactory(String groupId) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
            new JsonDeserializer<>(GQQGreeting.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GQQGreeting> fooGreetingKafkaListenerContainerFactory() {
        logger.debug("## generating consumer - fooGreetingKafkaListenerContainerFactory ##");
        ConcurrentKafkaListenerContainerFactory<String, GQQGreeting> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(greetingConsumerFactory("fooGreeting"));
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GQQGreeting> barGreetingKafkaListenerContainerFactory() {
        logger.debug("## generating consumer - barGreetingKafkaListenerContainerFactory ##");
        ConcurrentKafkaListenerContainerFactory<String, GQQGreeting> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(greetingConsumerFactory("barGreeting"));
        return factory;
    }

}