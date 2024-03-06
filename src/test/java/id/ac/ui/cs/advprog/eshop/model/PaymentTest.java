package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.StatusPayment;

class PaymentTest {
    private Map<String, String> paymentData;
    private Order order;
    private List<Product> products;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();

        products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("e45d7d21-fd29-4533-a569-abbe0819579a");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        Product product2 = new Product();
        product2.setProductId("8a76b99c-a0b3-46d2-a688-4c1831b21119");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        products.add(product2);

        order= new Order(
            "dbd4aff4-9a7f-4603-92c2-eaf529271cc9", 
            products, 
            1708560000L, 
            "Safira Sudrajat"
        );
    }

    void loadBankTransferPaymentData() {
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "1234567890");
    }

    void loadVoucherCodePaymentData() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataPendingStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        assertSame(payment.getPaymentOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("e45d7d21-fd29-4533-a569-abbe0819579a", payment.getPaymentId());
        assertEquals("", payment.getPaymentMethod());
        assertEquals(StatusPayment.PENDING.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataPendingStatus() {
        loadVoucherCodePaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        assertSame(payment.getPaymentOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("e45d7d21-fd29-4533-a569-abbe0819579a", payment.getPaymentId());
        assertEquals("", payment.getPaymentMethod());
        assertEquals(StatusPayment.PENDING.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataSuccessStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData, 
            StatusPayment.SUCCESS.getValue()
        );
        assertSame(payment.getPaymentOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("e45d7d21-fd29-4533-a569-abbe0819579a", payment.getPaymentId());
        assertEquals("", payment.getPaymentMethod());
        assertEquals(StatusPayment.SUCCESS.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataSuccessStatus() {
        loadVoucherCodePaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData, 
            StatusPayment.SUCCESS.getValue()
        );
        assertSame(payment.getPaymentOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("e45d7d21-fd29-4533-a569-abbe0819579a", payment.getPaymentId());
        assertEquals("", payment.getPaymentMethod());
        assertEquals(StatusPayment.SUCCESS.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataRejectedStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData, 
            StatusPayment.REJECTED.getValue()
        );
        assertSame(payment.getPaymentOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("e45d7d21-fd29-4533-a569-abbe0819579a", payment.getPaymentId());
        assertEquals("", payment.getPaymentMethod());
        assertEquals(StatusPayment.REJECTED.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataRejectedStatus() {
        loadVoucherCodePaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData, 
            StatusPayment.REJECTED.getValue()
        );
        assertSame(payment.getPaymentOrder(), order);
        assertNull(payment.getPaymentData());
        assertEquals("e45d7d21-fd29-4533-a569-abbe0819579a", payment.getPaymentId());
        assertEquals("", payment.getPaymentMethod());
        assertEquals(StatusPayment.REJECTED.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataInvalidStatus() {
        loadBankTransferPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                "e45d7d21-fd29-4533-a569-abbe0819579a", 
                "", 
                order, 
                paymentData, 
                "MEOW"
            );
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataInvalidStatus() {
        loadVoucherCodePaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                "e45d7d21-fd29-4533-a569-abbe0819579a", 
                "", 
                order, 
                paymentData, 
                "MEOW"
            );
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithBankTransferPaymentDataNullStatus() {
        loadBankTransferPaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                "e45d7d21-fd29-4533-a569-abbe0819579a", 
                "", 
                order, 
                paymentData, 
                null
            );
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithVoucherPaymentDataNullStatus() {
        loadVoucherCodePaymentData();
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                "e45d7d21-fd29-4533-a569-abbe0819579a", 
                "", 
                order, 
                paymentData, 
                null
            );
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToSuccess() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        payment.setStatus(StatusPayment.SUCCESS.getValue());
        assertEquals(StatusPayment.SUCCESS.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToSuccess() {
        loadVoucherCodePaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        payment.setStatus(StatusPayment.SUCCESS.getValue());
        assertEquals(StatusPayment.SUCCESS.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToRejected() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        payment.setStatus(StatusPayment.REJECTED.getValue());
        assertEquals(StatusPayment.REJECTED.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToRejected() {
        loadVoucherCodePaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        payment.setStatus(StatusPayment.REJECTED.getValue());
        assertEquals(StatusPayment.REJECTED.getValue(), payment.getStatusPayment());
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToInvalidStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToInvalidStatus() {
        loadVoucherCodePaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithBankTransferPaymentDataToNullStatus() {
        loadBankTransferPaymentData();
        Payment payment = new Payment(
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testSetStatusOfPaymentWithVoucherPaymentDataToNullStatus() {
        loadVoucherCodePaymentData();
        Payment payment = new Payment
        (
            "e45d7d21-fd29-4533-a569-abbe0819579a", 
            "", 
            order, 
            paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus(null);
        });
        paymentData.clear();
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            @SuppressWarnings("unused")
            Payment payment = new Payment(
                "e45d7d21-fd29-4533-a569-abbe0819579a", 
                "", 
                null, 
                paymentData
            );
        });
    }
}