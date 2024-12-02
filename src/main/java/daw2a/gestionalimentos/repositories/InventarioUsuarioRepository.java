package daw2a.gestionalimentos.repositories;

import daw2a.gestionalimentos.entities.InventarioUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioUsuarioRepository extends JpaRepository<InventarioUsuario, Long> {
    Optional<InventarioUsuario> findByUsuarioId(Long usuarioId);
}