package ru.osokin.portalfbi.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServerFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "size_kb")
    private Integer sizeInKb;

    @EqualsAndHashCode.Exclude
    @ManyToOne()
    @JoinColumn(name = "message_id", referencedColumnName = "id")
    private Message message;

}
