package ru.osokin.portalfbi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NonNull
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3,max = 100)
    private String name;
    @Column(name = "surname")
    @NonNull
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 3,max = 100)
    private String surname;
    @Column(name = "username")
    private String username;
    @Column
    @NonNull
    @NotEmpty(message = "Password should not be empty")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;


}
