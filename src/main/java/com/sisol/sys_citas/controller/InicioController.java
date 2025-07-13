package com.sisol.sys_citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
public class InicioController {
    
    @GetMapping("/")
    public String mostrarInicio(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("titulo", "SISOL - Sistema de Citas Médicas");
        model.addAttribute("descripcion", "Sistema Integral de Salud Ocupacional y Laboral");
        return "inicio";
    }
    
    @GetMapping("/inicio")
    public String mostrarInicioAlternativo(Model model, HttpSession session){
        return mostrarInicio(model, session);
    }
}
