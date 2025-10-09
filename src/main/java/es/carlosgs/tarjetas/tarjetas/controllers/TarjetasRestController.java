package es.carlosgs.tarjetas.tarjetas.controllers;

import es.carlosgs.tarjetas.tarjetas.dto.TarjetaCreateDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaResponseDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaUpdateDto;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.services.TarjetasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Slf4j
@RequestMapping("api/${api.version}/tarjetas")
@RestController
public class TarjetasRestController {

    // Repositorio de tarjetas
    private final TarjetasService tarjetasService;

    @Autowired
    public TarjetasRestController(TarjetasService tarjetasService) {
        this.tarjetasService = tarjetasService;
    }

    @GetMapping()
    public ResponseEntity<List<Tarjeta>> getAllTarjetas(@RequestParam(required = false) String numero,
                                                        @RequestParam(required = false) String titular) {
        log.info("Buscando tarjetas por numero={}, titular={}", numero, titular);
        return ResponseEntity.ok(tarjetasService.findAll(numero, titular));
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> getTarjetaById(@PathVariable Long id) {
        log.info("Buscando tarjeta por id {}", id);
        return ResponseEntity.ok(tarjetasService.findById(id));

    }

    @PostMapping()
    public ResponseEntity<TarjetaResponseDto> create(@RequestBody TarjetaCreateDto tarjetaCreateDto) {
        log.info("Creando tarjeta : {}", tarjetaCreateDto);
        var saved = tarjetasService.save(tarjetaCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarjeta> update(@PathVariable Long id, @RequestBody TarjetaUpdateDto tarjetaUpdateDto) {
        log.info("Actualizando tarjeta id={} con tarjeta={}", id, tarjetaUpdateDto);
        return ResponseEntity.ok(tarjetasService.update(id, tarjetaUpdateDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarjeta> updatePartial(@PathVariable Long id, @RequestBody TarjetaUpdateDto tarjetaUpdateDto) {
        log.info("Actualizando parcialmente tarjeta con id={} con tarjeta={}",id, tarjetaUpdateDto);
        return ResponseEntity.ok(tarjetasService.update(id, tarjetaUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Borrando tarjeta por id: " + id);
        tarjetasService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
