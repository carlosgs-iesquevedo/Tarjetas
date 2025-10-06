package es.carlosgs.tarjetas.tarjetas.controllers;

import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.services.TarjetasService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    /*
    //Sin anotación @ResponseStatus(HttpStatus.NOT_FOUND) habría que hacer esto

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> getTarjetaById(@PathVariable Long id) {
        log.info("Buscando tarjeta por id {}", id);
        try {
            Tarjeta t = tarjetasService.findById(id);
            return ResponseEntity.ok(t);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

    }

     */




}
