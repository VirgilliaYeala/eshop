package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepositoryImpl {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        for (Payment p : paymentData) {
            if (p.getPaymentId().equals(payment.getPaymentId())) {
                throw new IllegalArgumentException("Payment already exists");
            }
        }

        paymentData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment p : paymentData) {
            if (p.getPaymentId().equals(id)) {
                return p;
            }
        }

        return null;
    }

    public List<Payment> findAll() {
        return paymentData;
    }
}
