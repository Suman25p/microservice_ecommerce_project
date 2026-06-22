package com.kodewala.delivery.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodewala.delivery.entity.Delivery;
import com.kodewala.delivery.repository.DeliveryRepository;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private static final Logger log =
            LoggerFactory.getLogger(DeliveryController.class);

    @Autowired
    private DeliveryRepository repository;

    @GetMapping
    public List<Delivery> getAll() {

        log.info("Fetching all delivery records");

        List<Delivery> deliveries =
                repository.findAll();

        log.info("Total deliveries found: {}",
                deliveries.size());

        return deliveries;
    }
}