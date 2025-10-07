package es.carlosgs.tarjetas.tarjetas.services;

import es.carlosgs.tarjetas.exceptions.TarjetaNotFoundException;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.repositories.TarjetasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@CacheConfig(cacheNames = {"tarjetas"})
@Slf4j
@Service
public class TarjetasServiceImpl implements TarjetasService {
    private final TarjetasRepository tarjetasRepository;

    @Autowired
    public TarjetasServiceImpl(TarjetasRepository tarjetasRepository) {
        this.tarjetasRepository = tarjetasRepository;
    }

    @Override
    public List<Tarjeta> findAll(String numero, String titular) {
        // Si todo está vacío o nulo, devolvemos todas las tarjetas
        if ((numero == null || numero.isEmpty()) && (titular == null || titular.isEmpty())) {
            log.info("Buscando todas las tarjetas");
            return tarjetasRepository.findAll();
        }
        // Si el numero no está vacío, pero el titular si, buscamos por numero
        if ((numero != null && !numero.isEmpty()) && (titular == null || titular.isEmpty())) {
            log.info("Buscando tarjetas por numero: " + numero);
            return tarjetasRepository.findAllByNumero(numero);
        }
        // Si el numero está vacío, pero el titular no, buscamos por titular
        if (numero == null || numero.isEmpty()) {
            log.info("Buscando tarjetas por titular: " + titular);
            return tarjetasRepository.findAllByTitular(titular);
        }
        // Si el numero y el titular no están vacíos, buscamos por ambos
        log.info("Buscando tarjetas por numero: " + numero + " y titular: " + titular);
        return tarjetasRepository.findAllByNumeroAndTitular(numero, titular);
    }

    @Override
    @Cacheable
    public Tarjeta findById(Long id) {
        log.info("Buscando tarjeta por id {}", id);
        /*
        // Estilo estructurado
        Optional<Tarjeta> tarjetaEncontrada = tarjetasRepository.findById(id);
        if (tarjetaEncontrada.isPresent()) {
            return tarjetaEncontrada.get();
        } else {
            throw new TarjetaNotFoundException(id);
        }

         */
        // estilo funcional
        return tarjetasRepository.findById(id).orElseThrow(()-> new TarjetaNotFoundException(id));
    }

}
