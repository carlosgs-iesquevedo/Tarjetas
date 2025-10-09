package es.carlosgs.tarjetas.tarjetas.services;

import es.carlosgs.tarjetas.tarjetas.dto.TarjetaCreateDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaResponseDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaUpdateDto;
import es.carlosgs.tarjetas.tarjetas.exceptions.TarjetaNotFoundException;
import es.carlosgs.tarjetas.tarjetas.mappers.TarjetaMapper;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.repositories.TarjetasRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@CacheConfig(cacheNames = {"tarjetas"})
@Slf4j
@Service
public class TarjetasServiceImpl implements TarjetasService {
    private final TarjetasRepository tarjetasRepository;
    private final TarjetaMapper tarjetaMapper;

    @Autowired
    public TarjetasServiceImpl(TarjetasRepository tarjetasRepository,  TarjetaMapper tarjetaMapper) {
        this.tarjetasRepository = tarjetasRepository;
        this.tarjetaMapper = tarjetaMapper;
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


    @Override
    @Cacheable
    public Tarjeta findbyUuid(String uuid) {
        log.info("Buscando tarjeta por uuid: " + uuid);
        var myUUID = UUID.fromString(uuid);
        return tarjetasRepository.findByUuid(myUUID).orElse(null);
    }

    @Override
    @CachePut
    public TarjetaResponseDto save(TarjetaCreateDto tarjetaCreateDto) {
        log.info("Guardando tarjeta: " + tarjetaCreateDto);
        // obtenemos el id de tarjeta
        Long id = tarjetasRepository.nextId();
        // Creamos la tarjeta nueva con los datos que nos vienen
        Tarjeta nuevaTarjeta = tarjetaMapper.toTarjeta(id, tarjetaCreateDto);

        // La guardamos en el repositorio
        return tarjetaMapper.toTarjetaResponseDto(tarjetasRepository.save(nuevaTarjeta));
    }

    @Override
    @CachePut
    public Tarjeta update(Long id, TarjetaUpdateDto tarjetaUpdateDto) {
        log.info("Actualizando tarjeta por id: " + id);
        var tarjetaActual = this.findById(id);
        // Actualizamos la tarjeta con los datos que nos vienen
        Tarjeta tarjetaActualizada =  tarjetaMapper.toTarjeta(tarjetaUpdateDto, tarjetaActual);
        // La guardamos en el repositorio
        return tarjetasRepository.save(tarjetaActualizada);
    }

    @Override
    @CacheEvict
    public void deleteById(Long id) {
        log.debug("Borrando tarjeta por id: " + id);
        var tarjetaEncontrada = this.findById(id);
        // La borramos del repositorio si existe
        if (tarjetaEncontrada != null)
            tarjetasRepository.deleteById(id);

    }

}
