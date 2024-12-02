package daw2a.gestionalimentos.repositories;

import daw2a.gestionalimentos.entities.Ubicacion;
import daw2a.gestionalimentos.entities.enumeradas.Tipo_ubicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    Page<Ubicacion> findByTipoUbicacion(Tipo_ubicacion tipo, Pageable pageable);
}