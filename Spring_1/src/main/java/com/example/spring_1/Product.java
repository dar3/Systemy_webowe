package com.example.spring_1;

public class Product {

    private String name;
    private double weight;
    private double price;
    private int id;
    private String category;

    public Product() {}

    public Product(String name, double weight, double price, int id, String category) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.id = id;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
