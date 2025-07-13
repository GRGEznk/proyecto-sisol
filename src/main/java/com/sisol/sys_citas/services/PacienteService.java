package com.sisol.sys_citas.services;

import com.sisol.sys_citas.clients.reciec.dto.ResponseClientReniecDTO;

public interface PacienteService {

    ResponseClientReniecDTO validarDni(String dni);


} 
