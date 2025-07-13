package com.sisol.sys_citas.security;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataAccessException;

import com.sisol.sys_citas.dto.UsuarioSesionDTO;
import com.sisol.sys_citas.exceptions.AutenticacionException;
import com.sisol.sys_citas.forms.LoginForm;
import com.sisol.sys_citas.mapper.AuthMapper;

@Service
public class AutenticacionService {

    private final AuthMapper authMapper;

    public AutenticacionService(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    @Transactional
    public UsuarioSesionDTO login(LoginForm loginForm) {

        try {

            UsuarioSesionDTO usuario = authMapper.verificarCorreo(loginForm.getCorreo());

            if (!usuario.getContrasenia().equals(loginForm.getPassword())) {
                throw new AutenticacionException("Contraseña incorrecta");
            }

            // Obtener nombre completo según el rol
            String nombreCompleto = null;
            switch (usuario.getRol()) {
                case ROLE_PACIENTE:
                    nombreCompleto = authMapper.obtenerNombrePaciente(usuario.getId());
                    break;
                case ROLE_MEDICO:
                    nombreCompleto = authMapper.obtenerNombreMedico(usuario.getId());
                    break;
                case ROLE_PERSONAL_ADMINISTRATIVO:
                    nombreCompleto = authMapper.obtenerNombrePersonalAdministrativo(usuario.getId());
                    break;
                default:
                    nombreCompleto = "Usuario";
            }
            usuario.setNombres(nombreCompleto);

            // if (PasswordHashUtils.checkPassword(usuario.getContrasenia(),
            // loginForm.getPassword())) {

            // throw new AutenticacionException("Correo y/o contraseña incorrectos");

            // }

            return usuario;

        } catch (DataAccessException exsql) {
            throw new AutenticacionException(exsql.getCause().getMessage());
        } catch (AutenticacionException exauth) {
            throw exauth;
        } catch (Exception exp) {
            throw new AutenticacionException(exp.getMessage());
        }

    }

}
