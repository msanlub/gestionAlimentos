package daw2a.gestionalimentos.dto.inventarioUsuario;

import lombok.Data;
import java.util.List;

@Data
public class InventarioUsuarioDTO {
    private Long id;
    private Long usuarioId;
    private List<Long> alimentosIds;
}