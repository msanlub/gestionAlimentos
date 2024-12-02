package daw2a.gestionalimentos.dto.existencias;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExistenciasDTO {
    private Long id;
    private int cantidadAlimento;
    private LocalDate fechaEntrada;
    private Long alimentoId;
    private Long ubicacionId;
}