package com.sisol.sys_citas.controllerAdvices;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sisol.sys_citas.dto.UsuarioSesionDTO;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class UsuarioSesionAdvice {


    @ModelAttribute
    public void mantenerSesionUsuario(Model model, HttpSession session){
        //obtener la session del usuario
        UsuarioSesionDTO usuarioSesion =(UsuarioSesionDTO) session.getAttribute("usuario");
        
        if (usuarioSesion != null) {
            model.addAttribute("usuario", usuarioSesion);    
        }
    }
    
}
