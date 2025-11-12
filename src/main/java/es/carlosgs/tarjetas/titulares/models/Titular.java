package es.carlosgs.tarjetas.titulares.models;

import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    private  String nombre;
    private  String apellido;
    private  String email;
    private  String dni;
    private  String telefono;
    private  LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "titular")
    private List<Tarjeta> tarjetas;

}
