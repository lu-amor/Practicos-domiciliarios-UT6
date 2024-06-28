package com.example;

import java.io.Serializable;
import java.util.HashMap;

public class TNodoTrieHashMap implements Serializable {
    private final char caracter;
    private boolean esPalabra;

    private HashMap<Character, TNodoTrieHashMap> hijos = new HashMap<>();

    public TNodoTrieHashMap(char caracter) {
        this.caracter = caracter;
        this.esPalabra = false;
    }

    public HashMap<Character, TNodoTrieHashMap> getHijos() {
        return hijos;
    }

    public char getCaracter() {
        return caracter;
    }

    public boolean esPalabra() {
        return esPalabra;
    }

    public void setPalabra(boolean esPalabra) {
        this.esPalabra = esPalabra;
    }
}
