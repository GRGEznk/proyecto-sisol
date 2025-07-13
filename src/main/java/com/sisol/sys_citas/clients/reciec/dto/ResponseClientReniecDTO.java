package com.sisol.sys_citas.clients.reciec.dto;

import java.time.LocalDate;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ResponseClientReniecDTO {
    private String dni;
    private String nombre;
    private String apellidoParterno;
    private String apellidoMaterno;
    private String sexo;
    private LocalDate fechaNacimiento;  
}
