package edu.pe.cibertec.matricula;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


public class JasperDemo {
    
    public void run() {
        try {
            InputStream inputStream = new ClassPathResource("reports/listado_docentes.jasper").getInputStream();
            JasperReport report = (JasperReport) JRLoader.loadObject(inputStream);
            JRDataSource dataSource = new JREmptyDataSource();
            Map<String, Object> parameters = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, dataSource);

            
            OutputStream outputStream = new FileOutputStream("hola.pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);



        } catch (IOException | JRException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new JasperDemo().run();
    }

}