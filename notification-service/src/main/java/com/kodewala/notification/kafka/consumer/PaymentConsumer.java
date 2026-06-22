package com.kodewala.notification.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodewala.notification.dto.PaymentEvent;

@Service
public class PaymentConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(PaymentConsumer.class);

    @KafkaListener(
            topics = "payment-success-topic",
            groupId = "notification-group")
    public void consume(PaymentEvent event) {

        log.info("================================");
        log.info("PAYMENT EVENT RECEIVED");
        log.info("Order Id : {}", event.getOrderId());
        log.info("Status   : {}", event.getStatus());
        log.info("Sending Email Notification...");
        log.info("================================");
    }
}