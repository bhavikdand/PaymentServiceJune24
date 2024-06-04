package com.example.paymentservice.payment_gateways;

import com.razorpay.RazorpayException;

public interface PaymentGatewayAdapter {

    public String createPaymentLink(long orderId, long amount) throws Exception;
}
