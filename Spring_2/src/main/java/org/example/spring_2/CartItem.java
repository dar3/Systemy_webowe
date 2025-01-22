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
    private double price;

    public CartItem() {}

    public CartItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }




//    public int getProductId() {
//        return getProductId();
//    }
}