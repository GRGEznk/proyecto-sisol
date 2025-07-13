package com.sisol.sys_citas.enums;

public enum EstadoCita {

    CONFIRMADA("CONFIRMADO"),
    CANCELADA("CANCELADO"),
    ATENDIDA("ATENDIDA"),
    NO_ASISTIO("NO ASISTIO");
    
    private final String mostrarValor;

    EstadoCita(String mostrarValor){
        this.mostrarValor = mostrarValor;
    }
    public String getMostrarValor(){
        return mostrarValor;
    }

    
}
