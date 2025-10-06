package es.carlosgs.tarjetas.tarjetas.services;

import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface TarjetasService {
    List<Tarjeta> findAll(String numero, String titular);

    Tarjeta findById(Long id);
    /*
        Tarjeta findbyUuid(String uuid);

        Tarjeta save(TarjetaCreateDto productoCreateDto);

        Tarjeta update(Long id, TarjetaUpdateDto productoUpdateDto);

    void deleteById(Long id);

     */

}