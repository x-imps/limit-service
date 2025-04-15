package com.ximps.limit_service.dto;

import com.ximps.limit_service.constants.LimitStatus;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LimitVerificationResponse implements Serializable {
    private String limitVerificationLogId;
    private String paymentId;
    private String transactionId;
    private LimitStatus limitStatus;
}
