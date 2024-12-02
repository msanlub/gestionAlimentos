package daw2a.gestionalimentos.controllers;

import daw2a.gestionalimentos.dto.existencias.ExistenciasDTO;
import daw2a.gestionalimentos.dto.existencias.ExistenciasCreateDTO;
import daw2a.gestionalimentos.dto.existencias.ExistenciasUpdateDTO;
import daw2a.gestionalimentos.services.ExistenciasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/existencias")
public class ExistenciasController {

    @Autowired
    private ExistenciasService existenciasService;

    @GetMapping
    public Page<ExistenciasDTO> getAllExistencias(Pageable pageable) {
        return existenciasService.getAllExistencias(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExistenciasDTO> getExistenciasById(@PathVariable Long id) {
        return existenciasService.getExistenciasById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ExistenciasDTO createExistencias(@RequestBody ExistenciasCreateDTO createDTO) {
        return existenciasService.createExistencias(createDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExistenciasDTO> updateExistencias(@PathVariable Long id, @RequestBody ExistenciasUpdateDTO updateDTO) {
        return existenciasService.updateExistencias(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExistencias(@PathVariable Long id) {
        if (existenciasService.deleteExistencias(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/alimento/{alimentoId}")
    public List<ExistenciasDTO> getExistenciasByAlimentoId(@PathVariable Long alimentoId) {
        return existenciasService.findByAlimentoId(alimentoId);
    }

    @GetMapping("/ordenadas-por-fecha")
    public Page<ExistenciasDTO> getExistenciasOrderedByFechaEntrada(Pageable pageable) {
        return existenciasService.findByOrderByFechaEntradaAsc(pageable);
    }

    @GetMapping("/ubicacion/{ubicacionId}")
    public List<ExistenciasDTO> getExistenciasByUbicacionId(@PathVariable Long ubicacionId) {
        return existenciasService.findByUbicacionId(ubicacionId);
    }
}