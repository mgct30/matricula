package edu.pe.cibertec.matricula.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer CodAlumno;

    String NombreAlumno;
    String DireccionAlumno;
    Integer IdCurso;
    String TelefonoAlumno;
    

    public Alumno() {}

    public Alumno(String NombreAlumno,  String DireccionAlumno, Integer IdCurso, String TelefonoAlumno){
        this.NombreAlumno = NombreAlumno;
        this.DireccionAlumno = DireccionAlumno;
        this.IdCurso = IdCurso;
        this.TelefonoAlumno = TelefonoAlumno;
    }

    public Integer getCodAlumno() {
        return CodAlumno;
    }

    public void setCodAlumno(Integer codAlumno) {
        this.CodAlumno = codAlumno;
    }

    public String getNombreAlumno() {
        return NombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.NombreAlumno = nombreAlumno;
    }

    public String getDireccionAlumno() {
        return DireccionAlumno;
    }

    public void setDireccionAlumno(String direccionAlumno) {
        this.DireccionAlumno = direccionAlumno;
    }

    public Integer getIdCurso() {
        return IdCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.IdCurso = idCurso;
    }

    public String getTelefonoAlumno() {
        return TelefonoAlumno;
    }

    public void setTelefonoAlumno(String telefonoAlumno) {
        this.TelefonoAlumno = telefonoAlumno;
    }
}
