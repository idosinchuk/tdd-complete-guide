/**package com.idosinchuk.tddcompleteguide.infraestructure.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaUserEventConsumer {
    @KafkaListener(topics = "user-events")
    public void consumeUserEvent(UserEvent event) {
        // Logic to handle the user event received from Kafka
        // Perform actions related to the event, such as updating user information
    }
}*/

