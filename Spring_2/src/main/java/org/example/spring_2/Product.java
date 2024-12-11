package org.example.spring_2;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "product", schema = "sklep")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "product_name", unique = true, nullable = false)
    private String name;
    private double weight;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}

