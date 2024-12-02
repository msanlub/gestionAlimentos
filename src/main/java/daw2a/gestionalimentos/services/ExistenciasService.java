package daw2a.gestionalimentos.services;

import daw2a.gestionalimentos.dto.existencias.ExistenciasDTO;
import daw2a.gestionalimentos.dto.existencias.ExistenciasCreateDTO;
import daw2a.gestionalimentos.dto.existencias.ExistenciasUpdateDTO;
import daw2a.gestionalimentos.entities.Existencias;
import daw2a.gestionalimentos.repositories.ExistenciasRepository;
import daw2a.gestionalimentos.repositories.AlimentoRepository;
import daw2a.gestionalimentos.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ExistenciasService {

    @Autowired
    private ExistenciasRepository existenciasRepository;

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public Page<ExistenciasDTO> getAllExistencias(Pageable pageable) {
        return existenciasRepository.findAll(pageable).map(this::convertToDTO);
    }

    public Optional<ExistenciasDTO> getExistenciasById(Long id) {
        return existenciasRepository.findById(id).map(this::convertToDTO);
    }

    public ExistenciasDTO createExistencias(ExistenciasCreateDTO createDTO) {
        Existencias existencias = new Existencias();
        existencias.setCantidad_alimento(createDTO.getCantidadAlimento());
        existencias.setFecha_entrada(createDTO.getFechaEntrada());
        existencias.setAlimento(alimentoRepository.findById(createDTO.getAlimentoId()).orElseThrow());
        existencias.setUbicacion(ubicacionRepository.findById(createDTO.getUbicacionId()).orElseThrow());
        return convertToDTO(existenciasRepository.save(existencias));
    }

    public Optional<ExistenciasDTO> updateExistencias(Long id, ExistenciasUpdateDTO updateDTO) {
        Optional<Existencias> existenciasOptional = existenciasRepository.findById(id);
        if (existenciasOptional.isPresent()) {
            Existencias existencias = existenciasOptional.get();
            existencias.setCantidad_alimento(updateDTO.getCantidadAlimento());
            existencias.setFecha_entrada(updateDTO.getFechaEntrada());
            existencias.setAlimento(alimentoRepository.findById(updateDTO.getAlimentoId()).orElseThrow());
            existencias.setUbicacion(ubicacionRepository.findById(updateDTO.getUbicacionId()).orElseThrow());
            return Optional.of(convertToDTO(existenciasRepository.save(existencias)));
        }
        return Optional.empty();
    }

    public boolean deleteExistencias(Long id) {
        if (existenciasRepository.existsById(id)) {
            existenciasRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<ExistenciasDTO> findByAlimentoId(Long alimentoId) {
        return existenciasRepository.findByAlimentoId(alimentoId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Page<ExistenciasDTO> findByOrderByFechaEntradaAsc(Pageable pageable) {
        return existenciasRepository.findByOrderByFecha_entradaAsc(pageable).map(this::convertToDTO);
    }

    public List<ExistenciasDTO> findByUbicacionId(Long ubicacionId) {
        return existenciasRepository.findByUbicacionId(ubicacionId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ExistenciasDTO convertToDTO(Existencias existencias) {
        ExistenciasDTO dto = new ExistenciasDTO();
        dto.setId(existencias.getId());
        dto.setCantidadAlimento(existencias.getCantidad_alimento());
        dto.setFechaEntrada(existencias.getFecha_entrada());
        dto.setAlimentoId(existencias.getAlimento().getId());
        dto.setUbicacionId(existencias.getUbicacion().getId());
        return dto;
    }
}