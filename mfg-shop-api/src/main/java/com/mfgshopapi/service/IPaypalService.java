package com.mfgshopapi.service;

import com.mfgshopapi.enums.PaypalPaymentIntent;
import com.mfgshopapi.enums.PaypalPaymentMethod;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import java.math.BigDecimal;

public interface IPaypalService {
    Payment createPayment(BigDecimal total, String currency, PaypalPaymentMethod method, PaypalPaymentIntent intent, String description, String cancelUrl, String successUrl)throws PayPalRESTException;
    Payment executePayment(String paymentId, String payerId) throws PayPalRESTException;
}
