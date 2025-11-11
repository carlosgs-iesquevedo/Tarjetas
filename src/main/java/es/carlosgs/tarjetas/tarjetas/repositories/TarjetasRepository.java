package es.carlosgs.tarjetas.tarjetas.repositories;

import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TarjetasRepository extends JpaRepository<Tarjeta, Long> {


    List<Tarjeta> findByNumero(String numero);

    List<Tarjeta> findByTitularContainsIgnoreCase(String titular);

    List<Tarjeta> findByNumeroAndTitularContainsIgnoreCase(String numero, String titular);

    Optional<Tarjeta> findByUuid(UUID uuid);

    boolean existsByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);


}
