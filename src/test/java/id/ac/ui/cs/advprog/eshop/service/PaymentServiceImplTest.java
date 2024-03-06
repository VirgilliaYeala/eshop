package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.StatusPayment;
import id.ac.ui.cs.advprog.eshop.model.BankTransfer;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.model.Voucher;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @Spy
    @InjectMocks
    PaymentServiceImpl paymentService;
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments;

    @BeforeEach
    void setup() {
        payments = new ArrayList<>();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("e45d7d21-fd29-4533-a569-abbe0819579a");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        Order order = new Order("dbd4aff4-9a7f-4603-92c2-eaf529271cc9", 
            products, 1708560000L, "Safira Sudrajat");

        Map<String, String> voucherPaymentData = new HashMap<>();
        voucherPaymentData.put("voucherCode", "ESHOP00000000AAA");
        Payment voucherPayment = new Voucher(
            "c0f81308-9911-40c5-8da4-fa3194485aa1",
            PaymentMethod.VOUCHER.getValue(),
            order,
            voucherPaymentData
        );

        Map<String, String> bankPaymentData = new HashMap<>();
        bankPaymentData.put("bankName", "BNI");
        bankPaymentData.put("referenceCode", "1234567890");
        Payment bankPayment = new BankTransfer(
            "c0f81308-9911-40c5-8da4-fa3194485aa1",
            PaymentMethod.BANK_TRANSFER.getValue(),
            order,
            bankPaymentData
        );

        payments.add(voucherPayment);
        payments.add(bankPayment);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAddBankPayment() {
        Payment bankPayment = payments.get(1);
        doReturn(bankPayment).when(paymentRepository).save(any(Payment.class));
        bankPayment = paymentService.addPayment(
            bankPayment.getPaymentOrder(),
            bankPayment.getPaymentMethod(),
            bankPayment.getPaymentData()
        );

        doReturn(bankPayment).when(paymentRepository).findById(bankPayment.getPaymentId());
        Payment result = paymentService.findById(bankPayment.getPaymentId());

        assertEquals(bankPayment.getPaymentId(), result.getPaymentId());
        assertEquals(bankPayment.getPaymentMethod(), result.getPaymentMethod());
        assertEquals(bankPayment.getPaymentOrder(), result.getPaymentOrder());
        assertEquals(bankPayment.getPaymentData(), result.getPaymentData());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), result.getPaymentMethod());
        verify(paymentService, times(1)).addPayment(any(
            Order.class), any(String.class), any(Map.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testAddVoucherPayment() {
        Payment voucherPayment = payments.get(0);
        doReturn(voucherPayment).when(paymentRepository).save(any(Payment.class));
        voucherPayment = paymentService.addPayment(
            voucherPayment.getPaymentOrder(),
            voucherPayment.getPaymentMethod(),
            voucherPayment.getPaymentData()
        );

        doReturn(voucherPayment).when(paymentRepository).findById(voucherPayment.getPaymentId());
        Payment result = paymentService.findById(voucherPayment.getPaymentId());

        assertEquals(voucherPayment.getPaymentId(), result.getPaymentId());
        assertEquals(voucherPayment.getPaymentMethod(), result.getPaymentMethod());
        assertEquals(voucherPayment.getPaymentOrder(), result.getPaymentOrder());
        assertEquals(voucherPayment.getPaymentData(), result.getPaymentData());
        assertEquals(PaymentMethod.VOUCHER.getValue(), result.getPaymentMethod());
        verify(paymentService, times(1)).addPayment(any(
            Order.class), any(String.class), any(Map.class));
    }

    @Test
    void testUpdateStatusBankPayment() {
        Payment bankPayment = payments.get(1);
        doReturn(bankPayment).when(paymentRepository).save(any(Payment.class));
        bankPayment = paymentService.addPayment(
            bankPayment.getPaymentOrder(),
            bankPayment.getPaymentMethod(),
            bankPayment.getPaymentData()
        );

        doReturn(bankPayment).when(paymentRepository).findById(bankPayment.getPaymentId());
        Payment result = paymentService.findById(bankPayment.getPaymentId());

        assertEquals(result.getStatusPayment(), StatusPayment.PENDING.getValue());
        paymentService.setStatus(result, StatusPayment.SUCCESS.getValue());
        assertEquals(result.getStatusPayment(), StatusPayment.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getPaymentOrder().getOrderStatus());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), result.getPaymentMethod());
        
        paymentService.setStatus(result, StatusPayment.REJECTED.getValue());
        assertEquals(result.getStatusPayment(), StatusPayment.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), result.getPaymentOrder().getOrderStatus());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), result.getPaymentMethod());
    }

    @Test
    void testUpdateStatusVoucherPayment() {
        Payment voucherPayment = payments.get(0);
        doReturn(voucherPayment).when(paymentRepository).save(any(Payment.class));
        voucherPayment = paymentService.addPayment(
            voucherPayment.getPaymentOrder(),
            voucherPayment.getPaymentMethod(),
            voucherPayment.getPaymentData()
        );

        doReturn(voucherPayment).when(paymentRepository).findById(voucherPayment.getPaymentId());
        Payment result = paymentService.findById(voucherPayment.getPaymentId());

        assertEquals(result.getStatusPayment(), StatusPayment.PENDING.getValue());
        paymentService.setStatus(result, StatusPayment.SUCCESS.getValue());
        assertEquals(result.getStatusPayment(), StatusPayment.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), result.getPaymentOrder().getOrderStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), result.getPaymentMethod());
        
        paymentService.setStatus(result, StatusPayment.REJECTED.getValue());
        assertEquals(result.getStatusPayment(), StatusPayment.REJECTED.getValue());
        assertEquals(OrderStatus.FAILED.getValue(), result.getPaymentOrder().getOrderStatus());
        assertEquals(PaymentMethod.VOUCHER.getValue(), result.getPaymentMethod());
    }

    @Test
    void testUpdateStatusBankPaymentInvalidStatus() {
        assertEquals(payments.get(1).getStatusPayment(), StatusPayment.PENDING.getValue());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payments.get(1).getPaymentMethod());
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payments.get(0), "MEOW");
        });
    }

    @Test
    void testUpdateStatusVoucherPaymentInvalidStatus() {
        assertEquals(payments.get(0).getStatusPayment(), StatusPayment.PENDING.getValue());
        assertEquals(PaymentMethod.VOUCHER.getValue(), payments.get(0).getPaymentMethod());
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.setStatus(payments.get(0), "MEOW");
        });
    }

    @Test
    void testUpdateStatusBankPaymentUnregiesteredPayment() {
        Payment bankPayment = payments.get(1);
        doReturn(null).when(paymentRepository).findById(bankPayment.getPaymentId());
        assertThrows(NoSuchElementException.class, () -> {
            paymentService.setStatus(bankPayment, StatusPayment.SUCCESS.getValue());
        });
    }

    @Test
    void testUpdateStatusVoucherPaymentUnregiesteredPayment() {
        Payment voucherPayment = payments.get(0);
        doReturn(null).when(paymentRepository).findById(voucherPayment.getPaymentId());
        assertThrows(NoSuchElementException.class, () -> {
            paymentService.setStatus(voucherPayment, StatusPayment.SUCCESS.getValue());
        });
    }

    @Test
    void testFindByIdIfIdFound() {
        Payment payment = payments.get(1);
        doReturn(payment).when(paymentRepository).findById(payment.getPaymentId());

        Payment result = paymentService.findById(payment.getPaymentId());

        assertEquals(payment.getPaymentId(), result.getPaymentId());
        assertEquals(payment.getPaymentMethod(), result.getPaymentMethod());
        assertEquals(payment.getPaymentOrder(), result.getPaymentOrder());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        doReturn(null).when(paymentRepository).findById("zczc");
        assertNull(paymentService.findById("zczc"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();
        List<Payment> result = paymentService.findAll();
        assertEquals(payments, result);
    }

    @Test
    void testCreateVoucherPaymentInvalidMethod() {
        Payment voucherPayment = payments.get(0);
        Mockito.lenient().doReturn(voucherPayment).when(paymentRepository).save(any(Payment.class));
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(
                voucherPayment.getPaymentOrder(),
                "MEOW",
                voucherPayment.getPaymentData()
            );
        });
    }

    @Test
    void testCreateBankPaymentInvalidMethod() {
        Payment bankPayment = payments.get(1);
        Mockito.lenient().doReturn(bankPayment).when(paymentRepository).save(any(Payment.class));
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(
                bankPayment.getPaymentOrder(),
                "MEOW",
                bankPayment.getPaymentData()
            );
        });
    }
}