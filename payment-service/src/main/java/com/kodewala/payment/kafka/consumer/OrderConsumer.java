package com.kodewala.payment.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodewala.payment.dto.OrderEvent;
import com.kodewala.payment.dto.PaymentEvent;
import com.kodewala.payment.kafka.producer.PaymentProducer;

@Service
public class OrderConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(OrderConsumer.class);

    private final PaymentProducer paymentProducer;

    public OrderConsumer(PaymentProducer paymentProducer) {
        this.paymentProducer = paymentProducer;
    }

    @KafkaListener(
            topics = "order-topic2",
            groupId = "payment-group")
    public void consume(OrderEvent event) {

        log.info("Order Received with orderId: {}",
                event.getOrderId());

        PaymentEvent paymentEvent =
                new PaymentEvent(
                        event.getOrderId(),
                        "SUCCESS");

        log.info("Publishing Payment Event for orderId: {}",
                event.getOrderId());

        paymentProducer.publish(paymentEvent);

        log.info("Payment Event Published Successfully for orderId: {}",
                event.getOrderId());
    }
}