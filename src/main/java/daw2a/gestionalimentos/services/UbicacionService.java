package daw2a.gestionalimentos.services;

import daw2a.gestionalimentos.dto.ubicacion.UbicacionDTO;
import daw2a.gestionalimentos.dto.ubicacion.UbicacionCreateDTO;
import daw2a.gestionalimentos.dto.ubicacion.UbicacionUpdateDTO;
import daw2a.gestionalimentos.entities.Ubicacion;
import daw2a.gestionalimentos.entities.enumeradas.Tipo_ubicacion;
import daw2a.gestionalimentos.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public Page<UbicacionDTO> getAllUbicaciones(Pageable pageable) {
        return ubicacionRepository.findAll(pageable).map(this::convertToDTO);
    }

    public Optional<UbicacionDTO> getUbicacionById(Long id) {
        return ubicacionRepository.findById(id).map(this::convertToDTO);
    }

    public UbicacionDTO createUbicacion(UbicacionCreateDTO createDTO) {
        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setDescripcion(createDTO.getDescripcion());
        ubicacion.setCapacidad(createDTO.getCapacidad());
        ubicacion.setTipoUbicacion(createDTO.getTipoUbicacion());
        return convertToDTO(ubicacionRepository.save(ubicacion));
    }

    public Optional<UbicacionDTO> updateUbicacion(Long id, UbicacionUpdateDTO updateDTO) {
        Optional<Ubicacion> ubicacionOptional = ubicacionRepository.findById(id);
        if (ubicacionOptional.isPresent()) {
            Ubicacion ubicacion = ubicacionOptional.get();
            ubicacion.setDescripcion(updateDTO.getDescripcion());
            ubicacion.setCapacidad(updateDTO.getCapacidad());
            ubicacion.setTipoUbicacion(updateDTO.getTipoUbicacion());
            return Optional.of(convertToDTO(ubicacionRepository.save(ubicacion)));
        }
        return Optional.empty();
    }

    public boolean deleteUbicacion(Long id) {
        if (ubicacionRepository.existsById(id)) {
            ubicacionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<UbicacionDTO> getUbicacionesByTipo(Tipo_ubicacion tipo_ubicacion, Pageable pageable) {
        return ubicacionRepository.findByTipoUbicacion(tipo_ubicacion, pageable).map(this::convertToDTO);
    }

    private UbicacionDTO convertToDTO(Ubicacion ubicacion) {
        UbicacionDTO dto = new UbicacionDTO();
        dto.setId(ubicacion.getId());
        dto.setDescripcion(ubicacion.getDescripcion());
        dto.setCapacidad(ubicacion.getCapacidad());
        dto.setTipoUbicacion(ubicacion.getTipoUbicacion());
        return dto;
    }
}