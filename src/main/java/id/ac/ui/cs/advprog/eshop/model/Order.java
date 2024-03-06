package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;

@Builder
@Getter
public class Order {
    private String orderId;
    private List<Product> orderProducts;
    private Long orderTime;
    private String author;
    private String orderStatus;

    public Order(String orderId, List<Product> orderProducts, Long orderTime, String author) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.author = author;
        this.orderStatus = OrderStatus.WAITING_PAYMENT.getValue();
        
        if (orderProducts.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.orderProducts = orderProducts;
        }
    }

    public Order(String orderId, List<Product> orderProducts, Long orderTime, String author, String orderStatus) {
        this(orderId, orderProducts, orderTime, author);
        this.setOrderStatus(orderStatus);
    }

    public void setOrderStatus(String orderStatus) {
        if (OrderStatus.contains(orderStatus)) {
            this.orderStatus = orderStatus;
        } else {
            throw new IllegalArgumentException();
        }
    }
}