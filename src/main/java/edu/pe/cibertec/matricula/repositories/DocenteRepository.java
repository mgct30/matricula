package edu.pe.cibertec.matricula.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.pe.cibertec.matricula.entities.Docente;

public interface DocenteRepository extends CrudRepository <Docente, Integer>{

    List<Docente> findAll();
 
    List<Docente> findByNombre(String nombre);
}  
    
