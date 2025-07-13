package com.sisol.sys_citas.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sisol.sys_citas.dto.UsuarioSesionDTO;






@Mapper
public interface AuthMapper {
    UsuarioSesionDTO verificarCorreo(@Param("correo")String correo);

    // Métodos para obtener nombres y apellidos según el rol
    String obtenerNombrePaciente(@Param("id") Long id);
    String obtenerNombreMedico(@Param("id") Long id);
    String obtenerNombrePersonalAdministrativo(@Param("id") Long id);
}
