package es.carlosgs.tarjetas.titulares.services;

import es.carlosgs.tarjetas.titulares.dto.TitularRequestDto;
import es.carlosgs.tarjetas.titulares.models.Titular;

import java.util.List;

public interface TitularesService {
    List<Titular> findAll(String nombre);

    Titular findByNombre(String nombre);

    Titular findById(Long id);

    Titular save(TitularRequestDto titularRequestDto);

    Titular update(Long id, TitularRequestDto titularRequestDto);

    void deleteById(Long id);
}
