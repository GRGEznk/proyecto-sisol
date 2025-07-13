package com.sisol.sys_citas.enums;


public enum GrupoSanguineo {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");


    private final String valorMostrar;


    //constructor
    GrupoSanguineo(String valorMostrar) {
        this.valorMostrar = valorMostrar;
    }

    //getter para mostrar el texto
    public String getValorMostrar(){
        return valorMostrar;
    }

    //getter and setters con lombook



}
