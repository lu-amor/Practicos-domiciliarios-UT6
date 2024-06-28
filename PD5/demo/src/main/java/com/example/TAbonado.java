package com.example;
/**
 * @author ernesto
 */
public class TAbonado implements Comparable {

    private String nombre;
    private String telefono;

    TAbonado(String unNombre, String unTelefono) {

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
    public int compareTo(Object o) {
        return (this.nombre.compareTo(((TAbonado)o).getNombre()));
    }
}
