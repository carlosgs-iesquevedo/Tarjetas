package es.carlosgs.tarjetas.tarjetas.services;

import es.carlosgs.tarjetas.tarjetas.dto.TarjetaResponseDto;
import es.carlosgs.tarjetas.tarjetas.mappers.TarjetaMapper;
import es.carlosgs.tarjetas.tarjetas.models.Tarjeta;
import es.carlosgs.tarjetas.tarjetas.repositories.TarjetasRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarjetasServiceImplTest {

    private final Tarjeta tarjeta1 = Tarjeta.builder()
            .id(1L)
            .numero("1234-5678-1234-5678")
            .cvc("555")
            .fechaCaducidad(LocalDate.of(2025,12,31))
            .titular("Jose")
            .saldo(100.0)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .uuid(UUID.fromString("57727bc2-0c1c-494e-bbaf-e952a778e478"))
            .build();

    private final Tarjeta tarjeta2 = Tarjeta.builder()
            .id(2L)
            .numero("4321-5678-1234-5678")
            .cvc("555")
            .fechaCaducidad(LocalDate.of(2025,12,31))
            .titular("Juan")
            .saldo(100.0)
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .uuid(UUID.fromString("b36835eb-e56a-4023-b058-52bfa600fee5"))
            .build();

    private final TarjetaResponseDto tarjetaResponse1 = TarjetaResponseDto.builder()
            .id(1L)
            .numero("1234-5678-1234-5678")
            .cvc("555")
            .fechaCaducidad(LocalDate.of(2025,12,31))
            .titular("Jose")
            .saldo(100.0)
            .build();

    private final TarjetaResponseDto tarjetaResponse2 = TarjetaResponseDto.builder()
            .id(2L)
            .numero("4321-5678-1234-5678")
            .cvc("555")
            .fechaCaducidad(LocalDate.of(2025,12,31))
            .titular("Juan")
            .saldo(100.0)
            .build();

    @Mock
    private TarjetasRepository tarjetasRepository;

    @Mock
    private TarjetaMapper tarjetaMapper;

    @InjectMocks
    private TarjetasServiceImpl tarjetasService;

    @Captor // Captor de argumentos
    private ArgumentCaptor<Tarjeta> tarjetaCaptor;

    @Test
    void findAll_ShouldReturnAllTarjetas_WhenNoParametersProvided() {
        // Arrange
        List<Tarjeta> expectedTarjetas = Arrays.asList(tarjeta1, tarjeta2);
        List<TarjetaResponseDto> expectedTarjetaResponses =
                Arrays.asList(tarjetaResponse1, tarjetaResponse2);
        when(tarjetasRepository.findAll()).thenReturn(expectedTarjetas);
        when(tarjetaMapper.toTarjetaResponseDto(anyList())).thenReturn(expectedTarjetaResponses);

        // Act
        List<TarjetaResponseDto> actualTarjetaResponses = tarjetasService.findAll(null, null);

        // Assert
        assertIterableEquals(expectedTarjetaResponses, actualTarjetaResponses);

        // Verify
        verify(tarjetasRepository, times(1)).findAll();
        verify(tarjetaMapper, times(1)).toTarjetaResponseDto(anyList());

    }

    @Test
    void findAll_ShouldReturnTarjetasByNumero_WhenNumeroParameterProvided() {
        // Arrange
        String numero = "1234-5678-1234-5678";
        List<Tarjeta> expectedTarjetas = List.of(tarjeta1);
        List<TarjetaResponseDto> expectedTarjetaResponses = List.of(tarjetaResponse1);
        when(tarjetasRepository.findAllByNumero(numero)).thenReturn(expectedTarjetas);
        when(tarjetaMapper.toTarjetaResponseDto(anyList())).thenReturn(expectedTarjetaResponses);

        // Act
        List<TarjetaResponseDto> actualTarjetaResponses = tarjetasService.findAll(numero, null);

        // Assert
        assertIterableEquals(expectedTarjetaResponses, actualTarjetaResponses);

        // Verify
        verify(tarjetasRepository, times(1)).findAllByNumero(numero);
        verify(tarjetaMapper, times(1)).toTarjetaResponseDto(anyList());

    }


    @Test
    void findAll_ShouldReturnTarjetasByTitular_WhenTitularParameterProvided() {
        // Arrange
        String titular = "Jose";
        List<Tarjeta> expectedTarjetas = List.of(tarjeta1);
        List<TarjetaResponseDto> expectedTarjetaResponses = List.of(tarjetaResponse1);
        when(tarjetasRepository.findAllByTitular(titular)).thenReturn(expectedTarjetas);
        when(tarjetaMapper.toTarjetaResponseDto(anyList())).thenReturn(expectedTarjetaResponses);

        // Act
        List<TarjetaResponseDto> actualTarjetaResponses = tarjetasService.findAll(null, titular);

        // Assert
        assertIterableEquals(expectedTarjetaResponses, actualTarjetaResponses);

        // Verify
        verify(tarjetasRepository, times(1)).findAllByTitular(titular);
        verify(tarjetaMapper, times(1)).toTarjetaResponseDto(anyList());

    }

    @Test
    void findAll_ShouldReturnTarjetasByNumeroAndTitular_WhenBothParametersProvided() {

    }


    @Test
    void findbyUuid() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
}