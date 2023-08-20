package edu.pe.cibertec.matricula.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pe.cibertec.matricula.dtos.AlumnoDto;
import edu.pe.cibertec.matricula.entities.Alumno;
import edu.pe.cibertec.matricula.repositories.AlumnoRepository;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("alumnos")

public class AlumnoController {

    AlumnoRepository alumnoRepository;
    DataSource dataSource;

    public AlumnoController(AlumnoRepository alumnoRepository, DataSource dataSource) {
        this.alumnoRepository = alumnoRepository;
        this.dataSource = dataSource;
    }

    @GetMapping
    public String list(Model modelo){
       List<Alumno> alumnos = alumnoRepository.findAll();
       modelo.addAttribute("listaAlumnos", alumnos);
       return "alumnos/list"; 

    }

        @GetMapping("create")
    public String showCreateForm(Model model){
        AlumnoDto alumnoDto = new AlumnoDto();
        model.addAttribute("alumnoForm", alumnoDto);
        return "alumnos/create"; 
    }

    @PostMapping
    public String create(AlumnoDto alumnoDto){
        Alumno  alumno = new Alumno(alumnoDto.getNombreAlumno(), alumnoDto.getDireccionAlumno(),alumnoDto.getIdCurso(),alumnoDto.getTelefonoAlumno());
        alumnoRepository.save(alumno);
        return "redirect:/alumnos";
    }

        @GetMapping("{CodAlumno}")
    public String detail(@PathVariable Integer CodAlumno, Model model ){
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(CodAlumno);
        if(alumnoOptional.isEmpty()){
            return "404"; 
        }

        Alumno alumno = alumnoOptional.get();
        model.addAttribute("alumno", alumno);
        return "alumnos/detail";
    }
    @GetMapping("{CodAlumno}/edit")
    public String showEditForm(@PathVariable Integer CodAlumno, Model model){
        Optional<Alumno> alumOptional = alumnoRepository.findById(CodAlumno);
        if(alumOptional.isEmpty()) {
            return "404";
        }
        Alumno alumno = alumOptional.get();
        model.addAttribute("alumno", alumno);
        return "alumnos/edit";
     }

     @PostMapping("{CodAlumno}")
     public String edit(@PathVariable Integer CodAlumno, Alumno alumnoDataForm){
        Optional<Alumno> alumnoOptional = alumnoRepository.findById(CodAlumno);
        if(alumnoOptional.isEmpty()){
            return "404";
        }

        Alumno alumno = alumnoOptional.get();
        alumno.setNombreAlumno (alumnoDataForm.getNombreAlumno());
        alumno.setDireccionAlumno (alumnoDataForm.getDireccionAlumno());
        alumno.setIdCurso (alumnoDataForm.getIdCurso());
        alumno.setTelefonoAlumno (alumnoDataForm.getTelefonoAlumno());
        alumnoRepository.save(alumno);

            return "redirect:/alumnos";
    }

    @PostMapping("{CodAlumno}/delete")
    public String delete(@PathVariable Integer CodAlumno ){
        alumnoRepository.deleteById(CodAlumno);
        return "redirect:/alumnos";

    }


    @GetMapping("report")
    public void downloadReport(HttpServletResponse response) throws SQLException {
        try {
            InputStream inputStream = new ClassPathResource("reports/listado_alumnos.jasper").getInputStream();
            JasperReport report = (JasperReport) JRLoader.loadObject(inputStream);

            // JRDataSource dataSource = new JREmptyDataSource();
            Connection connection = dataSource.getConnection();


            Map<String, Object> parameters = new HashMap<>();
            //parameters.put("nombreEmpresa", "InkaFarma");
            //parameters.put("descargadoPor", "Arthur");

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, connection);
            connection.close();

            // OutputStream outputStream = new FileOutputStream("hola.pdf");
            response.setContentType("application/pdf");
            OutputStream outputStream =  response.getOutputStream();
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);



        } catch (IOException | JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    
}
    
