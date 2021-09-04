package com.example.todoApp.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(
        name = "note",
        uniqueConstraints = {
                @UniqueConstraint(name = "id_is_uniquely", columnNames = "id")
        }
)
@Builder
public class Note {

    @Id
    @SequenceGenerator(
            name = "sequence_generator",
            sequenceName = "to_do_app_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "to_do_app_sequence",
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String title;
    private String noteBody;

    @CreationTimestamp
    private LocalDateTime dateCreated;
}
