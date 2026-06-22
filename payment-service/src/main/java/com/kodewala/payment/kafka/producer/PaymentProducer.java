package com.kodewala.payment.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kodewala.payment.dto.PaymentEvent;

@Service
public class PaymentProducer {

    private static final Logger log =
            LoggerFactory.getLogger(PaymentProducer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(PaymentEvent event) {

        log.info("Publishing payment event for orderId: {}",
                event.getOrderId());

        kafkaTemplate.send(
                "payment-success-topic",
                event);

        log.info("Payment event published successfully for orderId: {}",
                event.getOrderId());
    }
}