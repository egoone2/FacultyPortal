package ru.osokin.portalfbi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Messages")
@NoArgsConstructor
@RequiredArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NonNull
    private String content;

    @Column
    private String filename;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @Column(name = "author_name")
    @NotEmpty
    private String authorName;

    @Column(name = "creation_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime timestamp = LocalDateTime.now();
}
