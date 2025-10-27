package es.carlosgs.tarjetas.tarjetas.mappers;

import es.carlosgs.tarjetas.tarjetas.dto.TarjetaCreateDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaResponseDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaUpdateDto;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class TarjetaMapper {
    public Tarjeta toTarjeta(Long id, TarjetaCreateDto tarjetaCreateDto) {
        return Tarjeta.builder()
                .id(id)
                .uuid(UUID.randomUUID())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .numero(tarjetaCreateDto.getNumero())
                .cvc(tarjetaCreateDto.getCvc())
                .fechaCaducidad(tarjetaCreateDto.getFechaCaducidad())
                .titular(tarjetaCreateDto.getTitular())
                .saldo(tarjetaCreateDto.getSaldo())
                .build();

    }

    public Tarjeta toTarjeta(TarjetaUpdateDto tarjetaUpdateDto, Tarjeta tarjeta) {
        return Tarjeta.builder()
                .id(tarjeta.getId())
                .numero(tarjetaUpdateDto.getNumero() != null ? tarjetaUpdateDto.getNumero() : tarjeta.getNumero())
                .cvc(tarjetaUpdateDto.getCvc() != null ? tarjetaUpdateDto.getCvc() : tarjeta.getCvc())
                .fechaCaducidad(tarjetaUpdateDto.getFechaCaducidad() != null ? tarjetaUpdateDto.getFechaCaducidad() : tarjeta.getFechaCaducidad())
                .titular(tarjeta.getTitular())
                .saldo(tarjetaUpdateDto.getSaldo() != null ? tarjetaUpdateDto.getSaldo() : tarjeta.getSaldo())
                .createdAt(tarjeta.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .uuid(tarjeta.getUuid())
                .build();
    }

    public TarjetaResponseDto toTarjetaResponseDto(Tarjeta tarjeta) {
            return TarjetaResponseDto.builder()
                    .id(tarjeta.getId())
                    .numero(tarjeta.getNumero())
                    .cvc(tarjeta.getCvc())
                    .fechaCaducidad(tarjeta.getFechaCaducidad())
                    .titular(tarjeta.getTitular())
                    .saldo(tarjeta.getSaldo())
                    .createdAt(tarjeta.getCreatedAt())
                    .updatedAt(tarjeta.getUpdatedAt())
                    .uuid(tarjeta.getUuid())
                    .build();
    }

    // Mapeamos de modelo a DTO (lista)
    public List<TarjetaResponseDto> toResponseDtoList(List<Tarjeta> tarjetas) {
        return tarjetas.stream()
                .map(this::toTarjetaResponseDto)
                .toList();
    }

}
