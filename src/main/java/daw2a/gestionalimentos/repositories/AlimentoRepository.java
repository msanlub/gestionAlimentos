package daw2a.gestionalimentos.repositories;

import daw2a.gestionalimentos.entities.Alimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AlimentoRepository  extends JpaRepository<Alimento,Long> {
    // Aquí podemos agregar métodos de consulta personalizados si fuera necesario
    //busqueda por nombre
    Page<Alimento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    //busqueda por si hay almacenado
    Page<Alimento> findAlimentoByExistencias(int existencias, Pageable pageable);

    //busqueda por si es perecedero
    Page<Alimento> findAlimentoByPerecedero(boolean perecedero, Pageable pageable);

    //busqueda por si hay abierto para consumir antes
    Page<Alimento> findAlimentoByAbierto(boolean abierto, Pageable pageable);

    //busqueda por fecha de caducidad reciente
    Page<Alimento> findAllByOrderByFechaCaducidadAsc(Pageable pageable);
}
