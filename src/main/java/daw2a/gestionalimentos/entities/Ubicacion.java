package daw2a.gestionalimentos.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import daw2a.gestionalimentos.entities.enumeradas.Tipo_ubicacion;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincremented
    private Long id;

    private String descripcion;
    private int capacidad;

    @Enumerated(EnumType.STRING)
    private Tipo_ubicacion tipo_ubicacion;

    @OneToMany(mappedBy = "ubicacion", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonManagedReference  // Lado propietario de la relación
    private List<Existencias> existencias;
}
