package ru.osokin.portalfbi.models;


import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private User author;

    @Column(name = "author_name")
    @NotEmpty
    private String authorName;

    @Column(name = "creation_time")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date creationTime = new Date();

    @OneToMany(mappedBy = "message")
    private Set<ServerFile> files = new HashSet<>();



}
