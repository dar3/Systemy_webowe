package org.example.spring_2;

import lombok.Data;

@Data
public class CartItem {
    private int productId;
    private String product_name;
    private double price;
    private int quantity;


}

