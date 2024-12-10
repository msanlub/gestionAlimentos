package daw2a.gestionalimentos.repositories;

import daw2a.gestionalimentos.entities.Existencias;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExistenciasRepository  extends JpaRepository<Existencias,Long> {
    List<Existencias> findByAlimentoId(Long alimento_id);

    // Obtiene las existencias ordenadas por fecha de entrada (más antiguas primero, FIFO)
    Page<Existencias> findAllByOrderByFechaEntradaAsc(Pageable pageable);

    //Obtiene las existencias por ubicacion
    List<Existencias> findByUbicacionId(Long ubicacion_id);
}

