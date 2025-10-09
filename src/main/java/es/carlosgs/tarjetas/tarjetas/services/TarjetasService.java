package es.carlosgs.tarjetas.tarjetas.services;

import es.carlosgs.tarjetas.tarjetas.dto.TarjetaCreateDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaResponseDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaUpdateDto;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TarjetasService {
    List<Tarjeta> findAll(String numero, String titular);

    Tarjeta findById(Long id);

    Tarjeta findbyUuid(String uuid);

    TarjetaResponseDto save(TarjetaCreateDto tarjetaCreateDto);

    Tarjeta update(Long id, TarjetaUpdateDto tarjetaUpdateDto);

    void deleteById(Long id);

}