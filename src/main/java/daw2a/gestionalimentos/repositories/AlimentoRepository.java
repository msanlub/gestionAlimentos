package daw2a.gestionalimentos.repositories;

import daw2a.gestionalimentos.entities.Alimento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepository  extends JpaRepository<Alimento,Long> {
    // Aquí podemos agregar métodos de consulta personalizados si fuera necesario
    //busqueda por nombre
    Page<Alimento> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);

    //busqueda por si hay almacenado
    Page<Alimento> findAlimentoByExistencias(Pageable pageable);

    //busqueda por si hay abierto para consumir antes
    Page<Alimento> findAlimentoByAbierto(Pageable pageable);

    //busqueda por fecha de caducidad reciente
    Page<Alimento> findOrderByFecha_caducidadAsc(Pageable pageable);
}
