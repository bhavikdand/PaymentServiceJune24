package com.example.paymentservice.payment_gateways;

import com.stripe.Stripe;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class StripePGAdapter implements PaymentGatewayAdapter{

    @Value("${stripe.secret.key}")
    private String stripeSecretKey;


    @Override
    public String createPaymentLink(long orderId, long amount) throws Exception {

        Stripe.apiKey = stripeSecretKey;

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)
                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(params);

        String priceId = price.getId();

        PaymentLinkCreateParams paymentLinkparams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(priceId)
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(PaymentLinkCreateParams.AfterCompletion.builder()
                                .setRedirect(
                                        PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                        .setUrl("https://scaler.com")
                                        .build()
                                )
                                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                .build()
                        )
                        .build();
        PaymentLink paymentLink = PaymentLink.create(paymentLinkparams);
        return paymentLink.getUrl();
    }
}
