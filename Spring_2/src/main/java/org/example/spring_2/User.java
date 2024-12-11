package org.example.spring_2;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "users", schema = "sklep")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "username", unique = true, nullable = false)
    private String name;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "permission_level", nullable = false)
    private int permission_level;

}
