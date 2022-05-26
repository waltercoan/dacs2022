package br.univille.dacs2022.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dacs2022.entity.Paciente;
import br.univille.dacs2022.repository.PacienteRepository;
import br.univille.dacs2022.service.PacienteService;

@Service
public class PacienteServiceImpl 
    implements PacienteService{
    
    @Autowired
    private PacienteRepository repository;

    @Override
    public List<Paciente> getAll() {
        return repository.findAll();
    }
    
}
