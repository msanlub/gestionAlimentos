package daw2a.gestionalimentos.controllers;

import daw2a.gestionalimentos.dto.inventarioUsuario.InventarioUsuarioDTO;
import daw2a.gestionalimentos.dto.inventarioUsuario.InventarioUsuarioCreateDTO;
import daw2a.gestionalimentos.services.InventarioUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventario-usuario")
public class InventarioUsuarioController {

    @Autowired
    private InventarioUsuarioService inventarioUsuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<InventarioUsuarioDTO> getInventarioUsuarioById(@PathVariable Long id) {
        return inventarioUsuarioService.getInventarioUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<InventarioUsuarioDTO> getInventarioUsuarioByUsuarioId(@PathVariable Long usuarioId) {
        return inventarioUsuarioService.getInventarioUsuarioByUsuarioId(usuarioId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public InventarioUsuarioDTO createInventarioUsuario(@RequestBody InventarioUsuarioCreateDTO createDTO) {
        return inventarioUsuarioService.createInventarioUsuario(createDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventarioUsuario(@PathVariable Long id) {
        if (inventarioUsuarioService.deleteInventarioUsuario(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}