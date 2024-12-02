package daw2a.gestionalimentos.dto.ubicacion;

import daw2a.gestionalimentos.entities.enumeradas.Tipo_ubicacion;
import lombok.Data;

@Data
public class UbicacionUpdateDTO {
    private String descripcion;
    private int capacidad;
    private Tipo_ubicacion tipoUbicacion;
}