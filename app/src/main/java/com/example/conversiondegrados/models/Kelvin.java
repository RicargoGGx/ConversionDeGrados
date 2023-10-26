package com.example.conversiondegrados.models;

public class Kelvin extends Grado{
    public Kelvin(Double valor, String unidad) {
        super(valor, unidad);
    }

    public Kelvin parse(Celsius c){
        return new Kelvin(c.getValor()-273.15, "C");
    }

    public Kelvin parse(Fahrenheit f){
        return new Kelvin((f.getValor()-273.15)*9/5+32, "F");
    }
}