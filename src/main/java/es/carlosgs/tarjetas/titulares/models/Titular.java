package es.carlosgs.tarjetas.titulares.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // JPA necesita un constructor vacío
@Entity
@Table(name = "TITULARES")
public class Titular {
    @Id // Indicamos que es el ID de la tabla
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(unique = true, nullable = false,  length = 20)
    private String nombre;

    @Builder.Default
    @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Builder.Default
    private LocalDateTime updatedAt =  LocalDateTime.now();

    @Column(columnDefinition = "boolean default false")
    @Builder.Default
    private Boolean isDeleted = false;

    @JsonIgnoreProperties("titular")
    @OneToMany(mappedBy = "titular")
    private List<Tarjeta> tarjetas;

}
