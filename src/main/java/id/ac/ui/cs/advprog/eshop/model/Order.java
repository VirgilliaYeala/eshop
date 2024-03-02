package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

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
        this.orderStatus = "WAITING_PAYMENT";
        
        if (orderProducts.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.orderProducts = orderProducts;
        }
    }

    public Order(String orderId, List<Product> orderProducts, Long orderTime, String author, String orderStatus) {
        this(orderId, orderProducts, orderTime, author);

        String[] orderStatusList = {"WAITING_PARMENT", "FAILED","SUCCESS", "CANCELLED"};
        if (Arrays.stream(orderStatusList).noneMatch(item -> (item.equals(orderStatus)))) {
            throw new IllegalArgumentException();
        } else {
            this.orderStatus = orderStatus;
        }
    }

    public void setOrderStatus(String orderStatus) {
        String[] orderStatusList = {"WAITING_PARMENT", "FAILED","SUCCESS", "CANCELLED"};
        if (Arrays.stream(orderStatusList).noneMatch(item -> (item.equals(orderStatus)))) {
            throw new IllegalArgumentException();
        } else {
            this.orderStatus = orderStatus;
        }
    }
}