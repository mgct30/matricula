package edu.pe.cibertec.matricula.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    // @RequestMapping(path = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/nosotros")
    public String about() {
        return "about";
    }
}

