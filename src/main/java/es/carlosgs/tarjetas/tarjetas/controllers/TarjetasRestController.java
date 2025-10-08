package es.carlosgs.tarjetas.tarjetas.controllers;

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
    public ResponseEntity<Tarjeta> create(@RequestBody Tarjeta tarjeta) {
        var saved = tarjetasService.save(tarjeta);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarjeta> update(@PathVariable Long id, @RequestBody Tarjeta tarjeta) {
        log.info("Actualizando tarjeta id={} con tarjeta={}", id, tarjeta);
        return ResponseEntity.ok(tarjetasService.update(id, tarjeta));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Tarjeta> updatePartial(@PathVariable Long id, @RequestBody Tarjeta tarjeta) {
        log.info("Actualizando parcialmente tarjeta con id={} con tarjeta={}",id, tarjeta);
        return ResponseEntity.ok(tarjetasService.update(id, tarjeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Borrando producto por id: " + id);
        tarjetasService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
