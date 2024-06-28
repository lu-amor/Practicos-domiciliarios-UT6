package com.example;

public class TablaHash implements IHash {
    private int[] tabla;
    private int tamañoTabla;

    public TablaHash(int cantClaves) {
        tamañoTabla = (int) (cantClaves / 0.9); // Dimensionamiento de la tabla según factor de carga 0.9, es decir 90%
        tabla = new int[tamañoTabla];
    }

    @Override
    public int funcionHashing(int unaClave) {
        return unaClave ;
    }

    @Override
    public int insertar(int unaClave) {
        int i = 0;
        do {
            int j = funcionHashing(unaClave + i)% tamañoTabla;
            if (tabla[j] == 0) {
                tabla[j] = unaClave;
                return i; // Cantidad de comparaciones realizadas
            }
            i++;
        } while (i < tamañoTabla);
        throw new RuntimeException("Sobrecarga de tabla de hash");
    }

    @Override
    public int buscar(int unaClave) {
        int i = 0;
        do {
            int j = funcionHashing(unaClave + i)% tamañoTabla;
            if (tabla[j] == unaClave) {
                return i + 1; // Cantidad de comparaciones realizadas
            }
            if (tabla[j] == 0) {
                return i + 1; // Cantidad de comparaciones realizadas
            }
            i++;
        } while (i < tamañoTabla);
        return i;
    }
}
