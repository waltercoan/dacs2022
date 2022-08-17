package br.univille.apidacs2022.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.apidacs2022.service.PacienteService;
import br.univille.coredacs2022.entity.Paciente;
import br.univille.coredacs2022.repository.PacienteRepository;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteControllerAPI {
    
    @Autowired
    private PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(){
        var listaPacientes = service.getAll();
        return new 
            ResponseEntity<List<Paciente>>
                (listaPacientes,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Paciente> 
        insertPaciente(@RequestBody Paciente paciente){
        service.save(paciente);
        return new ResponseEntity<Paciente>(paciente,HttpStatus.CREATED);
    }
}