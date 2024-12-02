package daw2a.gestionalimentos.dto.usuario;

import lombok.Data;

@Data
public class UsuarioDTO {
    private Long id;
    private String username;
    // No incluimos la contraseña por razones de seguridad
}