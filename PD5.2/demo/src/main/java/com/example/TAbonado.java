package com.example;

public class TAbonado implements Comparable<TAbonado> {

    private String nombre;
    private String telefono;

    public TAbonado(String unNombre, String unTelefono) {
        this.nombre = unNombre;
        this.telefono = unTelefono;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getTelefono() {
        return this.telefono;
    }

    @Override
    public int compareTo(TAbonado o) {
        return this.nombre.compareTo(o.getNombre());
    }
}

