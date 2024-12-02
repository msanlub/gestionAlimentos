package daw2a.gestionalimentos.services;

import daw2a.gestionalimentos.dto.alimentos.AlimentoDTO;
import daw2a.gestionalimentos.dto.alimentos.AlimentoCreateDTO;
import daw2a.gestionalimentos.dto.alimentos.AlimentoUpdateDTO;
import daw2a.gestionalimentos.entities.Alimento;
import daw2a.gestionalimentos.repositories.AlimentoRepository;
import daw2a.gestionalimentos.repositories.InventarioUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AlimentoService {

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private InventarioUsuarioRepository inventarioUsuarioRepository;

    public Page<AlimentoDTO> getAllAlimentos(Pageable pageable) {
        return alimentoRepository.findAll(pageable).map(this::convertToDTO);
    }

    public Optional<AlimentoDTO> getAlimentoById(Long id) {
        return alimentoRepository.findById(id).map(this::convertToDTO);
    }

    public AlimentoDTO createAlimento(AlimentoCreateDTO createDTO) {
        Alimento alimento = new Alimento();
        alimento.setNombre(createDTO.getNombre());
        alimento.setFecha_caducidad(createDTO.getFechaCaducidad());
        alimento.setAbierto(createDTO.isAbierto());
        alimento.setPerecedero(createDTO.isPerecedero());
        alimento.setInventarioUsuario(inventarioUsuarioRepository.findById(createDTO.getInventarioUsuarioId()).orElseThrow());
        return convertToDTO(alimentoRepository.save(alimento));
    }

    public Optional<AlimentoDTO> updateAlimento(Long id, AlimentoUpdateDTO updateDTO) {
        Optional<Alimento> alimentoOptional = alimentoRepository.findById(id);
        if (alimentoOptional.isPresent()) {
            Alimento alimento = alimentoOptional.get();
            alimento.setNombre(updateDTO.getNombre());
            alimento.setFecha_caducidad(updateDTO.getFechaCaducidad());
            alimento.setAbierto(updateDTO.isAbierto());
            alimento.setPerecedero(updateDTO.isPerecedero());
            alimento.setInventarioUsuario(inventarioUsuarioRepository.findById(updateDTO.getInventarioUsuarioId()).orElseThrow());
            return Optional.of(convertToDTO(alimentoRepository.save(alimento)));
        }
        return Optional.empty();
    }

    public boolean deleteAlimento(Long id) {
        if (alimentoRepository.existsById(id)) {
            alimentoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Page<AlimentoDTO> findByNombre(String nombre, Pageable pageable) {
        return alimentoRepository.findByNombreContainingIgnoreCase(nombre, pageable).map(this::convertToDTO);
    }

    public Page<AlimentoDTO> findAlimentoByExistencias(Pageable pageable) {
        return alimentoRepository.findAlimentoByExistencias(pageable).map(this::convertToDTO);
    }

    public Page<AlimentoDTO> findAlimentoByAbierto(Pageable pageable) {
        return alimentoRepository.findAlimentoByAbierto(pageable).map(this::convertToDTO);
    }

    public Page<AlimentoDTO> findOrderByFechaCaducidadAsc(Pageable pageable) {
        return alimentoRepository.findOrderByFecha_caducidadAsc(pageable).map(this::convertToDTO);
    }

    private AlimentoDTO convertToDTO(Alimento alimento) {
        AlimentoDTO dto = new AlimentoDTO();
        dto.setId(alimento.getId());
        dto.setNombre(alimento.getNombre());
        dto.setFechaCaducidad(alimento.getFecha_caducidad());
        dto.setAbierto(alimento.isAbierto());
        dto.setPerecedero(alimento.isPerecedero());
        dto.setInventarioUsuarioId(alimento.getInventarioUsuario().getId());
        return dto;
    }
}