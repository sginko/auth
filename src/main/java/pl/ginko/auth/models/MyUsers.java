package pl.ginko.auth.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "my")
public class MyUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String password;
    private String roles;

}
