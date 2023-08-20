package edu.pe.cibertec.matricula.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity 
public class Docente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer CodDocente; 
    
    String nombre;
    Integer IdCurso;
    String DireccionDocente;
    String TelefDocente; 


    public Docente()    {}

    public Docente(String nombre, Integer IdCurso, String DireccionDocente, String TelefDocente){
        this.nombre = nombre;
        this.IdCurso = IdCurso;
        this.DireccionDocente = DireccionDocente;
        this.TelefDocente = TelefDocente;
    }

    public Integer getCodDocente() {
        return CodDocente;
    }

  public String getNombre() {
      return nombre;
  }

    public Integer getIdCurso() {
        return IdCurso;
    }
    public String getDireccionDocente() {
        return DireccionDocente;
    }
    public String getTelefDocente() {
        return TelefDocente;
    }
    public void setIdCurso(Integer idCurso) {
        this.IdCurso = idCurso;
    }
    public void setDireccionDocente(String direccionDocente) {
        this.DireccionDocente = direccionDocente;
    }
    public void setTelefDocente(String telefDocente) {
        this.TelefDocente = telefDocente;
    }

    public void setCodDocente(Integer codDocente) {
        CodDocente = codDocente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
