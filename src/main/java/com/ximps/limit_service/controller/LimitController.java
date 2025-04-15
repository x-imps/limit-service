package com.ximps.limit_service.controller;

import com.netflix.discovery.converters.Auto;
import com.ximps.common.service.KafkaProducerService;
import com.ximps.limit_service.constants.LimitStatus;
import com.ximps.limit_service.dto.LimitVerificationRequest;
import com.ximps.limit_service.dto.LimitVerificationResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/limit")
public class LimitController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/verify")
    public ResponseEntity<LimitVerificationResponse> verifyLimit(@RequestBody @Valid LimitVerificationRequest limitVerificationRequest) {

        String limitVerificationLogId = UUID.randomUUID().toString();
        Random random = new Random();
        int randomInt = random.nextInt(11);
        LimitStatus limitStatus = LimitStatus.WITHIN_LIMIT;
        if(randomInt > 7) {
            limitStatus = LimitStatus.LIMIT_EXHAUSTED;
        }
        LimitVerificationResponse limitVerificationResponse = LimitVerificationResponse.builder()
                .limitVerificationLogId(limitVerificationLogId)
                .paymentId(limitVerificationRequest.getPaymentId())
                .transactionId(limitVerificationRequest.getPaymentId())
                .limitStatus(limitStatus).build();

        //publish to kafka
        kafkaProducerService.publish("limit-verification", limitVerificationResponse);

        return  ResponseEntity.ok(limitVerificationResponse);
    }

}
