package es.carlosgs.tarjetas.tarjetas.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TarjetaCreateDto {
    private final String numero;
    //@Digits(integer=3, fraction = 0, message = "Debe tener 3 digitos")
    @Pattern(regexp = "[0-9]{3}", message = "Debe tener 3 dígitos")
    private final String cvc;
    @Future(message = "La fecha debe ser posterior a la actual")
    private final LocalDate fechaCaducidad;
    private final String titular;
    private final Double saldo;
}
