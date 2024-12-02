package daw2a.gestionalimentos.controllers;

import daw2a.gestionalimentos.dto.alimentos.AlimentoDTO;
import daw2a.gestionalimentos.dto.alimentos.AlimentoCreateDTO;
import daw2a.gestionalimentos.dto.alimentos.AlimentoUpdateDTO;
import daw2a.gestionalimentos.services.AlimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    @GetMapping
    public Page<AlimentoDTO> getAllAlimentos(Pageable pageable) {
        return alimentoService.getAllAlimentos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlimentoDTO> getAlimentoById(@PathVariable Long id) {
        return alimentoService.getAlimentoById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AlimentoDTO createAlimento(@RequestBody AlimentoCreateDTO createDTO) {
        return alimentoService.createAlimento(createDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlimentoDTO> updateAlimento(@PathVariable Long id, @RequestBody AlimentoUpdateDTO updateDTO) {
        return alimentoService.updateAlimento(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlimento(@PathVariable Long id) {
        if (alimentoService.deleteAlimento(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public Page<AlimentoDTO> buscarPorNombre(@RequestParam String nombre, Pageable pageable) {
        return alimentoService.findByNombre(nombre, pageable);
    }

    @GetMapping("/conExistencias")
    public Page<AlimentoDTO> buscarConExistencias(Pageable pageable) {
        return alimentoService.findAlimentoByExistencias(pageable);
    }

    @GetMapping("/abiertos")
    public Page<AlimentoDTO> buscarAbiertos(Pageable pageable) {
        return alimentoService.findAlimentoByAbierto(pageable);
    }

    @GetMapping("/porCaducidad")
    public Page<AlimentoDTO> buscarPorCaducidad(Pageable pageable) {
        return alimentoService.findOrderByFechaCaducidadAsc(pageable);
    }
}