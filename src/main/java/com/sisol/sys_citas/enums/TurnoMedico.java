package com.sisol.sys_citas.enums;

public enum TurnoMedico {
    MANANA("Mañana"),
    TARDE("Tarde");

    private final String mostrarValor;

    TurnoMedico(String mostrarValor){
        this.mostrarValor = mostrarValor;
    }
    public String getMostrarValor (){
        return mostrarValor;
    }
}
