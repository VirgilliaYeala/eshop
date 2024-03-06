package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum StatusPayment {
    SUCCESS("SUCCESS"),
    REJECTED("REJECTED"),
    PENDING("PENDING");
    private final String value;

    private StatusPayment(String value){
        this.value = value;
    }

    public static  boolean contains(String param){
        for (StatusPayment StatusPayment : StatusPayment.values()){
            if (StatusPayment.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}