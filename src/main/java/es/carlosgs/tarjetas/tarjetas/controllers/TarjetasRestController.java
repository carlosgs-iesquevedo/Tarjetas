package es.carlosgs.tarjetas.tarjetas.controllers;

import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.services.TarjetasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Tarjeta>> getAllTarjetas(@RequestParam(required = false) String numero) {
        if (numero != null) {
            return ResponseEntity.ok(tarjetasService.findAll(numero, null));
        } else {
            //return ResponseEntity.ok(tarjetasRepository.findAll());
            return ResponseEntity.status(HttpStatus.OK).body(tarjetasService.findAll(null, null));
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> getTarjetaById(@PathVariable Long id) {

        return ResponseEntity.ok(tarjetasService.findById(id));

    }




}
