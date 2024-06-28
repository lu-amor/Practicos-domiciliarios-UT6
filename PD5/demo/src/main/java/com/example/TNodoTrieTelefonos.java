package com.example;

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieTelefonos extends TNodoTrieHashMap implements INodoTrieTelefonos {
    private LinkedList<TAbonado> abonados;

    public TNodoTrieTelefonos(char caracter) {
        super(caracter);
        this.abonados = new LinkedList<>();
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> resultado) {
        TNodoTrieTelefonos nodo = buscarNodo(primerosDigitos);
        if (nodo != null) {
            insertarTelefonos(nodo, resultado);
        }
    }

    private TNodoTrieTelefonos buscarNodo(String prefijo) {
        TNodoTrieTelefonos actual = this;
        HashMap<Character, TNodoTrieHashMap> hijos = actual.getHijos();

        for (char c : prefijo.toCharArray()) {
            if (hijos.containsKey(c)) {
                actual = (TNodoTrieTelefonos) hijos.get(c);
                hijos = actual.getHijos();
            } else {
                return null;
            }
        }

        return actual;
    }

    private void insertarTelefonos(TNodoTrieTelefonos nodo, LinkedList<TAbonado> resultado) {
        if (nodo.esPalabra()) {
            resultado.addAll(nodo.abonados);
        }
        for (TNodoTrieHashMap hijo : nodo.getHijos().values()) {
            insertarTelefonos((TNodoTrieTelefonos) hijo, resultado);
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        String telefono = unAbonado.getTelefono();
        TNodoTrieTelefonos actual = this;
        HashMap<Character, TNodoTrieHashMap> hijos = actual.getHijos();

        for (char c : telefono.toCharArray()) {
            if (!hijos.containsKey(c)) {
                TNodoTrieTelefonos nuevoNodo = new TNodoTrieTelefonos(c);
                hijos.put(c, nuevoNodo);
            }
            actual = (TNodoTrieTelefonos) hijos.get(c);
            hijos = actual.getHijos();
        }

        actual.setPalabra(true);
        actual.abonados.add(unAbonado);
    }
}
