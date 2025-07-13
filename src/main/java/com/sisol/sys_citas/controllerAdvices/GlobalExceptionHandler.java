package com.sisol.sys_citas.controllerAdvices;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;

import com.sisol.sys_citas.exceptions.AutenticacionException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Para peticiones normales (no AJAX)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, RedirectAttributes ra, 
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        
        // Si es una petición AJAX, no redirigir
        if ("XMLHttpRequest".equals(requestedWith)) {
            return null; // Dejar que el controlador maneje la respuesta
        }
        
        ra.addFlashAttribute("error", "Ocurrió un error inesperado. Por favor, inténtalo de nuevo.");
        return "redirect:/inicio";
    }

    // Para excepciones de autenticación en peticiones normales
    @ExceptionHandler(AutenticacionException.class)
    public String handleAuthException(AutenticacionException ex, RedirectAttributes ra,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        
        // Si es una petición AJAX, no redirigir
        if ("XMLHttpRequest".equals(requestedWith)) {
            return null; // Dejar que el controlador maneje la respuesta
        }
        
        ra.addFlashAttribute("error", ex.getMessage());
        return "redirect:/inicio";
    }

    // Para errores de base de datos
    @ExceptionHandler(org.springframework.dao.DataAccessException.class)
    public String handleDataAccessException(org.springframework.dao.DataAccessException ex, 
            RedirectAttributes ra,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        
        if ("XMLHttpRequest".equals(requestedWith)) {
            return null;
        }
        
        ra.addFlashAttribute("error", "Error de conexión con la base de datos. Inténtalo más tarde.");
        return "redirect:/inicio";
    }
} 