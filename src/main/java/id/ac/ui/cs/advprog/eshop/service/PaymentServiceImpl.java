package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.StatusPayment;
import id.ac.ui.cs.advprog.eshop.model.BankTransfer;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Voucher;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> data) {
        Payment payment;

        if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            payment = createBankPayment(order, method, data);
        } else if (method.equals(PaymentMethod.VOUCHER.getValue())) {
            payment = createVouvherPayment(order, method, data);
        } else {
            throw new IllegalArgumentException("Invalid payment method");
        }

        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);

        if (paymentRepository.findById(payment.getPaymentId()) == null){
            throw new NoSuchElementException("Payment not found");
        }

        if (payment.getStatusPayment().equals(StatusPayment.SUCCESS.getValue())){
            payment.getPaymentOrder().setOrderStatus(OrderStatus.SUCCESS.getValue());
        }else if(payment.getStatusPayment().equals(StatusPayment.REJECTED.getValue())){
            payment.getPaymentOrder().setOrderStatus(OrderStatus.FAILED.getValue());
        } else {
            throw new IllegalArgumentException("Invalid payment status");
        }

        return payment;
    }
    
    public Payment createBankPayment(Order order, String method, Map<String, String> data) {
        return new BankTransfer(
            UUID.randomUUID().toString(),
            method,
            order,
            data
        );
    }

    public Payment createVouvherPayment(Order order, String method, Map<String, String> data) {
        return new Voucher(
            UUID.randomUUID().toString(),
            method,
            order,
            data
        );
    }
}