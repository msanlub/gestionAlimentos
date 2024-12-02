package daw2a.gestionalimentos.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventarioUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //por si en el futuro cada usuario va a manejar varios inventarios

    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "inventarioUsuario")
    private List<Alimento> alimentos;
}
