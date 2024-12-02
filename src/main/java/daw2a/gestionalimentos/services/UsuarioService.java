package daw2a.gestionalimentos.services;

import daw2a.gestionalimentos.dto.usuario.UsuarioDTO;
import daw2a.gestionalimentos.dto.usuario.UsuarioCreateDTO;
import daw2a.gestionalimentos.dto.usuario.UsuarioUpdateDTO;
import daw2a.gestionalimentos.entities.Usuario;
import daw2a.gestionalimentos.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioDTO> getAllUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(this::convertToDTO);
    }

    public Optional<UsuarioDTO> getUsuarioById(Long id) {
        return usuarioRepository.findById(id).map(this::convertToDTO);
    }

    public UsuarioDTO createUsuario(UsuarioCreateDTO createDTO) {
        Usuario usuario = new Usuario();
        usuario.setUsername(createDTO.getUsername());
        usuario.setPassword(createDTO.getPassword()); // Considera encriptar la contraseña
        return convertToDTO(usuarioRepository.save(usuario));
    }

    public Optional<UsuarioDTO> updateUsuario(Long id, UsuarioUpdateDTO updateDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setUsername(updateDTO.getUsername());
            usuario.setPassword(updateDTO.getPassword()); // Considera encriptar la contraseña
            return Optional.of(convertToDTO(usuarioRepository.save(usuario)));
        }
        return Optional.empty();
    }

    public boolean deleteUsuario(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<UsuarioDTO> findByUsername(String username, Pageable pageable) {
        return usuarioRepository.findByUsernameContainingIgnoreCase(username, pageable).map(this::convertToDTO);
    }

    private UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setUsername(usuario.getUsername());
        return dto;
    }
}