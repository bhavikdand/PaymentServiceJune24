package com.example.paymentservice.services;

import com.example.paymentservice.payment_gateways.PaymentGatewayAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService{

    PaymentGatewayAdapter paymentGatewayAdapter;

    @Autowired
    public PaymentServiceImpl(PaymentGatewayAdapter paymentGatewayAdapter) {
        this.paymentGatewayAdapter = paymentGatewayAdapter;
    }

    @Override
    public String createPaymentLink(long orderId, long amount) throws Exception {
        //TODO Call the order service, user service
        return paymentGatewayAdapter.createPaymentLink(orderId, amount);
    }

}
