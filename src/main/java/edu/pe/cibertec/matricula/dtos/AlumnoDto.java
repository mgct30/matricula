package edu.pe.cibertec.matricula.dtos;

public class AlumnoDto {
    
    String NombreAlumno;
    String DireccionAlumno;
    Integer IdCurso;
    String TelefonoAlumno;


    public String getNombreAlumno() {
        return NombreAlumno;
    }
    public void setNombreAlumno(String nombreAlumno) {
        NombreAlumno = nombreAlumno;
    }
    public String getDireccionAlumno() {
        return DireccionAlumno;
    }

    public void setDireccionAlumno(String direccionAlumno) {
        DireccionAlumno = direccionAlumno;
    }
    public Integer getIdCurso() {
        return IdCurso;
    }
    public void setIdCurso(Integer idCurso) {
        IdCurso = idCurso;
    }
    public String getTelefonoAlumno() {
        return TelefonoAlumno;
    }
    public void setTelefonoAlumno(String telefonoAlumno) {
        TelefonoAlumno = telefonoAlumno;
    }
}
