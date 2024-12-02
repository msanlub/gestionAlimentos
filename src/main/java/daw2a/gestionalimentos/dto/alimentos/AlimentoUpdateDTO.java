package daw2a.gestionalimentos.dto.alimentos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AlimentoUpdateDTO {
    private String nombre;
    private LocalDate fechaCaducidad;
    private boolean abierto;
    private boolean perecedero;
    private Long inventarioUsuarioId;
}