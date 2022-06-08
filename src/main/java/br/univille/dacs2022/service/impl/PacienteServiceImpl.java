package br.univille.dacs2022.service.impl;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dacs2022.dto.PacienteDTO;
import br.univille.dacs2022.entity.Paciente;
import br.univille.dacs2022.mapper.PacienteMapper;
import br.univille.dacs2022.repository.PacienteRepository;
import br.univille.dacs2022.service.PacienteService;

@Service
public class PacienteServiceImpl 
    implements PacienteService{
    
    @Autowired
    private PacienteRepository repository;
    
    private PacienteMapper mapper 
        = Mappers.getMapper(PacienteMapper.class);

    
    @Override
    public List<PacienteDTO> getAll() {
        return mapper.mapListPaciente(repository.findAll());
    }


    @Override
    public PacienteDTO save(PacienteDTO paciente) {
        Paciente pacienteEntity = mapper.mapPacienteDTO(paciente);
        pacienteEntity = repository.save(pacienteEntity);
        return mapper.mapPaciente(pacienteEntity);
    }
    
}
