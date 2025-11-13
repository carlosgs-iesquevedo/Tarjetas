package es.carlosgs.tarjetas.titulares.mappers;

import es.carlosgs.tarjetas.titulares.dto.TitularRequestDto;
import es.carlosgs.tarjetas.titulares.models.Titular;
import org.springframework.stereotype.Component;

@Component
public class TitularesMapper {
    public Titular toTitular(TitularRequestDto dto) {
        return Titular.builder()
                .id(null)
                .nombre(dto.getNombre())
                .build();
    }

    public Titular toTitular(TitularRequestDto dto, Titular titular) {
        return Titular.builder()
                .id(titular.getId())
                .nombre(dto.getNombre() != null ? dto.getNombre() : titular.getNombre())
                .createdAt(titular.getCreatedAt())
                // no tenemos en cuenta este campo porque la actualización lo cambiará automáticamente
                //.updatedAt(LocalDateTime.now())
                .isDeleted(dto.getIsDeleted()  != null ? dto.getIsDeleted() : titular.getIsDeleted())
                .build();
    }
}
