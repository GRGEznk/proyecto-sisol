package com.sisol.sys_citas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.sisol.sys_citas.exceptions.AutenticacionException;
import com.sisol.sys_citas.forms.LoginForm;
import com.sisol.sys_citas.security.AutenticacionService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "redirect:/inicio";
    }

    @PostMapping("/login")
    @ResponseBody
    public Object procesarLogin(@ModelAttribute("loginForm") LoginForm form, HttpSession session,
            RedirectAttributes ra,
            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        boolean isAjax = "XMLHttpRequest".equals(requestedWith);
        try {
            session.setAttribute("usuario", autenticacionService.login(form));
            if (isAjax) {
                return ResponseEntity.ok().body("ok");
            } else {
                return "redirect:/inicio";
            }
        } catch (AutenticacionException ex) {
            if (isAjax) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(java.util.Collections.singletonMap("error", ex.getMessage()));
            } else {
                ra.addFlashAttribute("error", ex.getMessage());
                return "redirect:/auth/login";
            }
        } catch (Exception ex) {
            if (isAjax) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(java.util.Collections.singletonMap("error", "error en el sistema"));
            } else {
                ra.addFlashAttribute("error", "error en el sistema");
                return "redirect:/auth/login";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

    @PostMapping("/logout")
    public String logoutPost(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }

}
