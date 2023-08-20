package edu.pe.cibertec.matricula.dtos;

public class DocenteDto {

    String NomCompletoDocente;
    Integer IdCurso;
    String DireccionDocente;
    String TelefDocente; 

   public String getNomCompletoDocente() {
       return NomCompletoDocente;
   }
   public void setNomCompletoDocente(String nomCompletoDocente) {
       this.NomCompletoDocente = nomCompletoDocente;
   }

   public Integer getIdCurso() {
       return IdCurso;
   }
   public void setIdCurso(Integer idCurso) {
       this.IdCurso = idCurso;
   }
   public String getDireccionDocente() {
       return DireccionDocente;
   }
   public void setDireccionDocente(String direccionDocente) {
       this.DireccionDocente = direccionDocente;
   }
public String getTelefDocente() {
    return TelefDocente;
}
public void setTelefDocente(String telefDocente) {
    this.TelefDocente = telefDocente;
}


}