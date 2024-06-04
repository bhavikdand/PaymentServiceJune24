package com.example.paymentservice.dtos;

import lombok.Data;

@Data
public class CreatePaymentLinkRequestDto {
    Long orderId;
    Long amount; // Because we are not implement order service for now, once its implemented, we will fetch
    //amount from order service
}
