package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.StatusPayment;

@Getter
public class Payment {
    private String paymentId;
    private String paymentMethod;
    private Long orderTime;
    private Map<String, String> paymentData;
    private Order paymentOrder;
    private String statusPayment;

    public Payment(String paymentId, String paymentMethod, Order paymentOrder, Map<String, String> paymentData, String statusPayment) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;
        this.setOrder(paymentOrder);
        this.setPaymentData(paymentData);
        this.setStatus(statusPayment);
    }

    public Payment(String paymentId, String paymentMethod, Order order, Map<String, String> paymentData) {
        this(paymentId, paymentMethod, order, paymentData, StatusPayment.PENDING.getValue());
    }

    public void setStatus(String statusPayment) {
        if (!StatusPayment.contains(statusPayment)) {
            throw new IllegalArgumentException("Invalid payment status");
        }

        this.statusPayment = statusPayment;
    }

    private void setOrder(Order paymentOrder) {
        if (paymentOrder == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        this.paymentOrder = paymentOrder;
    }

    protected void setPaymentData(Map<String, String> paymentData) {
        if (PaymentMethod.contains(this.paymentMethod)) {
            throw new IllegalArgumentException(
                "Cannot set method-specific payment data for non-method-specific payment"
            );
        }

        this.paymentData = null;
    }
}