package es.carlosgs.tarjetas.tarjetas.services;

import es.carlosgs.tarjetas.tarjetas.dto.TarjetaCreateDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaResponseDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaUpdateDto;

import java.util.List;

public interface TarjetasService {
  List<TarjetaResponseDto> findAll(String numero, String titular);

  TarjetaResponseDto findById(Long id);

  TarjetaResponseDto findByUuid(String uuid);

  TarjetaResponseDto save(TarjetaCreateDto tarjetaCreateDto);

  TarjetaResponseDto update(Long id, TarjetaUpdateDto tarjetaUpdateDto);

  void deleteById(Long id);

}
