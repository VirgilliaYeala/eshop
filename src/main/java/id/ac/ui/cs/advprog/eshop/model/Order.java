package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Order {
    private String orderId;
    private List<Product> orderProducts;
    private Long orderTime;
    private String author;
    private String orderStatus;

    public Order(String orderId, List<Product> orderProducts, Long orderTime, String author) {

    }

    public Order(String orderId, List<Product> orderProducts, Long orderTime, String author, String orderStatus) {
        
    }
}