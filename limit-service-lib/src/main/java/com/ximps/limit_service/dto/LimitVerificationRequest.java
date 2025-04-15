package com.ximps.limit_service.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LimitVerificationRequest implements Serializable {

    private String paymentId;

    private String transactionId;

    private String payerCustomerId;

    private String payerAccountId;

    private Double amount;
}
