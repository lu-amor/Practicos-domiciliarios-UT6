package com.example;

import java.io.Serializable;
import java.util.LinkedList;

public class TArbolTrieModificado implements Serializable  {
    private TNodoTrieModificado raiz;

    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrieModificado();
        }
        raiz.insertar(palabra);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    public LinkedList<String> predecir(String prefijo) {
        if (raiz != null) {
            LinkedList<String> palabras = new LinkedList<String>();
            raiz.predecir(prefijo, palabras);
            return palabras;
        }
        return null;
    }

    public int buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra);
        }
        return 0;
    }
}
