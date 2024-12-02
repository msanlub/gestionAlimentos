package daw2a.gestionalimentos.controllers;

import daw2a.gestionalimentos.dto.usuario.UsuarioDTO;
import daw2a.gestionalimentos.dto.usuario.UsuarioCreateDTO;
import daw2a.gestionalimentos.dto.usuario.UsuarioUpdateDTO;
import daw2a.gestionalimentos.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<UsuarioDTO> getAllUsuarios(Pageable pageable) {
        return usuarioService.getAllUsuarios(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id) {
        return usuarioService.getUsuarioById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public UsuarioDTO createUsuario(@RequestBody UsuarioCreateDTO createDTO) {
        return usuarioService.createUsuario(createDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable Long id, @RequestBody UsuarioUpdateDTO updateDTO) {
        return usuarioService.updateUsuario(id, updateDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        if (usuarioService.deleteUsuario(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public Page<UsuarioDTO> findByUsername(@RequestParam String username, Pageable pageable) {
        return usuarioService.findByUsername(username, pageable);
    }
}