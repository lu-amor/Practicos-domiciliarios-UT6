package com.example;

public class THash implements IHash {
    private int[] T;
    private int size;
    private double loadFactor;

    public THash(int initialCapacity, double loadFactor) {
        this.T = new int[initialCapacity];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public int buscar(int unaClave) {
        int comparaciones = 0;
        int i = 0;
        int j = funcionHashing(unaClave) % T.length;
        while (T[j] != 0 && i < T.length) {
            comparaciones++;
            if (T[j] == unaClave) {
                return Math.abs(comparaciones);
            }
            i++;
            j = (funcionHashing(unaClave) + i) % T.length;
        }
        return -Math.abs(comparaciones);
    }

    public int insertar(int unaClave) {
        if (size + 1 > T.length * loadFactor) {
            redimensionar();
        }

        int comparaciones = 0;
        int i = 0;
        int j = funcionHashing(unaClave) % T.length;
        while (i < T.length && T[j] != 0) {
            comparaciones++;
            if (T[j] == unaClave) {
                // Si la clave ya existe, no se inserta de nuevo
                return comparaciones;
            }
            i++;
            j = (funcionHashing(unaClave) + i) % T.length;
        }
        if (i < T.length) {
            T[j] = unaClave;
            size++;
            comparaciones++;
            return comparaciones;
        } else {
            throw new UnsupportedOperationException("Hash table overflow");
        }
    }

    private void redimensionar() {
        int newCapacity = T.length * 2;
        int[] newTable = new int[newCapacity];
        int[] oldTable = T;
        T = newTable;
        size = 0;

        for (int clave : oldTable) {
            if (clave != 0) {
                insertar(clave);
            }
        }
    }

    public int funcionHashing(int unaClave) {
        return unaClave;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return T.length;
    }

    public double getLoadFactor() {
        return loadFactor;
    }
}