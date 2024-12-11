package org.example.spring_2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "category", schema = "sklep")
public class Category {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "category_name", unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String code;


}
