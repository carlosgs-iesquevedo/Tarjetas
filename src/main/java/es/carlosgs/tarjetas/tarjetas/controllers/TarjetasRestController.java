package es.carlosgs.tarjetas.tarjetas.controllers;

import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.repositories.TarjetasRepository;
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
    private final TarjetasRepository tarjetasRepository;

    @Autowired
    public TarjetasRestController(TarjetasRepository tarjetasRepository) {
        this.tarjetasRepository = tarjetasRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Tarjeta>> getAllTarjetas(@RequestParam(required = false) String numero) {
        if (numero != null) {
            return ResponseEntity.ok(tarjetasRepository.findAllByNumero(numero));
        } else {
            //return ResponseEntity.ok(tarjetasRepository.findAll());
            return ResponseEntity.status(HttpStatus.OK).body(tarjetasRepository.findAll());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarjeta> getTarjetaById(@PathVariable Long id) {
        // Estilo estructurado
        /*
        Optional<Tarjeta> tarjeta = tarjetasRepository.findById(id);
        if (tarjeta.isPresent()) {
            return ResponseEntity.ok(tarjeta.get());
        }  else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
         */

        // Estilo funcional
        return tarjetasRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
