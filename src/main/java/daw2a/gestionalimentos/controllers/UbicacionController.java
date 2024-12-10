package daw2a.gestionalimentos.controllers;

import daw2a.gestionalimentos.dto.ubicacion.UbicacionDTO;
import daw2a.gestionalimentos.dto.ubicacion.UbicacionCreateDTO;
import daw2a.gestionalimentos.dto.ubicacion.UbicacionUpdateDTO;
import daw2a.gestionalimentos.entities.enumeradas.Tipo_ubicacion;
import daw2a.gestionalimentos.services.UbicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ubicaciones")
public class UbicacionController {

    @Autowired
    private UbicacionService ubicacionService;

    @GetMapping
    public Page<UbicacionDTO> getAllUbicaciones(Pageable pageable) {
        return ubicacionService.getAllUbicaciones(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UbicacionDTO> getUbicacionById(@PathVariable Long id) {
        return ubicacionService.getUbicacionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UbicacionDTO createUbicacion(@RequestBody UbicacionCreateDTO createDTO) {
        return ubicacionService.createUbicacion(createDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDTO> updateUbicacion(@PathVariable Long id, @RequestBody UbicacionUpdateDTO updateDTO) {
        return ubicacionService.updateUbicacion(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable Long id) {
        if (ubicacionService.deleteUbicacion(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/tipo/{tipo}")
    public Page<UbicacionDTO> getUbicacionesByTipo(@PathVariable Tipo_ubicacion tipo_ubicacion, Pageable pageable) {
        return ubicacionService.getUbicacionesByTipo(tipo_ubicacion, pageable);
    }
}