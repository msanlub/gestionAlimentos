package daw2a.gestionalimentos.dto.existencias;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExistenciasCreateDTO {
    private int cantidadAlimento;
    private LocalDate fechaEntrada;
    private Long alimentoId;
    private Long ubicacionId;
}