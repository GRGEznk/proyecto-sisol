package com.sisol.sys_citas.enums;

public enum Comprobante {
    BOLETA_VENTA("Boleta de venta"),
    FACTURA("Factura");

    private final String mostrarValor;

    Comprobante(String mostrarValor){
        this.mostrarValor = mostrarValor;
    }
    public String getMostrarValor(){
        return mostrarValor;
    }

    
}
