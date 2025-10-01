package es.carlosgs.tarjetas.tarjetas.repositories;

import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class TarjetasRepositoryImpl implements TarjetasRepository {

    private final Map<Long, Tarjeta> tarjetas = new LinkedHashMap<>(
            Map.of(
                    1L, new Tarjeta(1L, "1234-5678-1234-5678", "555",
                            LocalDate.of(2025,12,31), "Jose", 100.0,
                            LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID()),
                    2L,  new Tarjeta(2L, "4321-5678-1234-5678", "555",
                            LocalDate.of(2025,12,31), "Juan", 100.0,
                            LocalDateTime.now(), LocalDateTime.now(), UUID.randomUUID())
            ));


    @Override
    public List<Tarjeta> findAll() {
        return tarjetas.values().stream()
                .toList();
    }

    @Override
    public List<Tarjeta> findAllByNumero(String numero) {
        return tarjetas.values().stream()
                .filter(tarjeta -> tarjeta.getNumero().toLowerCase().contains(numero.toLowerCase()))
                .toList();
    }

    @Override
    public Optional<Tarjeta> findById(Long id) {
        return tarjetas.get(id) != null ? Optional.of(tarjetas.get(id)) : Optional.empty();
    }
}
