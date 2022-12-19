package ru.osokin.carpetwash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Client")
@Data
@NoArgsConstructor
public class Client {
    @Id
    @Column(name = "phone_number")
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phoneNumber;
    @Column
    private String name;
    @Column
    private int sale;
    @OneToMany(mappedBy = "owner")
    private List<Carpet> carpets;

    public Client(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.sale = 0;
    }
}
