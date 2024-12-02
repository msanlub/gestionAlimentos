package daw2a.gestionalimentos.services;

import daw2a.gestionalimentos.dto.inventarioUsuario.InventarioUsuarioDTO;
import daw2a.gestionalimentos.dto.inventarioUsuario.InventarioUsuarioCreateDTO;
import daw2a.gestionalimentos.entities.InventarioUsuario;
import daw2a.gestionalimentos.entities.Usuario;
import daw2a.gestionalimentos.repositories.InventarioUsuarioRepository;
import daw2a.gestionalimentos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InventarioUsuarioService {

    @Autowired
    private InventarioUsuarioRepository inventarioUsuarioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<InventarioUsuarioDTO> getInventarioUsuarioById(Long id) {
        return inventarioUsuarioRepository.findById(id).map(this::convertToDTO);
    }

    public Optional<InventarioUsuarioDTO> getInventarioUsuarioByUsuarioId(Long usuarioId) {
        return inventarioUsuarioRepository.findByUsuarioId(usuarioId).map(this::convertToDTO);
    }

    public InventarioUsuarioDTO createInventarioUsuario(InventarioUsuarioCreateDTO createDTO) {
        Usuario usuario = usuarioRepository.findById(createDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        InventarioUsuario inventarioUsuario = new InventarioUsuario();
        inventarioUsuario.setUsuario(usuario);

        return convertToDTO(inventarioUsuarioRepository.save(inventarioUsuario));
    }

    public boolean deleteInventarioUsuario(Long id) {
        if (inventarioUsuarioRepository.existsById(id)) {
            inventarioUsuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private InventarioUsuarioDTO convertToDTO(InventarioUsuario inventarioUsuario) {
        InventarioUsuarioDTO dto = new InventarioUsuarioDTO();
        dto.setId(inventarioUsuario.getId());
        dto.setUsuarioId(inventarioUsuario.getUsuario().getId());
        dto.setAlimentosIds(inventarioUsuario.getAlimentos().stream()
                .map(alimento -> alimento.getId())
                .collect(Collectors.toList()));
        return dto;
    }
}