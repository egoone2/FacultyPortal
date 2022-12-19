package ru.osokin.carpetwash.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.osokin.carpetwash.util.Utils;

import java.time.LocalDateTime;

@Entity
@Table(name = "Carpet")
@Data
@NoArgsConstructor
public class Carpet {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Min(value = 0)
    private double length;
    @Column
    @Min(value = 0)
    private double width;
    @Column
    @Min(value = 0)
    private double area;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "iswashed")
    private boolean isWashed;
    @ManyToOne
    @JoinColumn(name = "owner_phone_number", referencedColumnName = "phone_number")
    private Client owner;
    @Column(name = "when_taken")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime whenTaken;

    @Override
    public String toString() {
        return id + "    " +
                length + "    " +
                width + "    " +
                area + "    " +
                totalPrice + "    " +
                (isWashed ? "Выполнено" : "Нет") + "    "
                + whenTaken.format(Utils.formatter);
    }
}
