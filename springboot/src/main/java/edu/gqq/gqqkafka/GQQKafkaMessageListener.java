package edu.gqq.gqqkafka;

import java.util.concurrent.CountDownLatch;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class GQQKafkaMessageListener {

    CountDownLatch latch = new CountDownLatch(3);

    CountDownLatch partitionLatch = new CountDownLatch(2);

    CountDownLatch filterLatch = new CountDownLatch(2);

    CountDownLatch greetingLatch = new CountDownLatch(1);

    /*
     * For consuming messages, we need to configure a ConsumerFactory and a KafkaListenerContainerFactory.
     * Once these beans are available in the Spring bean factory,
     * POJO based consumers can be configured using @KafkaListener annotation.
     */
    @KafkaListener(topics = "${message.topic.name}", groupId = "foo", containerFactory = "fooKafkaListenerContainerFactory")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group 'foo': " + message);
        latch.countDown();
    }

    /*
    Multiple listeners can be implemented for a topic, each with a different group Id.
     */
    @KafkaListener(topics = "${message.topic.name}", groupId = "bar", containerFactory = "barKafkaListenerContainerFactory")
    public void listenGroupBar(String message) {
        System.out.println("Received Message in group 'bar': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${message.topic.name}", groupId = "third", containerFactory =
        "thirdKafkaListenerContainerFactory")
    public void listenGroupThird(String message) {
        System.out.println("Received Message in group 'third': " + message);
        latch.countDown();
    }

    @KafkaListener(topics = "${message.topic.name}", containerFactory = "headersKafkaListenerContainerFactory")
    public void listenWithHeaders(@Payload String message,
        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Header Message: " + message + " from partition: " + partition);
        latch.countDown();
    }

    @KafkaListener(topicPartitions = @TopicPartition(topic = "${partitioned.topic.name}", partitions = {
        "0", "3"}), containerFactory = "partitionsKafkaListenerContainerFactory")
    public void listenToPartition(@Payload String message,
        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        System.out.println("Received Message: " + message + " from partition: " + partition);
        this.partitionLatch.countDown();
    }

    @KafkaListener(topics = "${filtered.topic.name}", containerFactory = "filterKafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        System.out.println("Received Message in filtered listener: " + message);
        this.filterLatch.countDown();
    }

    @KafkaListener(topics = "${greeting.topic.name}", containerFactory =
        "fooGreetingKafkaListenerContainerFactory")
    public void forGreetingListener(GQQGreeting greeting) {
        System.out.println("Received foo greeting message: " + greeting);
        this.greetingLatch.countDown();
    }

    @KafkaListener(topics = "${greeting.topic.name}", containerFactory =
        "barGreetingKafkaListenerContainerFactory")
    public void barGreetingListener(GQQGreeting greeting) {
        System.out.println("Received bar greeting message: " + greeting);
        this.greetingLatch.countDown();
    }

}
