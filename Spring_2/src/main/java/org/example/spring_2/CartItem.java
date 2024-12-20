package org.example.spring_2;

import lombok.Data;

//@Data
//public class CartItem {
//    private int productId;
//    private String product_name;
//    private double price;
//    private int quantity;
//
//
//}
//

@Data
public class CartItem {
    private String name;
    private int quantity;

    public CartItem() {}

    public CartItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }


    public CartItem(int id, String name, double price, double weight, String category, int quantity) {
    }

//    public int getProductId() {
//        return getProductId();
//    }
}
