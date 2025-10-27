package es.carlosgs.tarjetas.tarjetas.controllers;

import es.carlosgs.tarjetas.tarjetas.dto.TarjetaCreateDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaResponseDto;
import es.carlosgs.tarjetas.tarjetas.dto.TarjetaUpdateDto;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.services.TarjetasService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/${api.version}/tarjetas")
@RestController
public class TarjetasRestController {

    // Repositorio de tarjetas
    private final TarjetasService tarjetasService;

    @GetMapping()
    public ResponseEntity<List<TarjetaResponseDto>> getAllTarjetas(@RequestParam(required = false) String numero,
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
    public ResponseEntity<TarjetaResponseDto> create(@Valid @RequestBody TarjetaCreateDto tarjetaCreateDto) {
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

    /**
     * Manejador de excepciones de Validación: 400 Bad Request
     *
     * @param ex excepción
     * @return Mapa de errores de validación con el campo y el mensaje
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        BindingResult result = ex.getBindingResult();

        problemDetail.setDetail("Falló la validación para el objeto='" + result.getObjectName()
                + "'. " + "Núm. errores: " + result.getErrorCount());

        Map<String, String> errores = new HashMap<>();

        result.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errores.put(fieldName, errorMessage);
        });
        problemDetail.setProperty("errores", errores);
        return problemDetail;
    }




}
