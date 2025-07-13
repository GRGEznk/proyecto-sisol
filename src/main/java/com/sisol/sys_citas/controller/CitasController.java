package com.sisol.sys_citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/citas")
public class CitasController {
    
    @GetMapping("/nueva")
    public String mostrarFormularioCita(Model model, HttpSession session){
        model.addAttribute("titulo", "Agendar Nueva Cita - SISOL");
        model.addAttribute("usuario", session.getAttribute("usuario"));
        return "citas/nueva";
    }
    
    @PostMapping("/nueva")
    public String procesarCita(
            @RequestParam String departamento,
            @RequestParam String doctor,
            @RequestParam String fecha,
            @RequestParam String hora,
            Model model,
            HttpSession session){
        
        // Aquí iría la lógica para guardar la cita
        // Por ahora solo redirigimos a la página de inicio
        model.addAttribute("mensaje", "Cita agendada exitosamente");
        return "redirect:/";
    }
} 