package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class Payment {
    private String paymentId;
    private String paymentMethod;
    private Long orderTime;
    private Map<String, String> paymentData;

}