package edu.pe.cibertec.matricula.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.pe.cibertec.matricula.entities.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Integer> {

    List<Alumno> findAll(); 
    
}