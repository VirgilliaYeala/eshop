package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;

@Getter
public class BankTransfer extends Payment {
    public BankTransfer(String paymentId, String paymentMethod, Order paymentOrder, Map<String, String> paymentData, String statusPayment) {
        super (paymentId, paymentMethod, paymentOrder, paymentData, statusPayment);
    }

    public BankTransfer(String paymentId, String paymentMethod, Order paymentOrder, Map<String, String> paymentData) {
        super (paymentId, paymentMethod, paymentOrder, paymentData);
    }

    @Override
    protected void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.isEmpty()) {
            throw new IllegalArgumentException("Payment data cannot be empty");
        }

        if (paymentData.get("bankName") == null || paymentData.get("bankName").isEmpty()) {
            throw new IllegalArgumentException("Bank name cannot be empty");
        }

        if (paymentData.get("referenceCode") == null || paymentData.get("referenceCode").isEmpty()) {
            throw new IllegalArgumentException("Reference code cannot be empty");
        }

        this.paymentData = paymentData;
    }
}