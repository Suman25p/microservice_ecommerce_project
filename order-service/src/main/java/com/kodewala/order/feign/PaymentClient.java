package com.kodewala.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "payment-service", configuration = TracingFeignConfig.class)
public interface PaymentClient {

    @GetMapping("/payments/pay/{orderId}")
    String payOrder(@PathVariable Long orderId);
}