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

import edu.pe.cibertec.matricula.repositories.DocenteRepository;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import edu.pe.cibertec.matricula.entities.Docente;
import edu.pe.cibertec.matricula.dtos.DocenteDto;

@Controller
@RequestMapping("docentes")
public class DocenteController {

    DocenteRepository docenteRepository;
    DataSource dataSource;

    public DocenteController(DocenteRepository docenteRepository, DataSource dataSource){
        this.docenteRepository = docenteRepository;
        this.dataSource = dataSource;
    }

    @GetMapping
    public String list(Model modelo){
        List<Docente> docentes = docenteRepository.findAll();
        modelo.addAttribute("listaDocentes", docentes);

        return "docentes/list"; 
    }

    @GetMapping("create")
    public String showCreateForm(Model model){
        DocenteDto docenteDto = new DocenteDto();
        model.addAttribute("docenteForm", docenteDto);
        return "docentes/create"; 

    }
    @PostMapping
    public String create(DocenteDto docenteDto){
        Docente docente = new Docente(docenteDto.getNomCompletoDocente(), docenteDto.getIdCurso(), docenteDto.getDireccionDocente(),docenteDto.getTelefDocente());
        docenteRepository.save(docente); 
        return "redirect:/docentes";
    }

    @GetMapping("{CodDocente}")
    public String detail(@PathVariable Integer CodDocente, Model model) {
        Optional<Docente> doceOptional = docenteRepository.findById(CodDocente);
        if(doceOptional.isEmpty()){
            return "404";
        }

        Docente docente = doceOptional.get();
        model.addAttribute("docente", docente);
        return "docentes/detail";
    }
    
    @GetMapping("{CodDocente}/edit")
    public String showEditForm(@PathVariable Integer CodDocente, Model model) {
        Optional<Docente> doceOptional = docenteRepository.findById(CodDocente);
        if(doceOptional.isEmpty()) {
            return "404";
        }

        Docente docente = doceOptional.get();
        model.addAttribute("docente", docente);
        return "docentes/edit";
    }

    @PostMapping("{CodDocente}")
    public String edit(@PathVariable Integer CodDocente, Docente docenteDataForm) {
        Optional<Docente> docenteOptional = docenteRepository.findById(CodDocente);
        if(docenteOptional.isEmpty()) {
            return "404";
        }

        Docente docente = docenteOptional.get(); 
        docente.setNombre (docenteDataForm.getNombre());
        docente.setIdCurso  (docenteDataForm.getIdCurso());
        docente.setDireccionDocente  (docenteDataForm.getDireccionDocente());
        docente.setTelefDocente  (docenteDataForm.getTelefDocente());
        docenteRepository.save(docente);

        return "redirect:/docentes"; 
    }

    @PostMapping("{CodDocente}/delete")
    public String delete (@PathVariable Integer CodDocente ){
        docenteRepository.deleteById(CodDocente);
         return "redirect:/docentes"; 
    }

    @GetMapping("report")
    public void downloadReport(HttpServletResponse response) throws SQLException {
        try {
            InputStream inputStream = new ClassPathResource("reports/listado_docentes.jasper").getInputStream();
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