package com.example.paymentservice.payment_gateways;

import com.example.paymentservice.configs.RazorpayConfig;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RazorpayPGAdapter implements PaymentGatewayAdapter{

    private RazorpayClient razorpayClient;

    @Autowired
    public RazorpayPGAdapter(RazorpayClient razorpayClient) {
        this.razorpayClient = razorpayClient;
    }

    @Override
    public String createPaymentLink(long orderId, long amount) throws RazorpayException {

        JSONObject paymentLinkRequest = new JSONObject();
        paymentLinkRequest.put("amount",1000); // 199.99 => 19999
        paymentLinkRequest.put("currency","INR");
        paymentLinkRequest.put("expire_by", 1717553927);
        paymentLinkRequest.put("reference_id", String.valueOf(orderId)); //Is the order id at the client's end ()
        paymentLinkRequest.put("description","Payment for product");
        JSONObject customer = new JSONObject();
        customer.put("name","Gaurav Kumar");
        customer.put("contact","+919000090000");
        customer.put("email","gaurav.kumar@example.com");
        paymentLinkRequest.put("customer",customer);
        JSONObject notify = new JSONObject();
        notify.put("sms",true);
        notify.put("email",true);
        paymentLinkRequest.put("notify",notify);
        paymentLinkRequest.put("reminder_enable",true);
        JSONObject notes = new JSONObject();
        notes.put("policy_name","Jeevan Bima");
        paymentLinkRequest.put("notes",notes);
        paymentLinkRequest.put("callback_url","https://scaler.com/");
        paymentLinkRequest.put("callback_method","get");

        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
        return payment.get("short_url");
    }
}
