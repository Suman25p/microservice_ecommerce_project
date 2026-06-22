package com.kodewala.delivery.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kodewala.delivery.dto.PaymentEvent;
import com.kodewala.delivery.entity.Delivery;
import com.kodewala.delivery.repository.DeliveryRepository;

@Service
public class DeliveryConsumer {

    private static final Logger log =
            LoggerFactory.getLogger(DeliveryConsumer.class);

    @Autowired
    private DeliveryRepository repository;

    @KafkaListener(
            topics = "payment-success-topic",
            groupId = "delivery-group")
    public void consume(PaymentEvent event) {

        log.info("Payment Event Received for orderId: {}",
                event.getOrderId());

        Delivery delivery = new Delivery();

        delivery.setOrderId(
                event.getOrderId());

        delivery.setStatus("PACKED");

        repository.save(delivery);

        log.info("Delivery Created For Order: {}",
                event.getOrderId());
    }
}