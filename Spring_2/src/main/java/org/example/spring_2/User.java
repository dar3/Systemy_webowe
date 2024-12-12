package org.example.spring_2;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", unique = true, nullable = false)
    @NotNull
    @NotEmpty
    private String username;

    @Column(name = "password", nullable = false)
    @NotNull
    @NotEmpty
    private String password;

    @Column(name = "permission_level", nullable = false)
    @NotNull
    @NotEmpty
    private int permission_level;

}
