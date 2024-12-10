package org.example.spring_2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Setter
@Getter
@Table(name = "product", schema = "sklep")
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private double weight;
    private double price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}

