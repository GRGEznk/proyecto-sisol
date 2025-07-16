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
    
    @GetMapping("/nosotros")
    public String mostrarNosotros(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("titulo", "Sobre Nosotros - SISOL");
        model.addAttribute("descripcion", "Conoce más sobre SISOL y nuestro compromiso con tu salud");
        return "nosotros";
    }
    
    @GetMapping("/servicios")
    public String mostrarServicios(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("titulo", "Nuestros Servicios - SISOL");
        model.addAttribute("descripcion", "Descubre nuestra amplia gama de servicios médicos especializados");
        return "servicios";
    }
    
    @GetMapping("/agenda-citas")
    public String mostrarAgendaCitas(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("titulo", "Mi Agenda de Citas - SISOL");
        model.addAttribute("descripcion", "Gestiona tus citas médicas de forma fácil y rápida");
        return "agenda-citas";
    }
    
    @GetMapping("/pagos")
    public String mostrarPagos(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("titulo", "Gestión de Pagos - SISOL");
        model.addAttribute("descripcion", "Realiza y gestiona tus pagos de forma segura y rápida");
        return "pagos";
    }
    
    @GetMapping("/mi-cuenta")
    public String mostrarMiCuenta(Model model, HttpSession session){
        model.addAttribute("usuario", session.getAttribute("usuario"));
        model.addAttribute("titulo", "Mi Cuenta - SISOL");
        model.addAttribute("descripcion", "Gestiona tu información personal y preferencias");
        return "mi-cuenta";
    }
}
